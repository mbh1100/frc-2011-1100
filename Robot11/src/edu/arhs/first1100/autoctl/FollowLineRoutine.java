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
    private final double TURN_RATE = 0.2;
    private final double TURN_LIMIT = 0.3;
    
    /**
     *the start of the public line routine
     * @param robot
     * @param sleep
     * @param startingPosition
     */
    public FollowLineRoutine(RobotMain robot, int sleep, int path)
    {
        super(robot, sleep);
        this.path = path;
    }
    
    /**
     *
     * @param robot
     * @param sleep
     */
    public FollowLineRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }
    
    /**
     *
     */
    public void tick()
    {
        if(robot.lineSystem.getState() == LineSystem.STATE_ALL)
        {
            setDone();
        }
        
        if(robot.lineSystem.getState() == LineSystem.STATE_SPLIT)
        {
            if(path == -1)
            {
                robot.driveSystem.setDriveSpeed(-0.5, 0.5);
            }
            else if(path == 1)
            {
                robot.driveSystem.setDriveSpeed(-0.5, 0.5);
            }

            Timer.delay(TURN_DELAY);

            robot.driveSystem.setDriveSpeed(0.5, 0.5);

            Timer.delay(TURN_DELAY);
        }


        if(robot.lineSystem.getLeft())
        {
            robot.driveSystem.setDriveSpeed(0.6, 0.2);
        }
        else if(robot.lineSystem.getRight())
        {
            robot.driveSystem.setDriveSpeed(0.2, 0.6);
        }
        else if(robot.lineSystem.getMiddle())
        {
            robot.driveSystem.setDriveSpeed(0.45, 0.4);
        }
        else if(!robot.lineSystem.getMiddle())
        {
            robot.driveSystem.setDriveSpeed(0.4, 0.45);
        }
    }

    public void workingTick()
    {
        int s = robot.lineSystem.getState();
        log("TICK!!! :" + s);
        switch(s)
        {
            case LineSystem.STATE_LEFT:
            case LineSystem.STATE_SPLIT:
                robot.driveSystem.setDriveSpeed(.35, .65);
                log("drivin' LEFT!");
                break;
            case LineSystem.STATE_MIDDLE:
                robot.driveSystem.setDriveSpeed(.55, .55);
                log("drivin' STRAIGHT!");
                break;
            case LineSystem.STATE_RIGHT:
                robot.driveSystem.setDriveSpeed(.65, .35);
                log("drivin' RIGHT!");
                break;
            case LineSystem.STATE_ALL:
                robot.driveSystem.setDriveSpeed(0, 0);
                break;

        }
    }


    public void OldTick()
    {
        switch(robot.lineSystem.getState())
        {
            case LineSystem.STATE_ALL:
                robot.driveSystem.setDriveSpeed(0, 0);
                log("State all");
                setDone();
                break;
                
            case LineSystem.STATE_MIDDLE:
                log("State Middle");
                if(turn>0.0)
                    turn -= TURN_RATE * 2;
                else if(turn<0.0)
                    turn += TURN_RATE * 2;
                if(Math.abs(turn) <= TURN_RATE)
                    turn = 0;
                break;
                
            case LineSystem.STATE_LEFT://Move robot left
                log("State Left");
                if(turn > -TURN_LIMIT)
                    turn -= TURN_RATE;
                break;

            case LineSystem.STATE_RIGHT://Move robot right
                log("State Right");
                if(turn < TURN_LIMIT)
                    turn += TURN_RATE;
                break;
                
            case LineSystem.STATE_SPLIT:
                log("State Split");
                path = (robot.operatorSystem.rightJoystick.getRawButton(11))? -1:1;
                if(path == 1)
                    robot.driveSystem.setDriveSpeed(0.65, 0.35);
                else if(path == -1)
                    robot.driveSystem.setDriveSpeed(0.35, 0.65);
                Timer.delay(TURN_DELAY);
                turn = 0.0;
                break;
        }
        log("log robot state, turn: " + turn);
        robot.driveSystem.drive(0.3, -turn);
    }
}
