package edu.arhs.first1100.util;

import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.DriverStationLCD;
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
    private boolean killed = false;
    private boolean disabled = true;

    /**
     * Construct the system base
     * @param robot
     * @param sleep
     */
    public SystemBase(int sleep)
    {
        setSleep(sleep);
    }
    
    /**
     * Start the thread.
     */
    public void start()
    {
        stopThread = false;
        disabled = false;
        
        if(!threadStarted)
        {
            super.start();
            threadStarted = true;
        }

        reset();
    }
    
    /**
     * Stop the thread.
     */
    public void stop()
    {
        stopThread = true;
    }

    public void kill()
    {
        killed = true;
    }
    
    /**
     * Run the thread.  Called by start(), do not call directly!
     * If you need to have code run at the initialization of the thread,
     * Put it in the constructor or override the rest() method
     */
    public void run()
    {
        while(!killed)
        {
            while(!stopThread)
            {
                try
                {
                    tick(); // User code
                }
                catch(Exception e)
                {
                    log("********************************");
                    log("  Fatal Thread Error!");
                    log(e.getMessage());
                    log(e.toString());

                    log("********************************");
                    stopThread = true;
                    disable();
                }
                
                Timer.delay( sleepTime/1000.0 );
            }
            
            while(stopThread && !killed)
            {
                if (!disabled)
                {
                    disable();
                    disabled = true;
                }
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
    {
        //Log.deep(this, "tick()!");
    }
    
    /**
     * Prints out messages to the console
     * @param String message
     */
    public void log(String message)
    {
        String name = this.getClass().getName();
        System.out.println(name.substring(name.lastIndexOf('.')+1)+": "+message);
    }

    public void log()
    {
        log("");
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

    public void reset()
    { }

    public void disable()
    { }
}
