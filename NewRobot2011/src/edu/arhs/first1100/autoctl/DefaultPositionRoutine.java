/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.WristUpRoutine;

/**
 *
 * @author team1100
 */
public class DefaultPositionRoutine extends Routine
{
    public DefaultPositionRoutine()
    {
        super(100);
    }
    
    public void run()
    {
        WristUpRoutine wdr = new WristUpRoutine();
        LiftToPositionRoutine ltpr = new LiftToPositionRoutine(5);
        ArmToPositionRoutine atpr = new ArmToPositionRoutine(5);

        wdr.start();
        ltpr.start();
        atpr.start();
        
        wdr.waitForDone();
        ltpr.waitForDone();
        atpr.waitForDone();
        
        this.setDone();
    }
}
