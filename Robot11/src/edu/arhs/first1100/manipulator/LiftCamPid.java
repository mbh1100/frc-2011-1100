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
        try
        {
            return CameraSystem.getInstance().getCenterY();
        }
        catch (Exception e)
        {
            System.out.println("exception in LiftCamSource(): " + e);
        }
        return 0.0;
    }
}

class LiftCamOutput implements PIDOutput
{
    public void pidWrite(double output)
    {
        try
        {
            // assuming a DriveSystem interface that incorporates the behavior of the ArcadeDriveMux
            //System.out.println("lift cam pid setting pid speed to " + -output);
            ManipulatorSystem.getInstance().lift.setPidSpeed(-output);
        }
        catch (Exception e)
        {
            System.out.println("exception in pidWrite" + e);
        }
    }
}

public class LiftCamPid extends PIDController
{
    static private final double P = 2.0;
    static private final double I = 0.00;
    static private final double D = 0.00;

    LiftCamPid()
    {
	super(P, I, D, new LiftCamSource(), new LiftCamOutput());
    }

    // this class exposes all the methods of PIDController, such as enable(), disable(), setSetpoint(), etc.

}
