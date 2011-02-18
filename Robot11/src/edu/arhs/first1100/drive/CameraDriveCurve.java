/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arhs.first1100.drive;

import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.camera.XPIDSource;
import edu.wpi.first.wpilibj.PIDOutput;

public class CameraDriveCurve
{
    PID pid;
    XPIDSource source;
    ArcadeDriveMux output;

    final private double P = .1;
    final private double I = .01;
    final private double D = .001;

    PIDOutput curve;

    public CameraDriveCurve (ArcadeDriveMux adm)
    {
        output = adm;
        curve = new CurvePIDWrite(adm);
        source = new XPIDSource();
        pid = new PID( P, I, D, source, curve);
        pid.setOutputRange(-0.3, 0.3);
    }

    public void trackCamera()
    {
        pid.enable();
    }

    public void stopTrackCamera()
    {
        pid.disable();
    }
} 
