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
        log("Starting thread");
        
        stopThread = false;
        
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
        log("Stopping thread");
        stopThread = true;
    }
    
    /**
     * Run the thread.  Called by start(), do not call directly!
     * If you need to have code run at the initialization of the thread,
     * Put it in the constructor or override the rest() method
     */
    public void run()
    {
        log("run() called");
        
        while(true)
        {
            while(!stopThread)
            {
                /*
                try
                {*/
                    tick(); // User code
                /*}
                catch(Exception e)
                {
                    log("********************************");
                    log("  Fatal Thread Error!");
                    log(e.getMessage());
                    log(e.toString());

                    log("********************************");
                    robot.disabled();
                }*/
                
                Timer.delay( ((double)(sleepTime))/1000 );
            }

            log("Stopping thread");
            
            while(stopThread)
            {
                Timer.delay(0.1);
            }
            /*
            log("Starting to wait");
            selfWait();
            */
        log("run is looping again");
        }
    }
    
    /**
     * Makes the component wait
     * 
     */
    public synchronized void selfWait()
    {
        log("Waiting!");
        try
        {
            wait();
        }
        catch(InterruptedException e)
        {
            log("Wait error!");
        }
    }
    
    /**
     *
     */
    public synchronized void selfNotify()
    {
        log("Notify!");
        notify();
    }
    
    /**
     * Put your own code here to run.
     *
     * This method is called after every sleep cycle.
     * the delay time is stored in 'sleepTime'.
     */
    public void tick()
    {
        //log("Tick!");
    }
    
    public void setRobotMain(RobotMain r)
    {
        robot = r;
    }
    
    /**
     * Prints out messages to the console
     * @param String message
     */
    public void log(String message)
    {
        /*
        int lastDot = 0        String name = this.getClass().getName();
        
        for(int n = 0; n<name.length(); n++){
            if (name.charAt(n) == '.'){
                lastDot = n+1;
            }
        }
        name = name.substring(lastDot);
        */
        
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

    public void reset()
    { }
}
