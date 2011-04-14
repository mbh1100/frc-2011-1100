/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.LiftCamPIDRoutine;
import edu.arhs.first1100.autoctl.lowlevel.SteerPIDRoutine;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author team1100
 */
public class OberAutonomousRoutine extends Routine
{
    LiftRoutine lr = new LiftRoutine(1900);
    ArmToLimitSwitchRoutine ar = new ArmToLimitSwitchRoutine();
    DriveForwardWithRangeRoutine df = new DriveForwardWithRangeRoutine(35);
    ScoreRoutine sr = new ScoreRoutine();
    
    public OberAutonomousRoutine()
    {
        super(100);
    }
    
    public void run()
    {
        if(!isCancelled())lr.start();
        if(!isCancelled())ar.start();
        
        Timer.delay(2.0);
        
        if(!isCancelled())df.start();
        df.waitForDone();

        lr.waitForDone();
        ar.waitForDone();

        Timer.delay(0.2);
        
        if(!isCancelled()) sr.execute();

        DriveSystem.getInstance().setTankSpeed(-0.3, -0.3);
        Timer.delay(2);
        DriveSystem.getInstance().setTankSpeed( 0.0,  0.0);
        
        setDone();
    }
    
    public void doCancel()
    {
        lr.cancel();
        sr.cancel();
        ar.cancel();
        df.cancel();
    }
}
