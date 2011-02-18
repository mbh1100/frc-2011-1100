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

    public SystemBase(RobotMain robot, int sleep)
    {
        setSleep(sleep);
        setRobotMain(robot);
    }
    
    public SystemBase(int delay)
    {
        setSleep(delay);
    }
    
    public void start()
    {
        log("Starting thread");
        
        stopThread = false;
        
        if(!threadStarted)
        {
            super.start();
            threadStarted = true;
        }
        else
        {
            selfNotify();
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
                }
                catch(Exception e)
                {
                    log("********************************");
                    log("  Fatal Thread Error!");
                    log(e.getMessage());
                    log("********************************");
                    robot.disabled();
                }
                
                Timer.delay( ((double)(sleepTime))/1000 );
            }
            
            selfWait();
        
        log("run is looping again");
        }
    }
    
    public synchronized void selfWait()
    {
        try
        {
            super.wait();
        }
        catch(InterruptedException e)
        {
            log("Wait error!");
        }
    }

    public synchronized void selfNotify()
    {
        super.notify();
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
        int lastDot = 0;
        String name = this.getClass().getName();
        
        for(int n = 0; n<name.length(); n++){
            if (name.charAt(n) == '.'){
                lastDot = n+1;
            }
        }
        name = name.substring(lastDot);
        */
        
        System.out.println(this.getClass().getName().substring(20)+": "+message);
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
