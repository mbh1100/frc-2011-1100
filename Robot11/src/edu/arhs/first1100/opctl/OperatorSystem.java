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
    private DriverStationDataFeeder driverStation;
    
    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.

    private boolean xboxLeftBumperLastState = false;
    private boolean xboxRightBumperLastState = false;
    private boolean avgToggleLastState = false;
    
    //private ButtonBox buttonBox;
    private GamepieceIndicator ledIndicator;  //indicates the gamepiece that the human.
    
    // These are supposed to start as null
    private TargetPegRoutine targetRoutine = null;
    private ScoreRoutine scoreRoutine = null;
    private FollowLineRoutine lineRoutine = null;
    //private ManipulatorStateRoutine mStateRoutine = null;
    
    //private PickUpRoutine pickupRoutine = null;
    
    /**
     *what controllers will drive the robot
     * @param robot
     * @param sleepTime
     */
    public OperatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);

        driverStation = new DriverStationDataFeeder(robot, 500);
        driverStation.start();
        
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
        if(leftJoystick.getRawButton(11))
        {
            if(lineRoutine == null)
            {
                lineRoutine = new FollowLineRoutine(robot, 50);
                lineRoutine.start();
            }
            else
            {
                if(leftJoystick.getRawButton(10))
                {
                    lineRoutine.stop();
                    lineRoutine = null;
                }
            }
        }
        else
        {
            doDrive();
        }
        
        /*
         * Lift and R trigger targetting
         */
        if(xboxJoystick.getRightTrigger() > 0.5)
        {
            //log("right trigger down");
            robot.manipulatorSystem.lift.startCamPid();
        }
        else
        {
            if(Math.abs(xboxJoystick.getRightStickY()) > 0.25)
            {
                //log("9 from outerspace");
                log("Sending: " + xboxJoystick.getRightStickY());
                robot.manipulatorSystem.setLiftSpeed(xboxJoystick.getRightStickY());
            }
            else if(robot.manipulatorSystem.lift.getSpeed() != 0)
            {
                //log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                robot.manipulatorSystem.setLiftSpeed(0.0);
            }
            //log("right trigger up");
            robot.manipulatorSystem.lift.stopCamPid();

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
        int brightness = (int)((rightJoystick.getRawAxis(3)/2 + 0.5)*100);
        //log("setting brightness: " + brightness);
        robot.cameraSystem.setBrightness(brightness);
        
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

        doAVG();
    }
    
    /**
     * Operator controlled drive check
     */
    private void doDrive()
    {
        robot.driveSystem.setDriveSpeed(-leftJoystick.getY(), -rightJoystick.getY());
        //robot.driveSystem.testCameraDrive(-leftJoystick.getY());
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
    }

    private void doArm()
    {
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.25)
        {
            robot.manipulatorSystem.setArmSpeed(xboxJoystick.getLeftStickY()/4);
        }
        else if(!robot.manipulatorSystem.arm.pidEnabled())
        {
            robot.manipulatorSystem.setArmPosition(robot.manipulatorSystem.arm.getEncoder());
        }
    }
    
    /**
     * Checks for Manipluator System action
     */
    private void doStateButtons()
    {
        
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
            log("Minibot delpoy thing: " + leftJoystick.getStickX());
            log("Minibot arm speed: " + robot.minibotSystem.getArmSpeed());
            
            robot.minibotSystem.setArmSpeed(leftJoystick.getStickX());
            robot.minibotSystem.setDeployerSpeed(rightJoystick.getStickX());
            
            robot.driveSystem.setDriveSpeed(0, 0);
        }
        else
        {
            robot.minibotSystem.setArmSpeed(0.0);
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

    /**
     * toggles the Averager
     */
    private void doAVG()
    {
        if (leftJoystick.getRawButton(6) && !avgToggleLastState)
        {
            leftJoystick.toggleAVG();
            rightJoystick.toggleAVG();
            avgToggleLastState = true;
        }
        else if (!leftJoystick.getRawButton(6)){
            avgToggleLastState = false;
        }
    }
}
