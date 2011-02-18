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

    final private double P = .5;
    final private double I = .05;
    final private double D = .005;

    PIDOutput curve;

    public void trackCamera()
    {
        source = new XPIDSource();
        pid = new PID( P, I, D, source, curve);
    }
} 
