/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;
import edu.wpi.first.wpilibj.PIDOutput;
/**
 *declares the pid curve
 * @author team1100
 */
public class CurvePIDWrite implements PIDOutput
{
    ArcadeDriveMux adm;
/**
 * declares the paramiter for ardade drivemux
 * @param adm
 */
    public CurvePIDWrite(ArcadeDriveMux adm)
    {
        this.adm = adm;
    }
/**
 *declares the curve
 * @param curve
 */
    public void pidWrite(double curve)
    {
        // System.out.println("CurvePIDWrite pidWrist() - "+curve);
        adm.setCurve(curve);
    }

}
