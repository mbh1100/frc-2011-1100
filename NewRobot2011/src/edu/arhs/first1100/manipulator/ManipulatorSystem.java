/*
 * ManipulatorSystem.java
 *
 * Controls the manipulator component of the robot.
 * 
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author team1100
 */
public class ManipulatorSystem extends SystemBase
{
    private static ManipulatorSystem instance = null;

    public static final int STATE_DEFAULT    = 0;
    public static final int STATE_TOP_PEG    = 3;
    public static final int STATE_MIDDLE_PEG = 2;
    public static final int STATE_BOTTOM_PEG = 1;
    public static final int STATE_FLOOR      = 4;

    public static final int LIFTMUX_OPERATOR = 0;
    public static final int LIFTMUX_PID = 1;
    public static final int LIFTMUX_CAM = 2;

    public static final int ARMMUX_OPERATOR = 0;
    public static final int ARMMUX_PID = 1;
    
    private int liftMUX = LIFTMUX_OPERATOR;
    private int armMUX = ARMMUX_OPERATOR;
    
    private AdvJaguar liftJaguar;
    private Encoder liftEncoder;
    private PID liftPID;
    
    private AdvJaguar armJaguar;
    private Encoder armEncoder;
    private PID armPID;
    
    private PID camPID;

    private Solenoid claw;
    private Solenoid wrist;
    
    public ManipulatorSystem()
    {
        liftJaguar = new AdvJaguar(4, 6, false);

        liftEncoder = new Encoder(8, 9);

        liftPID = new PID(0.1, 0.0, 0.0);
        liftPID.setOutputRange(-0.2, 0.2);  // maybe it should be a bit higher like .3 ?

        armJaguar = new AdvJaguar(4, 8, false);

        armEncoder = new Encoder(10, 11);

        armPID = new PID(0.1, 0.0, 0.0);
        armPID.setOutputRange(-0.2, 0.2);  // maybe it should be a bit higher like .3 ?
        
        camPID = new PID(0.1, 0.0, 0.0);
        camPID.setOutputRange(-0.2, 0.2);  // maybe it should be a bit higher like .3 ?

        claw = new Solenoid(1);
        wrist = new Solenoid(2);
    }
    
    public static ManipulatorSystem getInstance()
    {
        if(instance == null) instance = new ManipulatorSystem();
        return instance;
    }

    public void tick()
    {
        /*
         * Lift code
         */
        if(liftMUX == LIFTMUX_PID)
        {
            Log.defcon1(this, "PID Running...");
            
            liftPID.feed(liftEncoder.get());
            liftJaguar.set(liftPID.get());

            if(liftPID.getError() <= 1.0)
            {
                liftPID.disable();
                imDone();
            }
        }
        else if(liftMUX == LIFTMUX_CAM)
        {
            camPID.feed(CameraSystem.getInstance().getCenterY());
            liftJaguar.set(camPID.get());

            if(camPID.getError() <= 1.0)
            {
                imDone();
            }
        }
        
        /*
         * Arm Code
         */
        if(armMUX == ARMMUX_PID)
        {
            armPID.feed(armEncoder.get());
            armJaguar.set(armPID.get());
            
            if(armPID.getError() <= 1.0)
            {
                imDone();
            }
        }
        Log.defcon2(this, ("Lift Encoder : "+liftEncoder.get()));
    }

    /*
     * Set State
     */
    public void setState(int state)
    {
        switch(state)
        {
            case STATE_DEFAULT:
                setLiftHeight(0);
                setArmPosition(0);
                // Make jags drive down to calibrate encoders?
                break;
            case STATE_TOP_PEG:
                setLiftHeight(1680);
                setArmPosition(62);
                break;
            case STATE_MIDDLE_PEG:
                setLiftHeight(620);
                setArmPosition(128);
                break;
            case STATE_BOTTOM_PEG:
                setLiftHeight(0);
                setArmPosition(121);
                break;
            case STATE_FLOOR:
                setLiftHeight(620);
                setArmPosition(0);
                break;
        }
    }
    
    /*
     * Lift Controls
     */
    public void setLiftSpeed(double speed)
    {
        liftMUX = LIFTMUX_OPERATOR;
        liftJaguar.set(speed);
    }
    
    public void setLiftHeight(double height)
    {
        liftMUX = LIFTMUX_PID;
        liftPID.setTarget(height);
    }

    public void useCamPID()
    {
        liftMUX = LIFTMUX_CAM;
    }
    
    public double getLiftSpeed()
    {
        return liftJaguar.get();
    }

    public double getLiftEncoder()
    {
        return liftEncoder.get();
    }

    /*
     * Arm Controls
     */
    public void setArmSpeed(double speed)
    {
        armMUX = ARMMUX_OPERATOR;
        armJaguar.set(speed);
    }
    public void setArmPosition(double position)
    {
        armMUX = ARMMUX_PID;
        armPID.setTarget(position);
    }
    public double getArmSpeed()
    {
        return armJaguar.get();
    }
    public double getArmEncoder()
    {
        return armEncoder.get();
    }


    /*
     * Claw and Wrist
     */
    public void toggleClaw()
    {
        claw.set(!claw.get());
    }
    public void openClaw()
    {
        claw.set(true);

    }public void closeClaw()
    {
        claw.set(false);
    }
    public boolean getClawState()
    {
        return claw.get();
    }
    public void toggleWrist()
    {
        wrist.set(!wrist.get());
    }
    public void wristUp()
    {
        wrist.set(true);
    }

    public void wristDown()
    {
        wrist.set(false);
    }

    public boolean getWristState()
    {
        return wrist.get();
    }
}