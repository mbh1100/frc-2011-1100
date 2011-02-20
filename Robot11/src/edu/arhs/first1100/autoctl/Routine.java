package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.SystemBase;
/**
 *
 * @author team1100
 */
public class Routine extends SystemBase
{
    /**
     *
     */
    private boolean done = false;
    /**
     *says how long the robot should be asleep in the routine
     * @param robot
     * @param sleep
     */
    public Routine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }
/**
 *says when the robot is done
 * @return
 */
    public boolean isDone()
    {
        return done;
    }
/**
 *says what to do if done is not true
 */
    public synchronized void waitForDone()
    {
        try
        {
            if(!done)
                wait();
        }
        /**
         *
         */
        catch(InterruptedException e)
        {
            log("Error: "+e.getMessage());
        }
    }
    /**
     *what to do when done = true
     */
    public synchronized void setDone()
    {
        done = true;
        stop();
        notify();
    }
/**
 *what to do if not done
 */
    public synchronized void execute()
    {
        start();
        waitForDone();
    }
}