/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

/**
 *
 * @author team1100
 */
public class DefaultPositionRoutine extends Routine
{
    WristUpRoutine wdr = new WristUpRoutine();
    LiftToPositionRoutine ltpr = new LiftToPositionRoutine(5);
    ArmToPositionRoutine atpr = new ArmToPositionRoutine(5);  //encoder needs to be fixed

    public DefaultPositionRoutine()
    {
        super(100);
    }

    public void run()
    {
        wdr.start();
        ltpr.start();
        atpr.start();

        wdr.waitForDone();
        ltpr.waitForDone();
        atpr.waitForDone();

        this.setDone();
    }

    protected void doCancel()
    {
        wdr.cancel();
        ltpr.cancel();
        atpr.start();
    }
}
