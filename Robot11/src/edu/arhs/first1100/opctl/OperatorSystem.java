package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick.AxisType;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.autoctl.AutonomousGoal;
import edu.arhs.first1100.autoctl.TargetPegRoutine;
import edu.arhs.first1100.autoctl.ScoreRoutine;
import edu.arhs.first1100.autoctl.FollowLineRoutine;

import edu.wpi.first.wpilibj.Joystick.ButtonType;
/**
 *
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
    *
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
     *
     */
    public void tick()
    {
        //log("Tick");
        
        /*
         * Start routine buttons
         */
        
        if(leftJoystick.getRawButton(11) && lineRoutine == null)
        {
            // middle line, go to left, bottom left peg. Get actual
            // target from controller
            lineRoutine = new FollowLineRoutine(robot, 100);
            lineRoutine.start();
        }
        if(xboxJoystick.getAButton() && targetRoutine == null)
        {
            // middle line, go to left, bottom left peg. Get actual
            // target from controller
            targetRoutine = new TargetPegRoutine(robot, 100, 100.0);
            targetRoutine.start();
        }       
        
        /*
         * Driving
         */
        if(lineRoutine == null)
        {
            robot.driveSystem.setDriveSpeed(-leftJoystick.getY(), -rightJoystick.getY());
            //robot.driveSystem.setSideSpeed(rightJoystick.getX()); // commented out by Alex 2-17-11 to try to get the robot to drive
            //robot.driveSystem.testCameraDrive(-rightJoystick.getY());
            
            /*
            if(rightJoystick.getRawButton(11))
                robot.driveSystem.setDriveModeSideStep();
            else if(rightJoystick.getRawButton(10))
                robot.driveSystem.setDriveModeTank();
             */
        }
        else
        {
            if(leftJoystick.getRawButton(10))
            {
                lineRoutine.stop();
                lineRoutine = null;
            }
        }
        
        if(rightJoystick.getRawButton(9))
        {
            robot.cameraSystem.light.on();
        }

        if(rightJoystick.getRawButton(8))
        {
            robot.cameraSystem.light.onForAWhile();
        }

        if(leftJoystick.getRawButton(9))
        {
            robot.manipulatorSystem.resetEncoders();
        }
        
        /*
         * Lift control
         *
        if(targetRoutine == null)
        {
            
        }
        else
        {
            if(xboxJoystick.getLeftBumper() || targetRoutine.isDone())
            {
                targetRoutine.stop();
                targetRoutine = null;
            }
        }*/
        
        /*
         * Arm control
         */
        
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
        
        if(xboxJoystick.getRightTrigger() > 0.5)
        {
            log("Starting cam auto");
            robot.manipulatorSystem.lift.startCamPid();
        }
        else
        {
            log("Stopping cam auto");
            robot.manipulatorSystem.lift.stopCamPid();
            
            if(Math.abs(xboxJoystick.getRightStickY())>.25)
            {
                robot.manipulatorSystem.setLiftSpeed(-xboxJoystick.getRightStickY());
            }
            else if(robot.manipulatorSystem.lift.getSpeed() != 0.0)
            {
                robot.manipulatorSystem.setLiftSpeed(0.0);
            }
        }

        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.25)
        {
            robot.manipulatorSystem.setArmSpeed(xboxJoystick.getLeftStickY()/4);
        }
        else
        {
            if(!robot.manipulatorSystem.arm.pidEnabled())
            {
                robot.manipulatorSystem.setArmHeight(robot.manipulatorSystem.arm.getEncoder());
            }
        }
        
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
        
        /**
         * Gamepiece indicator controller of love
         */
        if(rightJoystick.getRawButton(4) || leftJoystick.getRawButton(4))
            ledIndicator.setLightColorRed();

        else if(rightJoystick.getRawButton(3) || leftJoystick.getRawButton(3))
            ledIndicator.setLightColorWhite();

        else if(rightJoystick.getRawButton(5) || leftJoystick.getRawButton(5))
            ledIndicator.setLightColorBlue();
        
        else if(rightJoystick.getRawButton(2) || leftJoystick.getRawButton(2))
            ledIndicator.setLightColorClear();
        
        
        /*
         * Minibot control
         */
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
}
