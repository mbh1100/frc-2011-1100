/*
 * ManipulatorSystem.java
 *
 * Controls the manipulator component of the robot.
 * 
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
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
    private LiftPid liftPID;
    
    private DigitalInput liftBottomLimitSwitch;
    
    private AdvJaguar armJaguar;
    private Encoder armEncoder;
    private ArmPid armPID;

    private DigitalInput armBackLimitSwitch;
    private LiftCamPid liftCamPID;

    private Solenoid claw;
    private Solenoid wrist;
    
    public ManipulatorSystem()
    {
        liftJaguar = new AdvJaguar(4, 6, true);
        
        liftEncoder = new Encoder(8, 9);
        liftEncoder.start();
        
        liftPID = new LiftPid();
        liftPID.setOutputRange(-0.2, 0.2);  // maybe it should be a bit higher like .3 ?

        liftBottomLimitSwitch = new DigitalInput(7);

        armJaguar = new AdvJaguar(4, 8, false);

        armEncoder = new Encoder(10, 11);
        armEncoder.start();
        
        armPID = new ArmPid();
        armPID.setOutputRange(-0.2, 0.2);  // maybe it should be a bit higher like .3 ?
        
        armBackLimitSwitch = new DigitalInput(12);
        
        liftCamPID = new LiftCamPid();
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
                Log.defcon2(this, "LiftMux: Op");
                if(liftPID.isEnable()) liftPID.disable();
                if(liftCamPID.isEnable()) liftCamPID.disable();
                break;
                
            case LIFTMUX_PID:
                Log.defcon2(this, "LiftMux: Pid");
                liftPID.enable();
                if(liftCamPID.isEnable()) liftCamPID.disable();
                
                if(liftPID.getError() <= 1.0)
                {
                    stopLiftPIDs();
                    Log.defcon2(this, "LiftPid: TARGET REACHED");
                    imDone();
                }
                break;

            case LIFTMUX_CAM:
                Log.defcon2(this, "LiftMux: Cam");
                if(liftPID.isEnable()) liftPID.disable();
                liftCamPID.enable();
                
                if(liftCamPID.getError() <= 1.0)
                {
                    stopLiftPIDs();
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
                Log.defcon2(this, "ArmMux: Op");
                armPID.disable();
                break;
            
            case ARMMUX_PID:
                Log.defcon2(this, "ArmMux: Pid");
                armPID.enable();
                
                if(armPID.getError() <= 1.0)
                {
                    stopArmPIDs();
                    Log.defcon2(this, "ArmPid: TARGET REACHED");
                    imDone();
                }
                break;
        }
        
        Log.defcon2(this, "Lift Encoder : "+liftEncoder.get());
        Log.defcon2(this, "Arm Encoder  : "+armEncoder.get());
        Log.defcon2(this, "");
    }

    public void reset()
    {
        stopArmPIDs();
        stopLiftPIDs();
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
        if(!liftBottomLimitSwitch.get())
        {
            Log.defcon2(this, "!!!!!!!! Bottom limit pressed !!!!!!!!!!");

            resetLiftEncoder();
            if(speed<0.0)
            {
                liftJaguar.set(0.0);
            }
        }
        liftJaguar.set(speed);
    }
    
    public void setLiftHeight(double height)
    {
        liftMUX = LIFTMUX_PID;
        liftPID.setSetpoint(height);
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
    
    public double getLiftPIDError()
    {
        return liftPID.getError();
    }

    public void stopLiftPIDs()
    {
        liftMUX = LIFTMUX_OPERATOR;
    }

    public void stopArmPIDs()
    {
        armMUX = ARMMUX_OPERATOR;
    }
    
    /*
     * Arm Interface
     */
    public void setArmSpeed(double speed)
    {
        if(!armBackLimitSwitch.get())
        {
            Log.defcon2(this, "########### Arm limit pressed ############### (#brown)");
            
            resetArmEncoder();
            if(speed < 0.0)
            {
                armJaguar.set(0.0);
            }
        }
        armJaguar.set(speed);
    }
    
    public void setArmPosition(double position)
    {
        armMUX = ARMMUX_PID;
        armPID.setSetpoint(position);
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
    { claw.set(!claw.get()); }
    
    public void openClaw()
    { claw.set(true); }

    public void closeClaw()
    { claw.set(false); }

    public boolean getClawState()
    { return claw.get(); }

    public void toggleWrist()
    { wrist.set(!wrist.get()); }

    public void wristUp()
    { wrist.set(true); }

    public void wristDown()
    { wrist.set(false); }
    
    public boolean getWristState()
    { return wrist.get(); }
}
