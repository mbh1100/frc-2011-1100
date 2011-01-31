package edu.arhs.first1100.opctl;

import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.Joystick.ButtonType;

public class OperatorSystem extends SystemBase
{
    AdvJoystick leftJoystick;
    AdvJoystick rightJoystick;
    ButtonBox buttonBox;


    public OperatorSystem() { }

    public void tick()
    {
        robot.manipulatorSystem.setLiftSpeed(leftJoystick.getY());
        
        //robot.driveSystem.setDriveSpeed(leftJoystick.getY(), rightJoystick.getY());
    }
}
