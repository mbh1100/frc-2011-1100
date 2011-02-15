package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.SystemBase;

public class Routine extends SystemBase
{
    public boolean done = false;
    AutonomousGoal goal;
    
    public Routine(RobotMain robot, int sleep, AutonomousGoal goal)
    {
        super(robot, sleep);
        this.goal = goal;
    }

    public synchronized void waitForDone()
    {
        try
        {
            if(!done)
                wait();
        }
        catch(InterruptedException e)
        {
            log("Error: "+e.getMessage());
        }
    }
    
    public synchronized void setDone()
    {
        done = true;
        stop();
        notify();
    }

    public synchronized void execute()
    {
        start();
        waitForDone();
    }
}