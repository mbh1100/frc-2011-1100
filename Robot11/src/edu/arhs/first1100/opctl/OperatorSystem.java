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
    public AdvJoystick leftJoystick;  //controls the left side of the robot. There is love.
    public AdvJoystick rightJoystick; //controls the right side of the robot. I love lurve you.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.
    
    //private ButtonBox buttonBox;
    private GamepieceIndicator ledIndicator;  //indicates the gamepiece that the human. I love you.
                                              //should give to the robot I really do
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
        log("Tick");
        
        /*
         * Start routine buttons
         */
        
        /*
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
        */

        log("Compressor:"+robot.compressor.getPressureSwitchValue());
       
        
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
        }/*
        else
        {
            if(leftJoystick.getRawButton(10))
            {
                lineRoutine.stop();
                lineRoutine = null;
            }
        }*/
        
        /*
         * Lift control
         */
        if(targetRoutine == null)
        {
            robot.manipulatorSystem.setLiftSpeed(-xboxJoystick.getRightStickY());
        }/*
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
        if(true)//scoreRoutine == null)
        {
            robot.manipulatorSystem.setClawState( xboxJoystick.getLeftBumper() );
            robot.manipulatorSystem.setWristState( xboxJoystick.getRightBumper() );
            robot.manipulatorSystem.setArmSpeed( -xboxJoystick.getLeftStickY()/4 );
            
            /*
            if(xboxJoystick.getAButton())
                robot.manipulatorSystem.setState(ManipulatorSystem.STATE_BOTTOM_PEG);

            if(xboxJoystick.getBButton())
                robot.manipulatorSystem.setState(ManipulatorSystem.STATE_MID_PEG);

            if(xboxJoystick.getYButton())
                robot.manipulatorSystem.setState(ManipulatorSystem.STATE_TOP_PEG);

            if(xboxJoystick.getXButton())
                robot.manipulatorSystem.setState(ManipulatorSystem.STATE_FEEDER);
            */
        }
        else
        {
            /*
            if(xboxJoystick.getLeftBumper())
            {
                scoreRoutine.stop();
                scoreRoutine = null;
            }*/
        }

        /*
        // Gamepiece indicator controller of love
        if(xboxJoystick.getXButton())
            ledIndicator.setLightColorRed();

        else if(xboxJoystick.getYButton())
            ledIndicator.setLightColorWhite();

        else if(xboxJoystick.getBButton())
            ledIndicator.setLightColorBlue();
        
        else
            ledIndicator.setLightColorClear();
        */
        
        /*
         * Minibot control
         */
        if (xboxJoystick.getBackButton())
        {
            robot.minibotSystem.down();
        }
        if (xboxJoystick.getStartButton())
        {
            robot.minibotSystem.deploy();
        }
    }
}
