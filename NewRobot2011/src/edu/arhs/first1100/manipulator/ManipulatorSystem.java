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
    private LiftPID liftPID;
    
    private AdvJaguar armJaguar;
    private Encoder armEncoder;
    private ArmPID armPID;
    
    private CamPID liftCamPID;

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
        
        liftCamPID = new PID(0.1, 0.0, 0.0);
        liftCamPID.setOutputRange(-0.2, 0.2);  // maybe it should be a bit higher like .3 ?

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
         * MUX Control
         *
         * All PIDS revert back to the operator state after moving, and
         * calls a notify.  Operator state = no pids enabled.
         */
        switch(liftMUX)
        {
            case LIFTMUX_OPERATOR:
                liftPID.disable();
                liftCamPID.disable();
                break;

            case LIFTMUX_PID:
                liftPID.enable();
                liftCamPID.disable();
                
                if(liftPID.getError() <= 1.0)
                {
                    liftMUX = LIFTMUX_OPERATOR;
                    imDone();
                }
                break;

            case LIFTMUX_CAM:
                liftPID.disable();
                liftCamPID.enable();

                if(liftCamPID.getError() <= 1.0)
                {
                    imDone();
                }
                break;
        }
        
        /*
         * Arm Code
         */
        switch(armMUX)
        {
            case ARMMUX_OPERATOR:
                armPID.disable();
                break;
            case ARMMUX_PID:
                armPID.enable();
                if(armPID.getError() <= 1.0)
                {
                    imDone();
                }
                break;
        }

        /*
         * Limit switch
         */
        if(!bottoLiftLimitSwitch.get())
        {
            resetLiftEncoder();
            liftJaguar.set(0.0);
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
     * Lift Interfaces
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

    public double getLiftSpeed()
    {
        return liftJaguar.get();
    }
    
    
    /*
     * PID Interfaces
     */
    public void enableCamPID()
    {
        liftMUX = LIFTMUX_CAM;
    }
    
    public int getLiftMUXState()
    {
        return liftMUX;
    }
    
    public void boolean getLiftPIDError()
    {
        liftPID.getError();
    }
    
    /*
     * Arm Interface
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
    
    /*
     * Encoders
     */
    public double getLiftEncoder()
    {
        return liftEncoder.get();
    }

    public double getArmEncoder()
    {
        return armEncoder.get();
    }

    public void resetLiftEncoder()
    {
        liftEncoder.reset();
    }

    public void resetArmEncoder()
    {
        armEncoder.reset();
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
    }
    public void closeClaw()
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