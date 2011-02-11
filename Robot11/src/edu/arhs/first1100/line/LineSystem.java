package edu.arhs.first1100.line;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;

public class LineSystem extends SystemBase
{
    final int STATE_MIDDLE = 0;
    final int STATE_RIGHT = -1;
    final int STATE_LEFT = 1;
    final int STATE_NONE = 2;
    final int K_AUTONOMOUS = 1;
    final int K_OPERATOR_CONTROL = 2;
    final int K_NO_TRACKING = 3;
    int mode = K_NO_TRACKING;
    
    int lineStatus = STATE_MIDDLE;

    boolean done = false;
    
    LineTracker lt;

    public LineSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        lt = new LineTracker();
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

    public void trackLineToGoal()
    {
        done = false;
        initAutonomous();
    }

    synchronized public void waitUntilDone()
    {
        if (!done)
        {
            try
            {
                wait();
            }
            catch (InterruptedException x) {}
        }
    }

    synchronized void imDone()
    {
        done = true;
        this.notify();
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
        /*
        log(lt.ltLeft.get()+":"+lt.ltMiddle.get()+":"+lt.ltRight.get());
        log(lt.ltBack.get()+"");
        log("");
         *
         */
    }

    private void autonomousLineTracker()
    {
        if(lt.leftline() && lt.middleLine() && lt.rightline())
        {
            robot.driveSystem.setDriveSpeed(0.0, 0.0);
            endControl();
            imDone();
        }
        else if (lt.middleLine())
        {
            robot.driveSystem.setDriveSpeed(0.3,0.3);
            //log("Driving on line");
        }
        else if(lt.leftline()) //this moves the robot to the right
        {
            robot.driveSystem.setDriveSpeed(0.1,0.5);
            //log("Left LT on line");            
        }
        else if(lt.rightline()) //this moves the robot to the left
        {
            robot.driveSystem.setDriveSpeed(0.5, 0.1);
            //log("Right LT on line");            
        }
        
            
    }

    private void operatorControlLineTracker()
    {
        if ((lt.middleLine() && lt.backLine()) || (lineStatus == STATE_MIDDLE && lt.middleLine()))
        {
           robot.driveSystem.setDriveSpeed(0.7, 0.7);
           //log("Driving on line");
           lineStatus = STATE_MIDDLE;
        }

        else if (lt.middleLine() && !lt.backLine()) // this runs if the robot is not fully on the line
        {
            /*
            if (robot.operatorSystem.getJoystick(robot.operatorSystem.LEFT).getTrigger() ) // if the robot is on the left of the line (based on driver)
            {
                robot.driveSystem.setDriveSpeed(0.0, -0.7);  // the robot piviots to align to the line
            }
            else if (robot.operatorSystem.getJoystick(robot.operatorSystem.RIGHT).getTrigger()) // if the robot is on the right of the line (based on driver)
            {
                robot.driveSystem.setDriveSpeed(-0.7, 0.0);  // the robot piviots to align to the line
            }*/
        }
        else if (lt.leftline() || lineStatus == STATE_LEFT) //this moves the robot to the right
        {
            robot.driveSystem.setDriveSpeed(-0.3,0.7);
            //log("Left LT on line");
            lineStatus = STATE_LEFT;
        }
        else if (lt.rightline() || lineStatus == STATE_RIGHT) //this moves the robot to the left
        {
            robot.driveSystem.setDriveSpeed(0.7, -0.3);
            //log("Right LT on line");
            lineStatus = STATE_RIGHT;
        }
        else
        {
           robot.driveSystem.setDriveSpeed(0.7, 0.7);
        }
    }

    public int getState() {
        return lineStatus;
    }
}
