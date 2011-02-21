/**
 * ScoreRoutine.java
 *
 * use manipulator system to reach out and score on a peg.
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.robot.RobotMain;

/**
 *how to score
 * @author team1100
 */
public class ScoreRoutine extends Routine
{
    DriveSystem ds;
    ManipulatorSystem ms;
    /**
     *how long the robot should sleep
     * @param robot
     * @param sleep
     */
    public ScoreRoutine(RobotMain robot)
    {
        super(robot, 1000);
        ds = robot.driveSystem;
        ms = robot.manipulatorSystem;
    }
/**
 *how often the robot should read the tick
 */
    public void run()
    {
        //read camera, position lift
        new LiftToPegRoutine(robot, ManipulatorSystem.STATE_TOP_PEG).execute();

        ms.releaseTube();
        Timer.delay(0.5);

        ms.setLiftHeight(ms.getLiftHeight() - 50);
        while (!ms.liftOnTarget())
        {
            Timer.delay(0.5);
        }

        ds.drive(-0.5, 0.0);
        Timer.delay(1);
        ds.drive(0.0, 0.0);

        super.setDone();
    }
}
