/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * implements the z axis pid for the camera.
 *
 * @author team1100
 */
public class ZPIDSource implements PIDSource
{
    CameraSystem cs;
    
    /**
     *gets the instance of the pid including the hight of the axis camera.
     * @return
     */
    public double pidGet()
    {

        System.out.println("Z-pid get");
        if (cs == null || cs.pRep == null || cs.pRep.length == 0 || cs.pRep[0] == null)
        {
            System.out.println("bad stuff happening to Camera System ++++++++++++++++++++++");
            return 0;
        }
        
        else
        {
            cs.getCenterX();
            System.out.println("ZPIDSource: pidGet() return:" + cs.pRep[0].particleArea);
            return cs.pRep[0].particleArea;
        }
    }
}