package edu.arhs.first1100.util;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

public class LineTracker extends SimpleRobot
{
    final int MIDDLE = 0;
    final int RIGHT = -1;
    final int LEFT = 1;

    DigitalInput ltLeft;
    DigitalInput ltMiddle;
    DigitalInput ltRight;
    DigitalInput ltBack;

    RobotDrive rdPrimary;
    RobotDrive rdSecondary;
    double lineStatus = MIDDLE;

    Joystick leftStick;
    Joystick rightStick;

    public void robotInit()
    {
        ltLeft = new DigitalInput(1);
        ltMiddle = new DigitalInput(2);
        ltRight = new DigitalInput(3);
        ltBack = new DigitalInput(4);
        rdPrimary = new RobotDrive(1, 2);
        rdSecondary = new RobotDrive (3,4);
        leftStick = new Joystick(1);
        rightStick = new Joystick(2);
    }

    public void autonomous()
    {
       while (true)
       {
            if (ltMiddle.get() || lineStatus == MIDDLE)
            {
                rdPrimary.tankDrive(0.7, 0.7);
                System.out.println("Driving on line");
                lineStatus = MIDDLE;
            }
            if (ltLeft.get() || lineStatus == LEFT)   //this moves the robot to the right
            {
                rdPrimary.tankDrive(-0.3,0.7);
                System.out.println("Left LT on line");
                lineStatus = LEFT;
            }
            if (ltRight.get() || lineStatus == RIGHT)  //this moves the robot to the left
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
    public void operatorControl()
    {
        while (true)
        {
            if ((ltMiddle.get() && ltBack.get()) || (lineStatus == MIDDLE && ltBack.get()))
            {
               rdPrimary.tankDrive(0.7, 0.7);
               System.out.println("Driving on line");
               lineStatus = MIDDLE;
            }

            if (ltMiddle.get() && !ltBack.get() ) // this runs if the robot is not fully on the line
            {
                if ( leftStick.getTrigger() ) // if the robot is on the left of the line (based on driver)
                {
                    rdSecondary.tankDrive(0.0, -0.7);  // the robot piviots to align to the line
                }
                else if (rightStick.getTrigger()) // if the robot is on the right of the line (based on driver)
                {
                    rdSecondary.tankDrive(-0.7, 0.0);  // the robot piviots to align to the line
                }
            }
            ///----  Alex's good code ----
            /*if (ltMiddle.get() && !ltBack.get() && leftStick.getTrigger())
            {
                rdSeccondary.tankDrive(0.0, 0.7);
            }
            if (ltMiddle.get() && !ltBack.get() && rightStick.getTrigger())
            {
                rdSeccondary.tankDrive(0.0, -0.7);
            }*/
            if (ltLeft.get() || lineStatus == LEFT)   //this moves the robot to the right
            {
                rdPrimary.tankDrive(-0.3,0.7);
                System.out.println("Left LT on line");
                lineStatus = LEFT;
            }
            if (ltRight.get() || lineStatus == RIGHT)  //this moves the robot to the left
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
