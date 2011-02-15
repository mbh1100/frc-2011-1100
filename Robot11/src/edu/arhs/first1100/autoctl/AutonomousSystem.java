package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.robot.RobotMain;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.autoctl.FollowLineRoutine;

public class AutonomousSystem extends SystemBase
{
    private final int STATE_IDLE = 0;                  // Doing nothing
    //private final int STATE_SCORE_RING = 0;
    
    private int state = 0;

    private boolean targetRack = false;
    private int targetPeg = 0;
    
    public AutonomousSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
    }
    
    public void runRoutine(Routine r)
    {
        r.start();
        r.waitForDone();
    }

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
         
        //                                         startingpos,  side, column, row
        AutonomousGoal goal = new AutonomousGoal(            0, false,      1,   0);
        
        // Follow the line.  Will automaticly know which way to go down the split
        new FollowLineRoutine(robot, 100, goal).execute();

        // Position robot in front of the right column
        new SelectColumnRoutine(robot, 100, goal).execute();
        
        // Set the right lift height
        new TargetPegRoutine(robot, 100, goal).execute();
        
        // move the lift and arm out and release the gripper to score a tube
        new ScoreRoutine(robot, 100, goal).execute();
        
    }
    
    public void tick()
    {
        
    }
}