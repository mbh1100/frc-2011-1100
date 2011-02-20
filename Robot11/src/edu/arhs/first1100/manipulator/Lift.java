package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;


/**
 *runs the lift
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
    
    private final double VALUE_LOW = 0;
    private final double VALUE_MID = 1369;
    private final double VALUE_HIGH = 2382;

    public static final int STATE_LOW = 0;
    public static final int STATE_MID = 1;
    public static final int STATE_HIGH = 2;

    private int state = STATE_LOW;
    
    public Encoder encoder;
    
    private Jaguar liftJaguar;

    DigitalInput bottomLimitSwitch;
    
    /**
     *what encoders the lift is on and stuff
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
     *sets the state of the lift
     * @param state
     */
    public void setState(int state)
    {
        this.state = state;
        
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

    public int getState()
    {
        return state;
    }
    
    /**
     *sets the speed of the lift
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
         */
        liftPid.disable();
        liftJaguar.set(speed);
    }

    /**
     *when to stop
     */
    public void stop()
    {
        //camPid.disable();
        liftPid.disable();
        liftJaguar.set(0.0);
    }

    /**
     *what to do when it reieves an error
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
     *when to start lift
     */
    public void startLiftPid()
    {
        //camPid.disable();
        liftPid.enable();
    }

    /**
     *when to start arm
     */
    public void startArmPid()
    {
        //camPid.enable();
        liftPid.disable();
    }
    
    /**
     *when to stop lift
     */
    public void stopPID()
    {
        //camPid.disable();
        liftPid.disable();
    }

    /**
     * Zero both arm and lift encoders
     */
    public void resetEncoder()
    {
        encoder.reset();
    }
}
