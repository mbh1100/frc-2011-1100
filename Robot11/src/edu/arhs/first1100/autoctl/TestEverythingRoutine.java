/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

/**
 *
 * @author team1100
 */
public class TestEverythingRoutine extends Routine
{
    Routine cancelMe;

    boolean waiting = false;
    Double monitor; // random object for wait/notify monitor

    public TestEverythingRoutine()
    {
        super(1000);
    }

    public synchronized void advance()
    {
        if (waiting)
            monitor.notify();
    }
    
    public synchronized void waitForAdvance()
    {
        waiting = true;
        try
        {
            monitor.wait();
        }
        catch (Exception e)
        {}
    }

    public void run()
    {
        System.out.println("press trigger to run WristDownRoutine");
        waitForAdvance();
        if (!isCancelled())
        {
            cancelMe = new WristDownRoutine();
            cancelMe.execute();
        }
        if (!isCancelled())
        {
            System.out.println("press trigger to run WristUpRoutine");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new WristUpRoutine();
            cancelMe.execute();
        }
        if (!isCancelled())
        {
            System.out.println("press trigger to run GrabATubeRoutine");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new GrabATubeRoutine();
            cancelMe.execute();
        }
        if (!isCancelled())
        {
            cancelMe = new ReleaseATubeRoutine();
            cancelMe.execute();
        }
        this.setDone();
    }
    protected void doCancel()
    {
        if (cancelMe != null)
        {
            cancelMe.cancel();
        }
        advance();
    }
}
