/**
 * SelectColumnRoutine.java
 *
 * use drivesystem if needed to move robot in front of a selected column
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.robot.RobotMain;
/**
 *how to select which column to use.
 * @author team1100
 */
public class SelectColumnRoutine extends Routine
{
    private int startingColumn = 0;
    private int targetColumn = 0;
    
    /**
     *declares which starting column to use
     * @param robot
     * @param sleep
     * @param startingColumn
     * @param targetColumn
     */
    public SelectColumnRoutine(RobotMain robot, int sleep, int startingColumn, int targetColumn)
    {
        super(robot, sleep);
        this.startingColumn = startingColumn;
        this.targetColumn = targetColumn;
    }
    
    /**
     *how to decide whether or not it is done
     */
    public void tick()
    {
        Timer.delay(3);
        super.setDone();
    }
}
