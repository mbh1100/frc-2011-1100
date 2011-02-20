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
    private final double KP = 0.01;
    private final double KI = 0.001;
    private final double KD = 0.0001;
    
    private final double HIGH_VALUE = 62;
    private final double MID_VALUE = 128;
    private final double LOW_VALUE = 121;
    PID pid;
    
    public static final int STATE_HIGH = 2;
    public static final int STATE_MID  = 1;
    public static final int STATE_LOW  = 0;

    private int state = STATE_LOW;
    
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
        pid.setOutputRange(-0.3, 0.2 );
    }
    /**
     *
     * @param value
     */
    public void setState(int state)
    {
        this.state = state;
        switch(state)
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
        pid.enable();
    }

    public int getState()
    {
        return state;
    }
    
    /**
     *when to stop the pid
     * @param speed
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
    
    public void  stopPID()
    {
        pid.disable();
    }
    
    public void resetEncoder()
    {
        encoder.reset();
    }
}
