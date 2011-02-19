/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.PIDSource;
/**
 *
 * @author team1100
 */
public class YPIDSource implements PIDSource
{
    CameraSystem cs;
/**
 *
 * @return
 */
    public double pidGet()
    {
        return cs.getCenterY();
    }
}
