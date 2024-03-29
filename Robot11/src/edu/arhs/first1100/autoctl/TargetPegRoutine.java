/**
 * TargetPegRoutine.java
 *
 * use the manipulator and camera to aim at a peg
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.arhs.first1100.robot.RobotMain;
/**
 *how the robot is to find the peg to score
 * @author team1100
 */
public class TargetPegRoutine extends Routine
{
    /**
     *
     */
    double targetHeight = 0;
    int cam_resX;
    int cam_resY;
    public TargetPegRoutine(int sleep, double height)
    {
        super(sleep);
        //robot.manipulatorSystem.setLiftPosition(height);
        //robot.manipulatorSystem.waitUntilDone();
        //robot.manipulatorSystem.trackWithCamera();
        //robot.manipulatorSystem.waitUntilDone();
        //cam_resX = AxisCamera.getInstance().getResolution().width;
        //cam_resY = AxisCamera.getInstance().getResolution().height;


        log("launching TargetPegRoutine to height " + height);
    }
/**
 *how the robot is to get the target hight
 * @param robot
 * @param sleep
 */
    public TargetPegRoutine(RobotMain robot, int sleep)
    {
        super(sleep);
        targetHeight = /*Get current lift height*/ 0;
    }
   /**
    *what to do when done
    */
    public void tick()
    {
        if(ManipulatorSystem.getInstance().armOnTarget())
            setDone();

    }

    protected void doCancel()
    {
        kill();
        setDone();
    }
}
