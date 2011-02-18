/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.PIDSource;

public class ZPIDSource implements PIDSource
{
    CameraSystem cs;

    public double pidGet()
    {
        if (cs.pRep.length == 0)
        {
            return 0;
        }

        else
        {
            return cs.pRep[0].particleArea;
        }
    }
}

