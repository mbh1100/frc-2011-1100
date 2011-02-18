/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.PIDSource;

public class YPIDSource implements PIDSource
{
    CameraSystem cs;

    public double pidGet()
    {
        return cs.getCenterY();
    }
}
