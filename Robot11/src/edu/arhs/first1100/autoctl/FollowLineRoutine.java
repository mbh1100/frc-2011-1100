package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.robot.RobotMain;

public class FollowLineRoutine extends Routine
{
    private int path = 0;
    private final double TURN_DELAY = 0.6;
    
    public FollowLineRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }

    /**
     *
     * @param robot
     * @param sleep
     * @param path Set which way down the path to go.  0 none, -1 left, 1 right.
     */
    public FollowLineRoutine(RobotMain robot, int sleep, int path)
    {
        super(robot, sleep);
        this.path = path;
    }
    
    public void tick()
    {
        if(robot.lineSystem.getState() == robot.lineSystem.STATE_ALL)
        {
            setDone();
        }
        else if (robot.lineSystem.getState() == robot.lineSystem.STATE_MIDDLE)
        {
            robot.driveSystem.setDriveSpeed(0.3,0.3);
            //log("Driving on line");
        }
        else if(robot.lineSystem.getState() == robot.lineSystem.STATE_LEFT) //this moves the robot to the right
        {
            robot.driveSystem.setDriveSpeed(0.1,0.5);
            //log("Left LT on line");
        }
        else if(robot.lineSystem.getState() == robot.lineSystem.STATE_RIGHT) //this moves the robot to the left
        {
            robot.driveSystem.setDriveSpeed(0.5, 0.1);
            //log("Right LT on line");
        }
        else if(path != 0 && robot.lineSystem.getState() == robot.lineSystem.STATE_SPLIT)
        {
            if(path == 1)
                robot.driveSystem.setDriveSpeed(0.5, 0.0);
            else if(path == -1)
                robot.driveSystem.setDriveSpeed(0.0, 0.5);

            Timer.delay(TURN_DELAY);
        }

    }
}
