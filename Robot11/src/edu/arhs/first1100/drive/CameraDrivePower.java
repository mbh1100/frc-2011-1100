/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.drive;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.camera.ZPIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
/**
 *declare the power of the robot drive in camera operation
 * @author team1100
 */
public class CameraDrivePower
{
    PID pid;
    ZPIDSource source;
    ArcadeDriveMux output;
    CameraSystem cs;

    final private double P = 1.0;
    final private double I = .000;
    final private double D = .0000;

    PIDOutput power;
/**
 *declares output of the motors
 * @param adm
 */
    public CameraDrivePower(ArcadeDriveMux adm, CameraSystem cs, double target)
    {
        this.cs = cs;
        output = adm;
        power = new PowerPIDWrite(adm);
        source = new ZPIDSource();
        pid = new PID( P, I, D, source, power);
        pid.setOutputRange(-0.2, 0.2);
        pid.setSetpoint(target);
    }

    /**
     *enables the camera
     */
    public void trackCamera()
    {
        // System.out.println("CDP: trackCamera() - PID Enabled");
        pid.enable();
    }
    
    /**
     *disables the camera
     */
    public void stopTrackCamera()
    {
        //System.out.println("CDP: stopTrackCamera() - PID Disabled");
        pid.disable();
    }
}