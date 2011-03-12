/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.autoctl.lowlevel.ReleaseATubeRoutine;
import edu.arhs.first1100.autoctl.lowlevel.WristUpRoutine;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author markbh
 */
public class AutonomousRoutine extends Routine
{

    static final double MIDDLE_PEG_HEIGHT = 720;
    static final double TOP_PEG_HEIGHT = 2000;

    AutonomousRoutine() {
        super(100);
    }

    public void run()
    {
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        DriveSystem ds = DriveSystem.getInstance();
        LineSystem ls = LineSystem.getInstance();

        // tube is already grabbed, wrist is down, lift is down, arm is withdrawn

        // put the wrist up
        new WristUpRoutine().start(); // it should be done long before we care

        // calibrate the lift and arm (shouldn't take long, we should already be here)
        ms.setState(ms.STATE_DEFAULT);
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR ||
               ms.getArmMUXState() != ms.ARMMUX_OPERATOR)
        {
            Timer.delay(0.1);
        }

        // raise the lift to the middle and wait for it to get there.
        ms.setLiftHeight(MIDDLE_PEG_HEIGHT);
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }

        // enable y-axis steering and z-axis power, get near the peg.
        ds.driveByCamera();


        // stop either when the line system sees the 'T', or when the z-axis is done,
        // or if 5 seconds elapses. If we timeout, don't continue.
        int i = 0;
        while (i < 25)
        {
            ++i;
            if (ls.getLeft() && ls.getRight() && ls.getCenter())
            {
                Log.defcon1(this, "reached end of line");
                break;
            }
            if (ds.getDrivePidEnabled())
            {
                Log.defcon1(this, "drive pid reached target");
                break;
            }
        }
        if (i == 25)
        {
            Log.defcon1(this, "timeout looking for goal. Abandoning scoring attempt.");
            return;
        }

        // raise the lift to the top peg and wait for it to get there
        ms.setLiftHeight(TOP_PEG_HEIGHT);
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }

        // enable lift x-axis tracking
        ms.enableLiftCamPID();
        
        // enable arm z-axis tracking
        ms.enableArmCamPID();

        // wait for arm to stop
        while (ms.getArmMUXState() != ms.ARMMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }

        // open claw
        new ReleaseATubeRoutine().execute();

        // withdraw arm, wait until done
        ms.setArmPosition(0.0);
        while (ms.getArmMUXState() != ms.ARMMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }

        // position lift to 0, wait until done
        while (ms.getLiftMUXState() != ms.LIFTMUX_OPERATOR)
        {
            Timer.delay(0.2);
        }

        // with
        setDone();
    }

}
