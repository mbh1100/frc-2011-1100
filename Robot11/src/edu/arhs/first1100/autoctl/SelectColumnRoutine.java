/**
 * SelectColumnRoutine.java
 *
 * use drivesystem if needed to move robot in front of a selected column
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.robot.RobotMain;

public class SelectColumnRoutine extends Routine
{
    public SelectColumnRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }

    public void tick()
    {
        Timer.delay(3);
        super.setDone();
    }
}
