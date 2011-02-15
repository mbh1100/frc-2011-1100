package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.line.LineSystem;

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
        //read from LineSystem
        int lsR  = robot.lineSystem.getState();
        switch(lsR)
        {
            case LineSystem.STATE_ALL:
                setDone();
                break;
            case LineSystem.STATE_MIDDLE:
                robot.driveSystem.setDriveSpeed(0.3,0.3);
                break;
            case LineSystem.STATE_LEFT://Move robot left
                robot.driveSystem.setDriveSpeed(0.1,0.5);
                break;
            case LineSystem.STATE_RIGHT://Move robot right
                robot.driveSystem.setDriveSpeed(0.5, 0.1);
                break;
            case LineSystem.STATE_SPLIT:
                if(path == 1)
                    robot.driveSystem.setDriveSpeed(0.5, 0.0);
                else if(path == -1)
                    robot.driveSystem.setDriveSpeed(0.0, 0.5);
            Timer.delay(TURN_DELAY);
        }
    }
}
