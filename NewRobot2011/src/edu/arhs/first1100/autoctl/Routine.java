
package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;

public class Routine extends SystemBase
{
    private boolean done = false;

    public Routine(int sleep)
    {
        super();
        setSleep(sleep);
    }

    public boolean isDone()
    {
        return done;
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