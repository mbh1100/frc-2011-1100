/**
 * Moves the Lift until it reaches a certain particle (top middle
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.wpi.first.wpilibj.Timer;
import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author akshay
 */
public class LiftToPegRoutine extends Routine
{
    ParticleAnalysisReport pegs;

    public LiftToPegRoutine(RobotMain robot, int sleep)
    {
        super(robot, sleep);
    }

    public void tick()
    {
        ParticleAnalysisReport pegs  = robot.cameraSystem.getParticles();
    }

}
