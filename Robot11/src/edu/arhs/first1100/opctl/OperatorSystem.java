package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;

import edu.wpi.first.wpilibj.Joystick.AxisType;

public class OperatorSystem extends SystemBase
{
    public AdvJoystick leftJoystick; //controls the left side of the robot
    public AdvJoystick rightJoystick;//controls the right side of the robot

    AdvJoystick xboxGamepad;//controls the arm and other stuff
    
    ButtonBox buttonBox;
    GamepieceIndicator ledIndicator;//indicates the gamepiece that the human
                                    //should give to the robot

    public OperatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        
        log("Operator system constructor.");
        
        leftJoystick  = new AdvJoystick(2);
        rightJoystick = new AdvJoystick(1);
        xboxGamepad   = new AdvJoystick(3);
        
        ledIndicator  = new GamepieceIndicator();
    }
    
    public void tick()
    {
        //og("Tick...");

        if(false)
        {
            // Non 0 based array?  sigh....
            for(int axis=1; axis<=6; ++axis)
                log("xbox axis " + axis + ": " + xboxGamepad.getRawAxis(axis));
            for(int button=1; button<=12; ++button)
                log("xbox button " + button + ": " + xboxGamepad.getRawButton(button));
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
        
        robot.driveSystem.setDriveSpeed(-leftJoystick.getY(), -rightJoystick.getY());
        robot.driveSystem.setSideSpeed(rightJoystick.getX());
        
        /*
        // quo-quo-quo-quo-CHEET *transformers sound effect*
        if(rightJoystick.getRawButton(11))
            robot.driveSystem.setDriveModeSideStep();
        else if(rightJoystick.getRawButton(10))
            robot.driveSystem.setDriveModeTank();
        */
        
        // Lift control
        // robot.manipulatorSystem.setLiftSpeed(leftJoystick.getY());

        // Gamepiece indicator control
        if (leftJoystick.getRawButton(4))
        {
            //log("button 4");
            ledIndicator.setLightColorRed();
        }
        else if (leftJoystick.getRawButton(5))
        {
            //log("button 5");
            ledIndicator.setLightColorWhite();
        }
        else if (leftJoystick.getRawButton(3))
        {
            //log("button 3");
            ledIndicator.setLightColorBlue();
        }
        else if (leftJoystick.getRawButton(2))
        {
            //log("button 2");
            ledIndicator.setLightColorClear();
        }
    }
}
