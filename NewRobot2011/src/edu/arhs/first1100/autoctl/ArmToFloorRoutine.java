/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.ReleaseATubeRoutine;
import edu.arhs.first1100.autoctl.lowlevel.WristDownRoutine;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

public class ArmToFloorRoutine extends Routine
{
    public ArmToFloorRoutine()
    {
        super(100);
    }
    
    public void run()
    {
        WristDownRoutine wdr = new WristDownRoutine();
        SetManipulatorStateRoutine smr = new SetManipulatorStateRoutine(ManipulatorSystem.STATE_FLOOR);
        ReleaseATubeRoutine ratr = new ReleaseATubeRoutine();

        wdr.start();
        smr.start();
        ratr.start();
        
        wdr.waitForDone();
        smr.waitForDone();
        ratr.waitForDone();
        
        this.setDone();
    }
}