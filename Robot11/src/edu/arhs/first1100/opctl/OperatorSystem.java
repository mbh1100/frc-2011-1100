package edu.arhs.first1100.opctl;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.Timer;
import edu.arhs.first1100.test.Ligihts;

public class OperatorSystem extends SystemBase
{
    AdvJoystick leftJoystick;
    AdvJoystick rightJoystick;
    ButtonBox buttonBox;
    Ligihts stuffers;   //bad variable by raymond

    public OperatorSystem()
    {
        super(200);
        leftJoystick = new AdvJoystick(2);
        rightJoystick = new AdvJoystick(1);
        stuffers = new Ligihts();
    }

    public void tick()
    {
            if (leftJoystick.getRawButton(3))
            {
                log("button 3");
                stuffers.setLigits(Ligihts.Color.Blue);
            }
            if (leftJoystick.getRawButton(4))
            {
                log("button 4");
                stuffers.setLigits (Ligihts.Color.Red);
            }
            if (leftJoystick.getRawButton(5))
            {
                log("button 5");
                stuffers.setLigits (Ligihts.Color.White);
            }

        // robot.manipulatorSystem.setLiftSpeed(leftJoystick.getY());
        
        //robot.driveSystem.setDriveSpeed(leftJoystick.getY(), rightJoystick.getY());
    }
}
