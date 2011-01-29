package edu.arhs.first1100.opctl;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

public class LineTracker extends SimpleRobot
{
    DigitalInput linetracker;
    DriverStation ds;

    public void robotInit()
    {
        ds = DriverStation.getInstance();
        linetracker = new DigitalInput(1);
    }

    public void autonomous()
    {
        while(true)
        {
            if (linetracker.get())
            {
                System.out.println("true");
            }
            else
            {
                System.out.println("false");
            }
        }
    }
}