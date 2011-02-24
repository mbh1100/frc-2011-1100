/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.camera.XPIDSource;
//import edu.wpi.first.wpilibj.PIDOutput;
/**
 *how the bot will turn in camera drive
 * @author team1100
 */
public class CameraDriveCurve
{
    PIDController pid;
    XPIDSource source;
    ArcadeDriveMux output;
    CameraSystem cs;

    final private double P = 1.0;
    final private double I = .000;
    final private double D = .0000;

    PIDOutput curve;
/**
 *how the motors output will react
 * @param adm
 */
    public CameraDriveCurve (ArcadeDriveMux adm, CameraSystem cs)
    {
        this.cs = cs;
        output = adm;
        curve = new CurvePIDWrite(adm);
        source = new XPIDSource(cs);
        pid = new PIDController( P, I, D, source, curve);
        pid.setOutputRange(-0.2, 0.2);
    }

    /**
     *enables camera drive
     */

    public void trackCamera()
    {
        if (pid.isEnable())
        {
            return;
        }
        else
        {
            pid.enable();
        }
    }
    /**
     * turns off camera drive
     */
    public void stopTrackCamera()
    {
        //System.out.println("CDC: stopTrackCamera() - PID Disabled");
        pid.disable();
    }
} 
