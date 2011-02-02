package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;

public class AutonomousSystem extends SystemBase
{
    private final int STATE_IDLE = 0;                  // Doing nothing
    private final int STATE_FIND_LINE = 1;
    private final int STATE_FOLLOW_LINE = 2;
    private final int STATE_LINE_DECISION = 3;         // When is come at a line intersection
    private final int STATE_POSITION_HORIZONTALLY = 4; // transform drivetrain, position horizontally
    private final int STATE_POSITION_LIFT = 5;         // Adjust arm height
    private final int STATE_REACH_OUT_ARM = 6;         // Reaching out the arm
    private final int STATE_PLACE_RING = 7;            // Releasing the claw to place the ring on the peg
    private final int STATE_PULL_BACK_ARM = 8;         // Pulling back the arm
    
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
        
        if(state == STATE_IDLE)
        {
            targetRack = rack;
            targetPeg = peg;
            state = STATE_FOLLOW_LINE;
        }
    }

    public void tick()
    {
        if(state == STATE_FIND_LINE)
        {
            /*
            if lineSystem state == no line
                driveSystem.setDriveSpeed(0.5,0.5);
            else
                state = STATE_FOLLOW_LINE
            */
        }
        else if(state == STATE_FOLLOW_LINE)
        {
            /*
            if lineSystem state == too far left
                driveSystem.setDriveSpeed(0.6,0.4);

            else if lineSystem state == too far right
                driveSystem.setDriveSpeed(0.4,0.6);

            else if lineSystem state == just right
                driveSystem.setDriveSpeed(0.5,0.5);

            else if lineSystem state == fork in the road
                state = STATE_LINE_DECISION

            else if lineSystem state == no line
                state = STATE_POSITION_FOR_SCORING
            */
        }
        else if(state == STATE_LINE_DECISION)
        {
            if(targetRack==false)
                // move robot left a little
                state = STATE_FOLLOW_LINE;
            else if(targetRack==true)
                // move robot right a little
                state = STATE_FOLLOW_LINE;
        }







        //robot.manipulatorSystem.lift.followY(robot.cameraSystem.getCenterY());
    }    
}