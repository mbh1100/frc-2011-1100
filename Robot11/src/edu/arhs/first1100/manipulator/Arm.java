package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;

/**
 *how to run the arm
 * @author team1100
 */
public class Arm
{
    private final double KP = 0.5;
    private final double KI = 0.5;
    private final double KD = 0.5;
    
    private final double HIGH_VALUE = 0;
    private final double MID_VALUE = 125;
    private final double LOW_VALUE = 250;
    private PID pid;
    
    public static final int STATE_HIGH = 2;
    public static final int STATE_MID  = 1;
    public static final int STATE_LOW  = 0;

    public Encoder encoder;
    
    private Jaguar armJaguar;
    /**
     *runs the arm
     */
    public Arm()
    {
        armJaguar = new Jaguar(8);
        encoder = new Encoder(10, 11);

        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        encoder.start();
        
        pid = new PID(KP, KI, KD, encoder, armJaguar);
    }
    /**
     *
     * @param value
     */
    public void setState(int value)
    {
        pid.enable();
        switch(value)
        {
            case STATE_HIGH:
                pid.setSetpoint(HIGH_VALUE);
                break;
            case STATE_MID:
                pid.setSetpoint(MID_VALUE);
                break;
            case STATE_LOW:
                pid.setSetpoint(LOW_VALUE);
                break;
        }
    }
    /**
     *when to stop the pid
     */
    public void stop()
    {
        pid.disable();
    }
/**
 *sets the speed of the arm
 * @param speed
 */
    public void setSpeed(double speed)
    {
        pid.disable();
        armJaguar.set(speed);
    }
/**
 *what to do when it gets an error.
 * @return
 */
    public double getPidError()
    {
        return pid.getError();
    }

}
