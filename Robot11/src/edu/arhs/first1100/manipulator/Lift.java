package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.YPIDSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.arhs.first1100.util.PID;
import edu.wpi.first.wpilibj.RobotBase;
import edu.arhs.first1100.opctl.AdvJoystick;

/**
 *runs the lift
 * @author team1100
 */
public class Lift
{
    // Config for PID controller
    // Will need to calibrate later
    private final double kCAM_P = 1;
    private final double kCAM_I = 0;
    private final double kCAM_D = 0;
    public PID camPid;

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
    public Lift(YPIDSource ypids)
    {
        liftJaguar = new Jaguar(6);
        encoder = new Encoder(8, 9);
        
        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        encoder.start();
        
        bottomLimitSwitch = new DigitalInput(7);
        
        camPid  = new PID(kCAM_P, kCAM_I, kCAM_D, ypids , liftJaguar);
        camPid.setOutputRange(-0.4, 0.6);
        liftPid = new PID(kLIFT_P, kLIFT_I, kLIFT_D, encoder, liftJaguar);
        liftPid.setOutputRange(-0.6, 1.0);
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
        System.out.println("Lift: setSpeed:" + speed);
        liftPid.disable();
        
        if(!bottomLimitSwitch.get())
        {
            System.out.println("LIFT: reached bottom");
            
            resetEncoder();
            if(speed<0)
                liftJaguar.set(0.0);
            else
                liftJaguar.set(speed);
        }
        else if(encoder.get() > 2400)
        {
            System.out.println("LIFT: reached top");
            if (speed > 0)
            {
                liftJaguar.set(0);
            }
            else
            {
                liftJaguar.set(speed);
            }
        }
        else
        {
            liftJaguar.set(speed);
        }
    }

    public double getSpeed()
    {
        return liftJaguar.get();
    }
    
    public void setHeight(double height)
    {
        System.out.println("setting the lift height to " + height);
        liftPid.setSetpoint(height);
        liftPid.enable();
    }
    
    /**
     * when to stop
     */
    public void stop()
    {
        camPid.disable();
        liftPid.disable();
        liftJaguar.set(0.0);
    }

    public void startLiftPid()
    {
        camPid.disable();
        liftPid.enable();
    }

    public void startCamPid()
    {
        camPid.enable();
        liftPid.disable();
    }
    
    /**
     * what to do when it recieves an error
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
    
    public void stopPID()
    {
        camPid.disable();
        liftPid.disable();
    }

    public void stopCamPid()
    {
        camPid.disable();
    }

    public void stopLiftPid()
    {
        liftPid.disable();
    }

    public boolean pidEnabled()
    {
        return liftPid.isEnable() || camPid.isEnable();
    }

    public boolean liftPidEnabled()
    {
        return liftPid.isEnable();
    }

    public boolean camPidEnabled()
    {
        return camPid.isEnable();
    }

    public double getEncoder()
    {
        return encoder.get();
    }

    public void resetEncoder()
    {
        encoder.reset();
    }
}
