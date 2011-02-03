package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;


public class AutonomousSystem extends SystemBase
{
    private final int STATE_IDLE = 0;                  // Doing nothing
    //private final int STATE_SCORE_RING = 0;

    
    private int state = 0;
    private boolean targetRack = false;
    private int targetPeg = 0;
    
    public AutonomousSystem() { }

    /*
     * Start the process of following the line, positioning the arm, and
     * scoring the ring.
     */
    
    public void ScoreUberRing(int startingPosition, boolean rack, int peg)
    {
        /*
         * starting position input:
         *
         * when facing the rack, 0 is on the left line,
         * 1 is on the middle line, 2 is on the right line.
         * 
         * 
         * rack position input:
         *
         * when rack is false, it refers to the left rack.
         * when tack is true, it refers to the right rack.
         * 
         * peg position chart:
         * 0 1 2
         * 3 4 5
         * 6 7 8
         */

    }

    public void run()
    {
        LineSystem line = robot.lineSystem;

        //robot.manipulatorSystem.lift.followY(robot.cameraSystem.getCenterY());
    }


}
