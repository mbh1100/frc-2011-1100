/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.camera;

import com.sun.squawk.debugger.Log;
import edu.wpi.first.wpilibj.PIDSource;
/**
 *
 * @author team1100
 */
public class ZPIDSource implements PIDSource
{
    CameraSystem cs;
/**
 *
 * @return
 */
    public double pidGet()
    {

        if (cs.pRep.length == 0)
        {
            return 0;
        }

        else
        {
            System.out.println("z-axis return:" + cs.pRep[0].particleArea);
            return cs.pRep[0].particleArea;
        }
    }
}

