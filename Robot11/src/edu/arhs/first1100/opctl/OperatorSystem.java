package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.autoctl.TargetPegRoutine;
import edu.arhs.first1100.autoctl.ScoreRoutine;
import edu.arhs.first1100.autoctl.FollowLineRoutine;

/**
 *how the operator will drive the robot
 * @author team1100
 */
public class OperatorSystem extends SystemBase
{
    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.

    public boolean xboxLeftBumperLastState = false;
    public boolean xboxRightBumperLastState = false;
    
    //private ButtonBox buttonBox;
    private GamepieceIndicator ledIndicator;  //indicates the gamepiece that the human. 
    // These are supposed to start as null
    private TargetPegRoutine targetRoutine = null;
    private ScoreRoutine scoreRoutine = null;
    private FollowLineRoutine lineRoutine = null;
    
   /**
    *what controllers will drive the robot
    * @param robot
    * @param sleepTime
    */
    public OperatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        
        leftJoystick  = new AdvJoystick(1);
        rightJoystick = new AdvJoystick(2);
        xboxJoystick  = new XboxJoystick(3);
        
        ledIndicator  = new GamepieceIndicator();
        ledIndicator.start();
    }
    
    /**
     * Tick
     */
    public void tick()
    {
        /*
         * Drive and line routines
         */
        if(leftJoystick.getRawButton(11) && lineRoutine == null)
        {
            lineRoutine = new FollowLineRoutine(robot, 100);
            lineRoutine.start();
        }
        
        if(lineRoutine == null)
        {
            doDrive();
        }
        else
        {
            if(leftJoystick.getRawButton(10))
            {
                lineRoutine.stop();
                lineRoutine = null;
            }
        }
        
        /*
         * Lift and R trigger targetting
         */
        if(xboxJoystick.getRightTrigger() > 0.5)
        {
            robot.manipulatorSystem.lift.startCamPid();
        }
        else
        {
            robot.manipulatorSystem.lift.stopCamPid();
            doStateButtons();
            doLift();
        }

        /*
         * Arm and claw wrist
         */
        doArm();
        doClawWrist();
        
        // doGPI();
        doMinibot();
        
        /*
         * Debug buttons
         * encoder reset & light buttons
         */
        robot.cameraSystem.setBrightness(
                (int)(rightJoystick.getRawAxis(3)/2 + 0.5)*100
        );
        
        if(leftJoystick.getRawButton(9))
        {
            robot.manipulatorSystem.resetEncoders();
        }
        
        if(rightJoystick.getRawButton(9))
        {
            robot.cameraSystem.light.on();
        }
        if(rightJoystick.getRawButton(8))
        {
            robot.cameraSystem.light.onForAWhile();
        }
    }
    
    /**
     * Operator controlled drive check
     */
    private void doDrive()
    {
        robot.driveSystem.setDriveSpeed(-leftJoystick.getY(), -rightJoystick.getY());
    }

    private void doLift()
    {
        log("doLift");
        if(Math.abs(xboxJoystick.getRightStickY()) > 0.25)
        {
            log("9 from outerspace");
            robot.manipulatorSystem.setLiftSpeed(xboxJoystick.getRightStickY());
        }
        else if(robot.manipulatorSystem.lift.getSpeed() != 0)
        {
            log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            robot.manipulatorSystem.setLiftSpeed(0.0);
        }
        else
        {
            log("nothing happening :( ");
        }
    }

    private void doArm()
    {
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.25)
        {
            robot.manipulatorSystem.setArmSpeed(xboxJoystick.getLeftStickY()/4);
        }
        else if(!robot.manipulatorSystem.arm.pidEnabled())
        {
            robot.manipulatorSystem.setArmHeight(robot.manipulatorSystem.arm.getEncoder());
        }
    }
    
    /**
     * Checks for Manipluator System action
     */
    private void doStateButtons()
    {
        if(xboxJoystick.getAButton())
            robot.manipulatorSystem.setState(ManipulatorSystem.STATE_BOTTOM_PEG);
        
        else if(xboxJoystick.getBButton())
            robot.manipulatorSystem.setState(ManipulatorSystem.STATE_MID_PEG);

        else if(xboxJoystick.getYButton())
            robot.manipulatorSystem.setState(ManipulatorSystem.STATE_TOP_PEG);

        else if(xboxJoystick.getXButton())
            robot.manipulatorSystem.setState(ManipulatorSystem.STATE_DEFAULT);

        if(xboxJoystick.getDpad() == 1.0)
            robot.manipulatorSystem.setState(ManipulatorSystem.STATE_FLOOR);
    }
    
    /**
     * Control GamePiece indicator lights
     */
    private void doGPI()
    {
        if(rightJoystick.getRawButton(4) || leftJoystick.getRawButton(4))
            ledIndicator.setLightColorRed();
        else if(rightJoystick.getRawButton(3) || leftJoystick.getRawButton(3))
            ledIndicator.setLightColorWhite();
        else if(rightJoystick.getRawButton(5) || leftJoystick.getRawButton(5))
            ledIndicator.setLightColorBlue();
        else if(rightJoystick.getRawButton(2) || leftJoystick.getRawButton(2))
            ledIndicator.setLightColorClear();
    }

    private void doMinibot()
    {
        if (rightJoystick.getRawButton(6))
        {
            robot.minibotSystem.setArmSpeed(leftJoystick.getStickX());
            robot.driveSystem.setDriveSpeed(0, 0);
        }
        else
        {
        robot.minibotSystem.setArmSpeed(0.0);
        }

        if (rightJoystick.getRawButton(6))
        {
            robot.minibotSystem.setDeployerSpeed(rightJoystick.getStickX());
            robot.driveSystem.setDriveSpeed(0, 0);
        }
        else
        {
        robot.minibotSystem.setDeployerSpeed(0.0);
        }
    }
    
    private void doClawWrist()
    {
        // Left Toggle Button
        if(xboxJoystick.getLeftBumper() && !xboxLeftBumperLastState)
        {
            robot.manipulatorSystem.toggleClaw();
            xboxLeftBumperLastState = true;
        }
        if(!xboxJoystick.getLeftBumper())
            xboxLeftBumperLastState = false;

        // Right Bumper Toggle
        if(xboxJoystick.getRightBumper() && !xboxRightBumperLastState)
        {
            robot.manipulatorSystem.toggleWrist();
            xboxRightBumperLastState = true;
        }
        if(!xboxJoystick.getRightBumper())
            xboxRightBumperLastState = false;
    }
}
