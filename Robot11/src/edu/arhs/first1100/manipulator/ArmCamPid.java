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
class ArmCamSource implements PIDSource
{
    public double pidGet()
    {
        return CameraSystem.getInstance().getBiggestParticle().particleArea;
    }
}

class ArmCamOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
        ManipulatorSystem.getInstance().arm.setPidSpeed(-output);
    }
}

public class ArmCamPid extends PIDController
{
    static private final double P = 0.1;
    static private final double I = 0.01;
    static private final double D = 0.001;

    ArmCamPid()
    {
	super(P, I, D, new ArmCamSource(), new ArmCamOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
