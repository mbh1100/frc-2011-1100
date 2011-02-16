/**
 * TargetPegRoutine.java
 *
 * use the manipulator and camera to aim at a peg
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.arhs.first1100.robot.RobotMain;

public class TargetPegRoutine extends Routine
{
    double targetHeight = 0;
    
    public TargetPegRoutine(RobotMain robot, int sleep, double height)
    {
        super(robot, sleep);
        robot.manipulatorSystem.setLiftHeight(height);
        //robot.manipulatorSystem.waitUntilDone();
        //robot.manipulatorSystem.trackWithCamera();
        //robot.manipulatorSystem.waitUntilDone();

        log("launching TargetPegRoutine to height " + height);
    }

    public TargetPegRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
        targetHeight = /*Get current lift height*/ 0;
    }
    
    public void tick()
    {
        if(robot.manipulatorSystem.PIDOnTarget())
            setDone();
    }
}
