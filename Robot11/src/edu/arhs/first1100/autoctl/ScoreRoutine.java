/**
 * ScoreRoutine.java
 *
 * use manipulator system to reach out and score on a peg.
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.robot.RobotMain;

public class ScoreRoutine extends Routine
{
    public ScoreRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }

    public void tick()
    {
        //read camera, position lift
        Timer.delay(3);
        super.setDone();
    }
}
