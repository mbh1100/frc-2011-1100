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
     *
     * @param robot
     * @param sleep
     */
    public Routine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }
/**
 *
 * @return
 */
    public boolean isDone()
    {
        return done;
    }
/**
 *
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
     *
     */
    public synchronized void setDone()
    {
        done = true;
        stop();
        notify();
    }
/**
 *
 */
    public synchronized void execute()
    {
        start();
        waitForDone();
    }
}