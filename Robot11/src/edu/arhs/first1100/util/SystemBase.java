package edu.arhs.first1100.util;

import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.Timer;

public class SystemBase extends Thread
{
    public RobotMain robot;
    public int sleepTime = 100;
    private boolean stopThread = false;

    public SystemBase()
    {

    }

    public SystemBase(int delay)
    {
        setSleep(delay);
        log("instance");
    }
    
    public void start()
    {
        stopThread = false;
        super.start();
    }
    
    public void run()
    {
        log("in System Base");
        while (!stopThread)
        {
            try
            {
                tick();
                sleep(sleepTime);
            }
            catch (Exception e){
                log("exception: " + e.getMessage());
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
        System.out.println("tick not overridden");
    }
    
    /**
     *
     * @param delay sets the amount of time the system should sleep
     */
    
    
    public void setRobotMain(RobotMain r)
    {
        robot = r;
    }
    
    public void stop()
    {
        stopThread = true;
    }
    
    /**
     * Prints out messages to the console
     * @param String message
     */
    public void log(String message)
    {
        int lastDot = 0;
        String name = this.getName();
        System.out.println(name);
        for(int n = 0; n<name.length();n++){
            if (name.charAt(n) == '.'){
                lastDot = n;
            }
        }
        name = name.substring(lastDot);
        System.out.println(name);
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
