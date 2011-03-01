package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.opctl.OperatorSystem;
/**
 *our line following routine
 * @author team1100
 */
public class FollowLineRoutine extends Routine
{
    private static int sleepTime = 100;
    private int path = 0;
    private double turn = 0.0;

    private final double TURN_DELAY = 0.6;
    private final double TURN_RATE = 0.2;
    private final double TURN_LIMIT = 0.3;

    private LineSystem ls;
    private DriveSystem ds;
    
    /**
     *the start of the public line routine
     * @param robot
     * @param sleep
     * @param startingPosition
     */
    public FollowLineRoutine(int path)
    {
        super(sleepTime);
        this.path = path;
        ls = LineSystem.getInstance();
        ds = DriveSystem.getInstance();
    }
    
    /**
     *
     * @param robot
     * @param sleep
     */
    public FollowLineRoutine()
    {
        super(sleepTime);
        ls = LineSystem.getInstance();
        ds = DriveSystem.getInstance();
    }
    
    /**
     *
     */
    public void tick()
    {
        switch(ls.getState())
        {
            case LineSystem.STATE_ALL:
                ds.setDriveSpeed(0, 0);
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
                path = (OperatorSystem.getInstance().rightJoystick.getRawButton(11))? -1:1;
                if(path == 1)
                    ds.setDriveSpeed(0.65, 0.35);
                else if(path == -1)
                    ds.setDriveSpeed(0.35, 0.65);
                Timer.delay(TURN_DELAY);
                turn = 0.0;
                break;
        }
        log("log robot state, turn: " + turn);
        ds.drive(0.3, -turn);
    }
}
