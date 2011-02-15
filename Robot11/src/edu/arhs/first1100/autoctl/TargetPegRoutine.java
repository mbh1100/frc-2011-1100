/**
 * TargetPegRoutine.java
 *
 * use the manipulator and camera to aim at a peg
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.PID;

public class TargetPegRoutine extends Routine
{
    public TargetPegRoutine(RobotMain robot, int sleep, AutonomousGoal goal)
    {
        super(robot, sleep, goal);
    }

    public void tick()
    {
        Timer.delay(3);
        setDone();
    }
}
