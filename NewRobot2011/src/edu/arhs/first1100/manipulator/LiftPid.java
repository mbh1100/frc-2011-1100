/*
 * Encoder -> Jaguar
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.CameraSystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * @author markbh
 */
class LiftSource implements PIDSource
{
    public double pidGet()
    {
        return ManipulatorSystem.getInstance().getLiftEncoder();
    }
}

class LiftOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
        ManipulatorSystem.getInstance().setLiftSpeed(-output);
    }
}

public class LiftPid extends PIDController
{
    static private final double P = 0.1;
    static private final double I = 0.01;
    static private final double D = 0.001;

    LiftPid()
    {
	super(P, I, D, new LiftSource(), new LiftOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
