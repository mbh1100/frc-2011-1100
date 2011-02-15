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
                tick(); // User code
                Timer.delay( ((double)(sleepTime))/1000 );
            }
            
            while(stopThread)
            {
                Timer.delay(0.1);
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
