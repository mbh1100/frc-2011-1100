package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.robot.RobotMain;

/**
 *
 * @author team1100
 */

public class AutonomousSystem extends SystemBase
{
    private final int STATE_IDLE = 0;                  // Doing nothing
    //private final int STATE_SCORE_RING = 0;
    
    private int state = 0;

    private boolean targetRack = false;
    private int targetPeg = 0;
    /**
     *
     * @param robot
     * @param sleepTime
     */
    public AutonomousSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
    }
   /**
    *start the run routine and wait for the routine to finish
    * @param r
    */
    public void runRoutine(Routine r)
    {
        r.start();
        r.waitForDone();
    }
/**
 *where the starting position is and how to score the uber ring
 * @param startingPosition
 */
    public void ScoreUberRing(int startingPosition)
    {
        /*
         * starting position input:
         * 
         * when facing the rack, -1 is on the left line,
         * 0 is on the middle line, 1 is on the right line.
         * 
         * 
         * rack position:
         *    when rack is false, it refers to the left rack.
         *    when tack is true, it refers to the right rack.
         * 
         * Columns:
         *    2
         *    1
         *    0
         *
         * Rows:
         *  0 1 2
         * 
         * 
         */

        /*

        // Follow the line.  Will automaticly know which way to go down the split
        new FollowLineRoutine(robot, 100).execute();

        // Position robot in front of the right column
        new SelectColumnRoutine(robot, 100).execute();
        
        // Set the right lift height
        new TargetPegRoutine(robot, 100).execute();
        
        // move the lift and arm out and release the gripper to score a tube
        new ScoreRoutine(robot, 100).execute();
        
        */
    }
    /**
     *
     */
    public void tick()
    {
        log("tick");
    }
}