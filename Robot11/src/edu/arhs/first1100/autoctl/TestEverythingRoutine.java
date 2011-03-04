/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

/**
 *
 * @author team1100
 */
public class TestEverythingRoutine extends Routine
{
    Routine cancelMe;

    boolean waiting = false;
    Double monitor = new Double(42); // random object for wait/notify monitor

    public TestEverythingRoutine()
    {
        super(1000);
    }

    public void advance()
    {
        synchronized (monitor)
        {
            if (waiting)
            {
                monitor.notify();
                waiting = false;
            }
        }
    }
    
    public void waitForAdvance()
    {
        synchronized  (monitor)
        {
            try
            {
                waiting = true;
                monitor.wait();
            }
            catch (Exception e)
            { log("monitor exception: " + e);}
        }
    }

    public void run()
    {
        //testOpenGrip();
        testCloseGrip();
        //testWristUp();
        //testArmUp();
        testLiftTracking();
        testWristDown();
        setDone();
        log("complete");
    }

    private void testLiftTracking()
    {
        if (!isCancelled())
        {
            log("press trigger to run testLiftTracking");
            waitForAdvance();
        }
        ManipulatorSystem.getInstance().lift.startCamPid();
        log("press trigger to stop lift tracking");
        waitForAdvance();
        ManipulatorSystem.getInstance().lift.stop();
    }
    private void testArmUp()
    {
        if (!isCancelled())
        {
            log("press trigger to run ArmToPositionRoutine(0)");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new ArmToPositionRoutine(0);
            cancelMe.execute();
        }

    }
    private void testWristDown()
    {
        if (!isCancelled())
        {
            log("press trigger to run WristDownRoutine");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new WristDownRoutine();
            cancelMe.execute();
        }
    }

    private void testWristUp()
    {
        if (!isCancelled())
        {
            log("press trigger to run WristUpRoutine");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new WristUpRoutine();
            cancelMe.execute();
        }
    }

    private void testCloseGrip()
    {
        if (!isCancelled())
        {
            log("press trigger to run GrabATubeRoutine");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new GrabATubeRoutine();
            cancelMe.execute();
        }
    }

    private void testOpenGrip()
    {
        if (!isCancelled())
        {
            log("press trigger to run ReleaseATubeRoutine");
            waitForAdvance();
        }
        if (!isCancelled())
        {
            cancelMe = new ReleaseATubeRoutine();
            cancelMe.execute();
        }
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
