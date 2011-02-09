package edu.arhs.first1100.line;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;

public class LineSystem extends SystemBase
{
    final int MIDDLE = 0;
    final int RIGHT = -1;
    final int LEFT = 1;
    final int K_AUTONOMOUS = 1;
    final int K_OPERATOR_CONTROL = 2;
    final int K_NO_TRACKING = 3;
    int mode = 3;

    RobotDrive rdPrimary;
    RobotDrive rdSecondary;
    double lineStatus = MIDDLE;

    Joystick leftStick;
    Joystick rightStick;

    LineTracker lt;
   
    public LineSystem(RobotMain robot)
    {
        setRobotMain(robot);
        rdPrimary = new RobotDrive(1, 2);
        rdSecondary = new RobotDrive (3,4);
        leftStick = robot.operatorSystem.leftJoystick;
        rightStick = robot.operatorSystem.rightJoystick;
    }
    
    public void initAutonomous()
    {
        mode = K_AUTONOMOUS;
    }

    public void initOperatorControl()
    {
        mode = K_OPERATOR_CONTROL;
    }

    public void endControl()
    {
        mode = K_NO_TRACKING;
    }

    public void tick()
    {
        switch (mode)
        {
            case K_AUTONOMOUS:
            {
                autonomousLineTracker();
                break;
            }
            case K_OPERATOR_CONTROL:
            {
                operatorControlLineTracker();
                break;
            }
            case K_NO_TRACKING:
            {
                break;
            }
        }
    }

    public void autonomousLineTracker()
    {       
            if (lt.middleLine() || lineStatus == MIDDLE)
            {
                rdPrimary.tankDrive(0.7, 0.7);
                log("Driving on line");
                lineStatus = MIDDLE;
            }
            else if(lt.leftline() || lineStatus == LEFT) //this moves the robot to the right
            {
                robot.driveSystem.setDriveSpeed(-0.3,0.7);
                log("Left LT on line");
                lineStatus = LEFT;
            }
            else if(lt.rightline() || lineStatus == RIGHT) //this moves the robot to the left
            {
                robot.driveSystem.setDriveSpeed(0.7, -0.3);
                log("Right LT on line");
                lineStatus = RIGHT;
            }
            Timer.delay(.25);        
    }

    public void operatorControlLineTracker()
    {
        while (mode == K_OPERATOR_CONTROL)
        {
            Timer.delay(.25);
            if ((lt.middleLine() && lt.backLine()) || (lineStatus == MIDDLE && lt.middleLine()))
            {
               rdPrimary.tankDrive(0.7, 0.7);
               log("Driving on line");
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
                log("Left LT on line");
                lineStatus = LEFT;
            }
            else if (lt.rightline() || lineStatus == RIGHT) //this moves the robot to the left
            {
                rdPrimary.tankDrive(0.7, -0.3);
                log("Right LT on line");
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
