/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.camera.CameraSystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
/**
 *
 * @author markbh
 */
class SteerSource implements PIDSource
{
    public double pidGet()
    {
        return 0.0; //CameraSystem.getInstance().getCenterX();
    }
}

class SteerOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
        // DriveSystem.getInstance().setCurve(output);
    }
}

public class SteerPid extends PIDController
{
    static private final double P = 0.1;
    static private final double I = 0.01;
    static private final double D = 0.001;

    SteerPid()
    {
	super(P, I, D, new SteerSource(), new SteerOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
