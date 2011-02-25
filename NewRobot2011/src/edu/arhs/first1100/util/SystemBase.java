package edu.arhs.first1100.util;

import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author team1100
 */
public class SystemBase extends Thread
{
    protected int sleepTime = 100;

    private boolean stopThread = true;
    private boolean threadStarted = false;

    public SystemBase()
    { }

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
     *
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
                Timer.delay(sleepTime/1000.0);
            }

            while(stopThread)
            {
                Timer.delay(0.1);
            }
        }
    }

    /**
     * Put your own code here to run.
     *
     * This method is called after every sleep cycle.
     * the delay time is stored in 'sleepTime'.
     */
    public void tick()
    { }
    
    /**
     * Set the amount of time that the system should sleep
     * @param int time How long the component should sleep in milliseconds
     */
    public void setSleep(int time)
    {
        sleepTime = time;
    }

    public int getSleep()
    {
        return sleepTime;
    }
}
