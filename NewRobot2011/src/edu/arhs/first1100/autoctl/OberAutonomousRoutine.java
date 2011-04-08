/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.LiftCamPIDRoutine;
import edu.arhs.first1100.autoctl.lowlevel.SteerPIDRoutine;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author team1100
 */
public class OberAutonomousRoutine extends Routine
{
    public OberAutonomousRoutine()
    {
        super(100);
    }
    
    public void run()
    {
        
        /*ManipulatorSystem ms = ManipulatorSystem.getInstance();
        DriveSystem ds = DriveSystem.getInstance();
        
        // Move wrist up
        ms.wristUp();
        
        // Set the manip state to where we want to be
        new SetManipulatorStateRoutine(ManipulatorSystem.STATE_TOP_PEG).execute();
        
        // Then make sure the PID fights gravity
        ms.setArmPosition(ms.getArmEncoder());

        ds.setTankSpeed(0.7, 0.7);
        Timer.delay(5);
        
        LiftCamPIDRoutine lcr = new LiftCamPIDRoutine();

        spr.start();
        lcr.start();
        
        spr.waitForDone();
        lcr.waitForDone();
         * 
         */
        
    }
}
