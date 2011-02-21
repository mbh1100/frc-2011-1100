package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.line.LineSystem;
/**
 *our line following routine
 * @author team1100
 */
public class FollowLineRoutine extends Routine
{
    private int path = 0;
    private double turn = 0.0;

    private final double TURN_DELAY = 0.6;
    private final double TURN_RATE = 0.05;
    private final double TURN_LIMIT = 0.5;
    
    /**
     *the start of the public line routine
     * @param robot
     * @param sleep
     * @param startingPosition
     */
    public FollowLineRoutine(RobotMain robot, int sleep, int startingPosition)
    {
        super(robot, sleep);
        path = startingPosition;
    }

    /**
     *
     * @param robot
     * @param sleep
     */
    public FollowLineRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
        path = 2;
    }

    /**
     *
     */
    public void tick()
    {
        switch(robot.lineSystem.getState())
        {
            case LineSystem.STATE_ALL:
                setDone();
                break;
                
            case LineSystem.STATE_MIDDLE:
                if(turn>0.0)
                    turn -= TURN_RATE;
                else if(turn<0.0)
                    turn -= TURN_RATE;
                break;
                
            case LineSystem.STATE_LEFT://Move robot left
                if(turn > -TURN_LIMIT)
                    turn -= TURN_RATE;
                break;

            case LineSystem.STATE_RIGHT://Move robot right
                if(turn < TURN_LIMIT)
                    turn += TURN_RATE;
                break;
                
            case LineSystem.STATE_SPLIT:
                if(path == 1)
                    robot.driveSystem.setDriveSpeed(0.5, 0.0);
                else if(path == -1)
                    robot.driveSystem.setDriveSpeed(0.0, 0.5);
                Timer.delay(TURN_DELAY);
                turn = 0.0;
                break;
        }

        robot.driveSystem.drive(0.5, turn);
    }
}
