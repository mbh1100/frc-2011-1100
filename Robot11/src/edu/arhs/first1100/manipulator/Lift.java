package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.arhs.first1100.util.PID; 
import edu.wpi.first.wpilibj.RobotBase;
import edu.arhs.first1100.opctl.AdvJoystick;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

/**
 *runs the lift
 * @author team1100
 */
public class Lift
{
    // Config for PID controller
    // Will need to calibrate later
    private LiftCamPid camPid;

    private final double kLIFT_P = 0.01;
    private final double kLIFT_I = 0.001;
    private final double kLIFT_D = 0.0001;
    public PID liftPid;
        
    public Encoder encoder;
    
    private Jaguar liftJaguar;

    DigitalInput bottomLimitSwitch;
    DigitalInput topLimitSwitch;

    private boolean hasTopLimitSwitch = false;
    private boolean hasBottomLimitSwitch = false;

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
        topLimitSwitch = new DigitalInput(5);
        
        camPid  = new LiftCamPid();
        camPid.setOutputRange(-0.4, 0.6);
        
        liftPid = new PID(kLIFT_P, kLIFT_I, kLIFT_D, encoder, liftJaguar);
        liftPid.setOutputRange(-0.6, 1.0);
    }
    
    /**
     * set speed without stopping PIDs.
     * Used by LiftCamPid.
     * @param speed
     */
    void setPidSpeed(double speed)
    {
        liftJaguar.set(-speed);
    }

    /*
     *sets the speed of the lift
     * @param speed
     */
    public void setSpeed(double speed)
    {
        //System.out.println("Lift: setSpeed:" + -speed);
        stopPID();

        liftJaguar.set(-speed);
    }

    public double getSpeed()
    {
        return liftJaguar.get();
    }

    public void setPosition(double height)
    {
        System.out.println("setting the lift height to " + height);
        camPid.disable();
        
        liftPid.setSetpoint(height);
        liftPid.enable();
    }
    
    /**
     * when to stop
     */
    public void stop()
    {
        stopPID();
        liftJaguar.set(0.0);
    }

    public void startLiftPid()
    {
        stopCamPid();
        liftPid.enable();
    }

    public void startCamPid()
    {
        //System.out.println("starting cam pid");
        stopLiftPid();
        camPid.enable();
    }
    
    /**
     * what to do when it receives an error
     * @return
     */
    public double getLiftPidError()
    {
        if(liftPid.isEnable())
        {
            //System.out.println("Lift: returning lift Pid");
            return liftPid.getError();
        }
        else{
            return 0.0;
        }
    }

    public double getCamPidError()
    {
        
        if(camPid.isEnable())
        {
            //System.out.println("Lift: returning cam Pid");
            return camPid.getError();
        }
        else
        {
            //System.out.println("Lift: returning no Pid");
            return 0.0;
        }
    }
    
    public void stopPID()
    {
        stopCamPid();
        stopLiftPid();
    }

    private void stopCamPid()
    {
        //System.out.println("stopping cam pid");
        camPid.disable();
    }

    void stopLiftPid()
    {
        liftPid.disable();
    }

    public boolean pidEnabled()
    {
        return liftPidEnabled() || camPidEnabled();
    }

    boolean liftPidEnabled()
    {
        return liftPid.isEnable();
    }

    boolean camPidEnabled()
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
