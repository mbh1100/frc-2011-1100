package edu.arhs.first1100.opctl;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.robot.RobotMain;

public class OperatorSystem extends SystemBase
{
    AdvJoystick leftJoystick;
    AdvJoystick rightJoystick;
    ButtonBox buttonBox;

    public OperatorSystem()
    {
        leftJoystick = new AdvJoystick(2);
        rightJoystick = new AdvJoystick(1);
    }

    public void run()
    {
        
    }

    public void tick()
    {
        robot.manipulatorSystem.setLiftSpeed(leftJoystick.getY());
        
        //robot.driveSystem.setDriveSpeed(leftJoystick.getY(), rightJoystick.getY());
    }
}
