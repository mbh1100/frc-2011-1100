   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.PIDSource;
/**
 *gets the x axis pid
 * @author team1100
 */
public class XPIDSource implements PIDSource
{
    CameraSystem cs;
/**
 *returns the instance of the x pid when it finds center.
 * @return
 */
    public double pidGet()
    {
        return cs.getCenterX();
    }
}
