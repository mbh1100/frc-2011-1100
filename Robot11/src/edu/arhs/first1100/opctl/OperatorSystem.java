package edu.arhs.first1100.opctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.util.SystemBase;

public class OperatorSystem extends SystemBase
{
    AdvJoystick leftJoystick;
    AdvJoystick rightJoystick;
    ButtonBox buttonBox;
    GamepieceIndicator ledIndicator;

    public OperatorSystem()
    {
        super(200);

        leftJoystick = new AdvJoystick(2);
        rightJoystick = new AdvJoystick(1);
        
        ledIndicator = new GamepieceIndicator();
    }

    public void tick()
    {
        /*
        // Robot drive.  DriveSystem handles when and how to use the input.  We
        // just keep pumping in data.
        robot.driveSystem.setDriveSpeed(leftJoystick.getY(), rightJoystick.getY());
        robot.driveSystem.setSideSpeed(rightJoystick.getX());
        */

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
            log("button 4");
            ledIndicator.setLightColorRed();
        }
        else if (leftJoystick.getRawButton(5))
        {
            log("button 5");
            ledIndicator.setLightColorWhite();
        }
        else if (leftJoystick.getRawButton(3))
        {
            log("button 3");
            ledIndicator.setLightColorBlue();
        }
        else if (leftJoystick.getRawButton(2))
        {
            log("button 2");
            ledIndicator.setLightColorClear();
        }
        
        
        
    }
}
