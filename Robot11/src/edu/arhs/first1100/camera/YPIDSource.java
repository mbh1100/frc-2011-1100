/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.PIDSource;
/**
 *sets the y axis pid
 * @author team1100
 */
public class YPIDSource implements PIDSource
{
    CameraSystem cs;

    public YPIDSource(CameraSystem cs)
    {
        this.cs = cs;
    }
/**
 *returns the instance of the pid when it finds center
 * @return
 */
    public double pidGet()
    {
        if(cs == null)
        {
            System.out.println("cs = null!!!!!!!!!!!!!");
            return 0.0;
        }
        System.out.println("YPIDSource: pidGet " + cs.getCenterY());
        return cs.getCenterY();
    }
}
