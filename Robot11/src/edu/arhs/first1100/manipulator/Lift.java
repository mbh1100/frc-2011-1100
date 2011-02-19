package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

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

    private final double kLIFT_P = 0.1;
    private final double kLIFT_I = 0.01;
    private final double kLIFT_D = 0.001;
    private PID liftPid;
    
    private final double VALUE_LOW = 0;
    private final double VALUE_MID = 100;
    private final double VALUE_HIGH = 200;

    public static final int STATE_LOW = 0;
    public static final int STATE_MID = 1;
    public static final int STATE_HIGH = 2;

    private int state = STATE_LOW;
    
    private final double pulseDistance = .1;
    private Encoder encoder;
    
    private Jaguar liftJaguar;

    /**
     *
     */
    public Lift()
    {
        liftJaguar = new Jaguar(6);
        encoder = new Encoder(8, 9);
        
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        encoder.setDistancePerPulse(pulseDistance);
        encoder.start();
        
        //camPid  = new PID(kCAM_P, kCAM_I, kCAM_D, null, liftJaguar);
        liftPid = new PID(kLIFT_P, kLIFT_I, kLIFT_D, encoder, liftJaguar);
    }
/**
 *
 * @param state
 */
    public void setState(int state)
    {
        liftPid.enable();
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
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(double speed)
    {
        liftPid.disable();
        //camPid.disable();
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
        else if(camPid.isEnable())
        {
            return camPid.getError();
        }
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
