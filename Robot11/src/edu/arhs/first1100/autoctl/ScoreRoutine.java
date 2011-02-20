/**
 * ScoreRoutine.java
 *
 * use manipulator system to reach out and score on a peg.
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.robot.RobotMain;
/**
 *how to score
 * @author team1100
 */
public class ScoreRoutine extends Routine
{
    /**
     *how long the robot should sleep
     * @param robot
     * @param sleep
     */
    public ScoreRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }
/**
 *how often the robot should read the tick
 */
    public void tick()
    {
        //read camera, position lift
        Timer.delay(3);
        super.setDone();
    }
}
