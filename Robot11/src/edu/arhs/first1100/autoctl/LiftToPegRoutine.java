/**
 * Moves the Lift until it reaches a certain particle (top middle
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.wpi.first.wpilibj.Timer;
import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *the routine to put pegs on the scoring board and to analysize the amount of particles that the axis camera sees.
 * @author Connor Moroney
 */
public class LiftToPegRoutine extends Routine
{
    ManipulatorSystem m;
    int positionState;

/**
 *tells the robot how long to sleep
 * @param robot
 * @param sleep
 */
    public LiftToPegRoutine(RobotMain robot, int state)
    {
        super(robot, 200);
        m = robot.manipulatorSystem;
        positionState = state;
    }

    public void start()
    {
        m.setState(positionState);
        super.start();
    }

    public void tick()
    {
        // see if it's done
        if (m.liftOnTarget())
        {
            setDone();
        }
    }

}
