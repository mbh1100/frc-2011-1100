package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;


/**
 *
 * @author team1100
 */
public class Lift
{
    // Config for PID controller
    // Will need to calibrate later
    private final double kCAM_P = 0.5;
    private final double kCAM_I = 0.05;
    private final double kCAM_D = 0.005;
    private PID camPid;

    private final double kLIFT_P = 0.01;
    private final double kLIFT_I = 0.001;
    private final double kLIFT_D = 0.0001;
    public PID liftPid;
    
    private final double VALUE_LOW = 100;
    private final double VALUE_MID = 1200;
    private final double VALUE_HIGH = 2300;

    public static final int STATE_LOW = 0;
    public static final int STATE_MID = 1;
    public static final int STATE_HIGH = 2;
    
   public Encoder encoder;
    
    private Jaguar liftJaguar;

    DigitalInput bottomLimitSwitch;

    /**
     *
     */
    public Lift()
    {
        liftJaguar = new Jaguar(6);
        encoder = new Encoder(8, 9);
        
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        encoder.start();

        bottomLimitSwitch = new DigitalInput(7);
        
        //camPid  = new PID(kCAM_P, kCAM_I, kCAM_D, null, liftJaguar);
        liftPid = new PID(kLIFT_P, kLIFT_I, kLIFT_D, encoder, liftJaguar);
        liftPid.setOutputRange(-0.3, 0.6);
    }
    /**
     *
     * @param state
     */
    public void setState(int state)
    {
        switch(state)
        {
            case STATE_HIGH:
                liftPid.setSetpoint(VALUE_HIGH);
                break;
            case STATE_MID:
                liftPid.setSetpoint(VALUE_MID);
                break;
            case STATE_LOW:
                liftPid.setSetpoint(VALUE_LOW);
                break;
        }
        liftPid.enable();
    }
    
    /**
     *
     * @param speed
     */
    public void setSpeed(double speed)
    {
        /*
        if (bottomLimitSwitch.get())
        {
            System.out.println("LIFT: Bottom switch pressed");
            
            //encoder.reset();
            if(speed < 0)
            {
                liftJaguar.set(0);
            }
        }
        else
        {
            liftPid.disable();
            //camPid.disable();
            liftJaguar.set(speed);
        }
         *
         */
        
        liftJaguar.set(speed);
    }

    /**
     *
     */
    public void stop()
    {
        //camPid.disable();
        liftPid.disable();
        liftJaguar.set(0.0);
    }

    /**
     *
     * @return
     */
    public double getPidError()
    {
        if(liftPid.isEnable())
        {
            return liftPid.getError();
        }
        /*
        else if(camPid.isEnable())
        {
            return camPid.getError();
        }*/
        else
        {
            return 0.0;
        }
    }
    
    /**
     *
     */
    public void startLiftPid()
    {
        //camPid.disable();
        liftPid.enable();
    }

    /**
     *
     */
    public void startArmPid()
    {
        //camPid.enable();
        liftPid.disable();
    }
    
    /**
     *
     */
    public void stopPID()
    {
        //camPid.disable();
        liftPid.disable();
    }
}