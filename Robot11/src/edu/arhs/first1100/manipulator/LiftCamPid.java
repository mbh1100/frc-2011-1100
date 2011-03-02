/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.CameraSystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * @author markbh
 */
class LiftCamSource implements PIDSource
{
    public double pidGet()
    {
        return CameraSystem.getInstance().getCenterY();
    }
}

class LiftCamOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
        ManipulatorSystem.getInstance().lift.setPidSpeed(output);
    }
}

public class LiftCamPid extends PIDController
{
    static private final double P = 0.1;
    static private final double I = 0.01;
    static private final double D = 0.001;

    LiftCamPid()
    {
	super(P, I, D, new LiftCamSource(), new LiftCamOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
