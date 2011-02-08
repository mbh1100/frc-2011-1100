package edu.arhs.first1100.util;

import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;

public class SystemBase extends Thread
{
    public RobotMain robot;
    public int sleepTime = 100;
    private boolean stopThread = true;
    
    private boolean threadStarted = false;
    
    public SystemBase() { }

    public SystemBase(int delay)
    {
        setSleep(delay);
    }
    
    public void start()
    {
        init(); // User code
        stopThread = false;
        
        if(!threadStarted)
        {
            super.start();
            threadStarted = true;
        }
        else
        {
            interrupt();
        }
    }

    public void stop()
    {
        stopThread = true;
    }
    
    public void run()
    {
        log("run() called");
        while(true)
        {
            while(!stopThread)
            {
                try
                {
                    tick(); // User code
                    sleep(sleepTime);
                }
                catch(InterruptedException e)
                {
                    log("Interrupted tick: " + e.getMessage());
                }
            }
            
            while(stopThread)
            {
                try
                {
                    sleep(5);
                }
                catch(InterruptedException e)
                {
                    log("Interrupted sleep: " + e.getMessage());
                }
            }
        
        log("run is looping again");
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
        log("tick not overridden");
    }

    /**
     * Put your own code here to run.
     *
     * This method is called after the thread starts
     */
    public void init()
    {
        log("startThread not overridden");
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
        int lastDot = 0;
        String name = this.getClass().getName();
        
        for(int n = 0; n<name.length(); n++){
            if (name.charAt(n) == '.'){
                lastDot = n+1;
            }
        }
        name = name.substring(lastDot);
        System.out.println(name+": "+message);
    }

    /**
     * Set the amount of time that the system should sleep
     * @param int time How long the component should sleep in milliseconds
     */
    public void setSleep(int time)
    {
        sleepTime = time;
    }
}