/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

public class ArmToFloorRoutine extends Routine
{


    public ArmToFloorRoutine()
    {
        super(100);
    }

    public void run()
    {
        WristDownRoutine wdr = new WristDownRoutine();
        LiftToPositionRoutine ltpr = new LiftToPositionRoutine(5);
        ArmToPositionRoutine atpr = new ArmToPositionRoutine(100);  //encoder needs to be fixed
        ReleaseATubeRoutine ratr = new ReleaseATubeRoutine();

        wdr.start();
        ltpr.start();
        atpr.start();
        ratr.start();

        wdr.waitForDone();
        ltpr.waitForDone();
        atpr.waitForDone();
        ratr.waitForDone();

        this.setDone();
    }
}
