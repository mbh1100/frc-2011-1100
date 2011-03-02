package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.autoctl.TargetPegRoutine;
import edu.arhs.first1100.autoctl.ScoreRoutine;
import edu.arhs.first1100.autoctl.FollowLineRoutine;
import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.autoctl.TestEverythingRoutine;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.minibot.MinibotSystem;

/**
 *how the operator will drive the robot
 * @author team1100
 */
public class OperatorSystem extends SystemBase
{
    private static OperatorSystem instance;
    private static int sleepTime = 100;

    private DriverStationDataFeeder driverStation;
    
    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.

    private boolean xboxLeftBumperLastState = false;
    private boolean xboxRightBumperLastState = false;
    private boolean avgToggleLastState = false;
    private boolean leftButton10State = false;
    
    //private ButtonBox buttonBox;
    private GamepieceIndicator ledIndicator;  //indicates the gamepiece that the human.
    
    // These are supposed to start as null
    private TargetPegRoutine targetRoutine = null;
    private ScoreRoutine scoreRoutine = null;
    private FollowLineRoutine lineRoutine = null;
    private TestEverythingRoutine testRoutine = null;
    //private ManipulatorStateRoutine mStateRoutine = null;

    private ManipulatorSystem ms;
    private CameraSystem cs;
    private DriveSystem ds;
    private MinibotSystem mbot;
    
    //private PickUpRoutine pickupRoutine = null;
    public static OperatorSystem getInstance()
    {
        if(instance == null) instance = new OperatorSystem();
        return instance;
    }
    
    /**
     *what controllers will drive the robot
     * @param robot
     * @param sleepTime
     */
    public OperatorSystem()
    {
        super(sleepTime);

        driverStation = new DriverStationDataFeeder();
        driverStation.start();
        
        leftJoystick  = new AdvJoystick(1);
        rightJoystick = new AdvJoystick(2);
        xboxJoystick  = new XboxJoystick(3);
        
        ledIndicator  = new GamepieceIndicator();
        ledIndicator.start();

        ms = ManipulatorSystem.getInstance();
        cs = CameraSystem.getInstance();
        ds = DriveSystem.getInstance();
        mbot = MinibotSystem.getInstance();
    }
    
    /**
     * Tick
     */
    public void tick()
    {
        /*
         * Drive and line routines
         */
        /*
        if(leftJoystick.getRawButton(11))
        {
            if(lineRoutine == null)
            {
                lineRoutine = new FollowLineRoutine();
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
         *
         */
        if (leftJoystick.getRawButton(11))
        {
            if (testRoutine == null)
            {
                log("starting TestEverythingRoutine");
                testRoutine = new TestEverythingRoutine();
                testRoutine.start();
            }
            if (leftJoystick.getRawButton(10) && !leftButton10State)
            {
                log("advancing");
                testRoutine.advance();
                leftButton10State = true;
            }
            else if (!leftJoystick.getRawButton(10))
            {
                leftButton10State = false;
            }
        }
        else
        {
            if (testRoutine != null)
            {
                testRoutine.cancel();
                testRoutine.waitForDone();
                log("test routine cancelled!");
                testRoutine = null;
            }
            doDrive();
        }
        
        /*
         * Lift and R trigger targetting
         */
        if(xboxJoystick.getRightTrigger() > 0.5)
        {
            //log("right trigger down");
            ms.lift.startCamPid();
        }
        else
        {
            // trigger up
            if(Math.abs(xboxJoystick.getRightStickY()) > 0.25)
            {
                log("Sending: " + xboxJoystick.getRightStickY());
                ms.setLiftSpeed(xboxJoystick.getRightStickY());
            }
            else
            {
                ms.lift.stop();
            }

            /*
             * use these buttons to launch routines.
             *
            if(xboxJoystick.getAButton())
                ms.setState(ManipulatorSystem.STATE_BOTTOM_PEG);

            else if(xboxJoystick.getBButton())
                ms.setState(ManipulatorSystem.STATE_MID_PEG);

            else if(xboxJoystick.getYButton())
                ms.setState(ManipulatorSystem.STATE_TOP_PEG);

            else if(xboxJoystick.getXButton())
                ms.setState(ManipulatorSystem.STATE_DEFAULT);

            if(xboxJoystick.getDpad() == 1.0)
                ms.setState(ManipulatorSystem.STATE_FLOOR);
             * 
             */
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
        cs.setBrightness(brightness);
        
        if(leftJoystick.getRawButton(9))
        {
            ms.resetEncoders();
        }
        if(rightJoystick.getRawButton(9))
        {
            cs.light.on();
            
        }
        if(rightJoystick.getRawButton(8))
        {
            cs.light.onForAWhile();
        }

        doAVG();
    }
    
    /**
     * Operator controlled drive check
     */
    private void doDrive()
    {
        ds.setDriveSpeed(-leftJoystick.getY(), -rightJoystick.getY());
        //robot.driveSystem.testCameraDrive(-leftJoystick.getY());
    }
    
    private void doLift()
    {
        log("doLift");
        if(Math.abs(xboxJoystick.getRightStickY()) > 0.25)
        {
            log("9 from outerspace");
            ms.setLiftSpeed(xboxJoystick.getRightStickY());
        }
        else if(ms.lift.getSpeed() != 0)
        {
            log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            ms.setLiftSpeed(0.0);
        }
    }

    private void doArm()
    {
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.25)
        {
            ms.setArmSpeed(xboxJoystick.getLeftStickY()/4);
        }
        else if(!ms.arm.pidEnabled())
        {
            ms.setArmPosition(ms.arm.getEncoder());
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
            log("Minibot arm speed: " + mbot.getArmSpeed());
            
            mbot.setArmSpeed(leftJoystick.getStickX());
            mbot.setDeployerSpeed(rightJoystick.getStickX());
            
            ds.setDriveSpeed(0, 0);
        }
        else
        {
            mbot.setArmSpeed(0.0);
            mbot.setDeployerSpeed(0.0);
        }
    }
    
    private void doClawWrist()
    {
        // Left Toggle Button
        if(xboxJoystick.getLeftBumper() && !xboxLeftBumperLastState)
        {
            ms.toggleClaw();
            xboxLeftBumperLastState = true;
        }
        if(!xboxJoystick.getLeftBumper())
            xboxLeftBumperLastState = false;

        // Right Bumper Toggle
        if(xboxJoystick.getRightBumper() && !xboxRightBumperLastState)
        {
            ms.toggleWrist();
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
