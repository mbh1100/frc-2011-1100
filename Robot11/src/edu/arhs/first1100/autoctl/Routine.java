package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.SystemBase;
/**
 *
 * @author team1100
 */
public abstract class Routine extends SystemBase
{
    /**
     *
     */
    private boolean done = false;
    private boolean cancelled = false;

    /**
     *says how long the robot should be asleep in the routine
     * @param robot
     * @param sleep
     */
    public Routine(int sleep)
    {
        super(sleep);
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
        if (done)
        {
            // only notify once in the event of multiple setDone calls
            return;
        }
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
    public final void cancel()
    {
        cancelled = true;
        doCancel();
    }
    public boolean isCancelled()
    {
        return cancelled;
    }
    protected abstract void doCancel();
}