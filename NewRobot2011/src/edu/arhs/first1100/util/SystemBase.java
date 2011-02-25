package edu.arhs.first1100.util;

import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author team1100
 */
public class SystemBase extends Thread
{
    public RobotMain robot;
    public int sleepTime = 100;

    private boolean stopThread = true;
    private boolean threadStarted = false;

    /**
     * Construct the system base
     * @param robot
     * @param sleep
     */
    public SystemBase(RobotMain robot, int sleep)
    {
        setSleep(sleep);
        setRobotMain(robot);
    }

    /**
     *
     * @param delay
     */
    public SystemBase(int delay)
    {
        setSleep(delay);
    }

    /**
     * Start the thread.
     */
    public void start()
    {
        stopThread = false;

        if(!threadStarted)
        {
            super.start();
            threadStarted = true;
        }
    }

    /**
     * Stop the thread.
     */
    public void stop()
    {
        stopThread = true;
    }

    /**
     * Run the thread.  Called by start(), do not call directly!
     * If you need to have code run at the initialization of the thread,
     * Put it in the constructor or override the rest() method
     */
    public void run()
    {
        while(true)
        {
            while(!stopThread)
            {
                tick();

                Timer.delay( ((double)(sleepTime))/1000 );
            }

            while(stopThread)
            {
                Timer.delay(0.1);
            }
        }
    }

    /**
     * Makes the component wait
     *
     */
    public synchronized void selfWait()
    {
        try
        {
            wait();
        }
        catch(InterruptedException e)
        { }
    }

    /**
     *
     */
    public synchronized void selfNotify()
    {
        notify();
    }

    /**
     * Put your own code here to run.
     *
     * This method is called after every sleep cycle.
     * the delay time is stored in 'sleepTime'.
     */
    public void tick()
    { }

    public void setRobotMain(RobotMain r)
    {
        robot = r;
    }
    
    /**
     * Set the amount of time that the system should sleep
     * @param int time How long the component should sleep in milliseconds
     */
    public void setSleep(int time)
    {
        sleepTime = time;
    }

    public boolean isStopped()
    {
        return stopThread;
    }
}
