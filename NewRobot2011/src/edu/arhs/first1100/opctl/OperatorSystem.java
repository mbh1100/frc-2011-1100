package edu.arhs.first1100.opctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.util.SystemBase;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.minibot.MinibotSystem;

public class OperatorSystem extends SystemBase
{
    private static OperatorSystem instance = null;
    
    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.
    
    private boolean xboxLeftBumperLastState = false;
    private boolean xboxRightBumperLastState = false;

    private boolean stopLift = false;
    private boolean stopArm = false;

    public ButtonBox buttonBox;
    
    private OperatorSystem()
    {
        leftJoystick = new AdvJoystick(1);
        rightJoystick = new AdvJoystick(2);
        
        xboxJoystick = new XboxJoystick(3);

        buttonBox = new ButtonBox();
    }
    
    public static OperatorSystem getInstance()
    {
        if(instance == null) instance = new OperatorSystem();
        return instance;
    }
    
    public void tick()
    {
        /*
         * !!!! A NOTE ON JOYSTICK INVERSION !!!!
         *
         * All systems will take positive values to go forward, negative to go back
         * ex. left wheels: 1.0 forward, -1.0 backward
         *     lift: 1.0 for up, -1.0 for down
         *
         * The driver joysticks are inverted here because they return -1.0 when
         * pushed forward.
         *
         * The xbox does not need inversion because when an axis is pushed
         * forward, it returns 1.0.
         *
         * IF YOU NEED TO INVERT A MOTOR, DO SO IN THE SYSTEM IT IS DECLARED,
         * DON'T INVERT THE JOYSTICK
         * 
         */

        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        MinibotSystem minis = MinibotSystem.getInstance();
        
        if(xboxJoystick.getStartButton())
        {
            processMinibotControls();
        }
        else
        {
            minis.setArmSpeed(0.0);
            minis.setBeltSpeed(0.0);
            
            if(xboxJoystick.getRightTrigger() > 0.5)
            {
                Log.defcon1(this, "Using LiftCamPID");
                ms.enableLiftCamPID();
                stopLift = true;
            }
            else
            {
                processLiftControls();
            }
            processArmControls();
        }

        processDriveControls();
        
        /*
         * Reset joysticks
         */
        if (leftJoystick.getRawButton(8))
            leftJoystick.reset();
        
        if (rightJoystick.getRawButton(8))
            rightJoystick.reset();
    }

    public void processMinibotControls()
    {
        /*
         * Minibot controls
         */
        MinibotSystem minis = MinibotSystem.getInstance();
        if(xboxJoystick.getBackButton())
        {
            minis.setBeltSpeed(0.3);
        }
        else
        {
            if(Math.abs(xboxJoystick.getLeftStickY()) > 0.20)
                if(xboxJoystick.getLeftStickY()>0)
                    minis.setArmSpeed(xboxJoystick.getLeftStickY()/5);
                else
                    minis.setArmSpeed(xboxJoystick.getLeftStickY()/2);
            else
                minis.setArmSpeed(0.0);

            minis.setBeltSpeed(-0.2);
        }
    }

    public void processLiftControls()
    {
        /*
         * Lift controls
         */
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        if(Math.abs(xboxJoystick.getRightStickY()) > 0.20)
        {
            Log.defcon1(this, "Setting lift speed " + xboxJoystick.getRightStickY()/2);
            ms.setLiftSpeed(xboxJoystick.getRightStickY()/2);

            ms.stopLiftPIDs();

            // Will make sure lift won't drift when joystick re-enters
            // deadband
            stopLift = true;
        }
        else if(stopLift)
        {
            Log.defcon1(this, "Stopping lift within deadband");
            stopLift = false;
            ms.stopLiftPIDs();
            ms.setLiftSpeed(0.0);
        }
        else
        {
            if(xboxJoystick.getXButton())
            {
                Log.defcon1(this, "Setting state to default");
                ms.setState(ManipulatorSystem.STATE_DEFAULT);
            }
            else if(xboxJoystick.getAButton())
            {
                Log.defcon1(this, "Setting state to bottom");
                ms.setState(ManipulatorSystem.STATE_BOTTOM_PEG);
            }
            else if(xboxJoystick.getBButton())
            {
                Log.defcon1(this, "Setting state to middle");
                ms.setState(ManipulatorSystem.STATE_MIDDLE_PEG);
            }
            else if(xboxJoystick.getYButton())
            {
                Log.defcon1(this, "Setting state to top");
                ms.setState(ManipulatorSystem.STATE_TOP_PEG);
            }
            else if(xboxJoystick.getDpad() == 1.0)
            {
                Log.defcon1(this, "Setting state to floor");
                ms.setState(ManipulatorSystem.STATE_FLOOR);
            }
        }
    }

    public void processArmControls()
    {
        /*
         * Arm control
         */
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.20)
        {
            ms.stopArmPIDs();
            if(xboxJoystick.getLeftStickY() > 0.0)
            {
                Log.defcon1(this, "Setting arm speed" + xboxJoystick.getLeftStickY()/4);
                ms.setArmSpeed(xboxJoystick.getLeftStickY()/4);
            }
            else //Lower than 0
            {
                Log.defcon1(this, "Setting arm speed" + xboxJoystick.getLeftStickY()/2);
                ms.setArmSpeed(xboxJoystick.getLeftStickY()/2);
            }

            stopArm = true;
        }
        else if(stopArm)
        {
            Log.defcon1(this, "Stopping arm within deadband");
            ms.setArmSpeed(0.0);
            stopArm = false;
        }
    }

    public void processGripperWristControls()
    {
        /*
         * Gripper and wrist
         */
        if(xboxJoystick.getLeftBumper() && !xboxLeftBumperLastState)
        {
            ManipulatorSystem.getInstance().toggleClaw();
            xboxLeftBumperLastState = true;
        }
        if(!xboxJoystick.getLeftBumper())
            xboxLeftBumperLastState = false;

        if(xboxJoystick.getRightBumper() && !xboxRightBumperLastState)
        {
            ManipulatorSystem.getInstance().toggleWrist();
            xboxRightBumperLastState = true;
        }
        if(!xboxJoystick.getRightBumper())
            xboxRightBumperLastState = false;
    }

    public void processDriveControls()
    {
        /*
         * Drive Controls
         *
         * Trim:
         *   Each joystick has a trim axis below buttons 8-9 with a range of 0-1
         *   The drive output is multiplied by this value when setTankSpeed is called.
         *   To avoid accidental moving, the trim will only work if the axis is over 75 percent.
         */
        double leftSpeed = -leftJoystick.getStickY()   * Math.max(-((leftJoystick.getZ() /2)-0.5), 0.75);
        double rightSpeed = -rightJoystick.getStickY() * Math.max(-((rightJoystick.getZ()/2)-0.5), 0.75);

        DriveSystem.getInstance().setTankSpeed(leftSpeed, rightSpeed);

        Log.defcon1(this, "SETTING TANK SPEED WITH TRIM:\n"+
                          leftSpeed+"\n"+
                          rightSpeed);
    }
    
    public void start()
    {
        Log.defcon2(this, "Resetting joystick centers...");
        super.start();
        
        rightJoystick.reset();
        leftJoystick.reset();
    }
}