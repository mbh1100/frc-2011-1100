package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick.AxisType;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.autoctl.TargetPegRoutine;
import edu.arhs.first1100.autoctl.ScoreRoutine;

public class OperatorSystem extends SystemBase
{
    private boolean ignoreJoysticks = false;
    
    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot
    public XboxJoystick xboxJoystick; //controls the arm and other stuff
    
    private ButtonBox buttonBox;
    private GamepieceIndicator ledIndicator; //indicates the gamepiece that the human
                                             //should give to the robot
    private boolean ignoreManipulatorCommands = false;
    
    private TargetPegRoutine targetRoutine;
    private ScoreRoutine scoreRoutine;
    
    public OperatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        
        log("Operator system constructor.");
        
        leftJoystick  = new AdvJoystick(2);
        rightJoystick = new AdvJoystick(1);
        xboxJoystick  = new XboxJoystick(3);
        
        ledIndicator  = new GamepieceIndicator();
        ledIndicator.start();

        
    }
    
    public void tick()
    {
        //log("Tick...");

        if(false)
        {
            // Non 0 based array?  sigh....
            for(int axis=1; axis<=6; ++axis)
                log("xbox axis " + axis + ": " + xboxJoystick.getRawAxis(axis));
            for(int button=1; button<=12; ++button)
                log("xbox button " + button + ": " + xboxJoystick.getRawButton(button));
            log("");
        }

        if(false)
        {
            log("Left  Joy:" + leftJoystick.getY());
            log("Right Joy:" + rightJoystick.getY());
            log("");
        }
        
        // Robot drive.  DriveSystem handles when and how to use the input.  We
        // just keep pumping in data.

        if(xboxJoystick.getAButton() && !ignoreManipulatorCommands)
        {
            
        }

        if(!ignoreJoysticks)
        {
            robot.driveSystem.setDriveSpeed(-leftJoystick.getY(), -rightJoystick.getY());
            robot.driveSystem.setSideSpeed(rightJoystick.getX());

            if(!ignoreManipulatorCommands)
            {
                robot.manipulatorSystem.lift.setSpeed(xboxJoystick.getLeftStickY());
            }
            else
            {
                if(xboxJoystick.getLeftBumper())
                {
                    
                    ignoreManipulatorCommands = false;
                }
            }
            
            /*
            // quo-quo-quo-quo-CHEET *transformers sound effect*
            if(rightJoystick.getRawButton(11))
                robot.driveSystem.setDriveModeSideStep();
            else if(rightJoystick.getRawButton(10))
                robot.driveSystem.setDriveModeTank();
            */

            // Lift control
            //robot.manipulatorSystem.lift.setSpeed(xboxJoystick.getTrigger());
        }
        
        // Gamepiece indicator control
        if(xboxJoystick.getXButton())
            ledIndicator.setLightColorRed();

        else if(xboxJoystick.getYButton())
            ledIndicator.setLightColorWhite();

        else if(xboxJoystick.getBButton())
            ledIndicator.setLightColorBlue();
        
        else
            ledIndicator.setLightColorClear();
        
    }
}