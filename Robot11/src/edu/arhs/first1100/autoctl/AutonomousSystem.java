package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.robot.RobotMain;

import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
/**
 *
 * @author team1100
 */

public class AutonomousSystem extends SystemBase
{
    static private AutonomousSystem instance;
    static private int sleepTime = 100;

    FollowLineRoutine flrt;
    DriverStationEnhancedIO dsio;
    ArmToPositionRoutine atpr;
    LiftToPositionRoutine ltpr;
    ReleaseATubeRoutine ratr;
    WristUpRoutine wur;

    public static AutonomousSystem getInstance()
    {
        if(instance == null) instance = new AutonomousSystem();
        return instance;
    }
    /**
     *
     * @param robot
     * @param sleepTime
     */
    public AutonomousSystem()
    {
        super(sleepTime);
    }
    
    /**
     *where the starting position is and how to score the uber ring
     * @param startingPosition
     */
    public void ScoreUberRing(int startingPosition, boolean rack, int column, int row)
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
        
        if(startingPosition == 0) //Middle
        {
            if(rack == false)
                new FollowLineRoutine(-1).execute();
            else
                new FollowLineRoutine(1).execute();
        }
        else
        {
            new FollowLineRoutine().execute();
        }
        
        // Position robot in front of the right column
        //new SelectColumnRoutine(robot, 100).execute();
        
        // Set the right lift height
        //new TargetPegRoutine(robot, 100).execute();
        
        // move the lift and arm out and release the gripper to score a tube
        //new ScoreRoutine(robot, 100).execute();
        
        
    }
    
    /**
     *
     */
    public void run()
    {
        int path = 0;
        double targetPegHeight = 0;
        double targetArmHeight = 0;
        flrt = new FollowLineRoutine(path);
        ltpr = new LiftToPositionRoutine(targetPegHeight);
        atpr = new ArmToPositionRoutine(targetArmHeight);
        ratr = new ReleaseATubeRoutine();
        wur = new WristUpRoutine();

        flrt.execute();
        ltpr.start();
        atpr.start();
        wur.start();

        ltpr.waitForDone();
        atpr.waitForDone();
        wur.waitForDone();

        ratr.execute();
    }
}