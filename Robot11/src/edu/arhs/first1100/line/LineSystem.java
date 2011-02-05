package edu.arhs.first1100.line;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

public class LineSystem extends SimpleRobot
{
    final int MIDDLE = 0;
    final int RIGHT = -1;
    final int LEFT = 1;
    final int kAutonomous = 1;
    final int kOperatorControl = 2;
    final int kNoTracking = 3;
    int mode = 3;

    RobotDrive rdPrimary;
    RobotDrive rdSecondary;
    double lineStatus = MIDDLE;

    Joystick leftStick;
    Joystick rightStick;

    LineTracker lt;

    public void robotInit()
    {
        rdPrimary = new RobotDrive(1, 2);
        rdSecondary = new RobotDrive (3,4);
        leftStick = new Joystick(1);
        rightStick = new Joystick(2);
    }

    public void beginAutonomous()
    {
        mode = kAutonomous;
    }

    public void beginOperatorControl()
    {
        mode = kOperatorControl;
    }

    public void endControl()
    {
        mode = kNoTracking;
    }

    public void tick()
    {
        switch (mode)
        {
            case kAutonomous:
                autonomousLineTracker();
                break;
            case kOperatorControl:
                operatorControlLineTracker();
                break;
            case kNoTracking:
                break;
        }
    }

    public void autonomousLineTracker()
    {
       {
            if (lt.middleLine() || lineStatus == MIDDLE)
            {
                rdPrimary.tankDrive(0.7, 0.7);
                System.out.println("Driving on line");
                lineStatus = MIDDLE;
            }
            if (lt.leftline() || lineStatus == LEFT)   //this moves the robot to the right
            {
                rdPrimary.tankDrive(-0.3,0.7);
                System.out.println("Left LT on line");
                lineStatus = LEFT;
            }
            if (lt.rightline() || lineStatus == RIGHT)  //this moves the robot to the left
            {
                rdPrimary.tankDrive(0.7, -0.3);
                System.out.println("Right LT on line");
                lineStatus = RIGHT;
            }
            Timer.delay(.25);
        }
    }
        //used to test if the LTs are working
        /*while(true)
        {
            if (ltLeft.get() && ltMiddle.get())
            {
                System.out.println("true");
            }
            else
            {
                System.out.println("false");
            }
        }*/
    public void operatorControlLineTracker()
    {
        while (true)
        {
            rdPrimary.tankDrive(0.7, 0.7);

            Timer.delay(.25);

            if ((lt.middleLine() && lt.backLine()) || (lineStatus == MIDDLE && lt.middleLine()))
            {
               rdPrimary.tankDrive(0.7, 0.7);
               System.out.println("Driving on line");
               lineStatus = MIDDLE;
            }

            else if (lt.middleLine() && !lt.backLine()) // this runs if the robot is not fully on the line
            {
                if (leftStick.getTrigger() ) // if the robot is on the left of the line (based on driver)
                {
                    rdSecondary.tankDrive(0.0, -0.7);  // the robot piviots to align to the line
                }
                else if (rightStick.getTrigger()) // if the robot is on the right of the line (based on driver)
                {
                    rdSecondary.tankDrive(-0.7, 0.0);  // the robot piviots to align to the line
                }
            }
            else if (lt.leftline() || lineStatus == LEFT) //this moves the robot to the right
            {
                rdPrimary.tankDrive(-0.3,0.7);
                System.out.println("Left LT on line");
                lineStatus = LEFT;
            }
            else if (lt.rightline() || lineStatus == RIGHT) //this moves the robot to the left
            {
                rdPrimary.tankDrive(0.7, -0.3);
                System.out.println("Right LT on line");
               lineStatus = RIGHT;
            }
            else
            {
               rdPrimary.tankDrive(0.7, 0.7);
            }
            Timer.delay(.25);
        }
    }
}
