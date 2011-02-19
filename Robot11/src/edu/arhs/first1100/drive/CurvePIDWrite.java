/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;
import edu.wpi.first.wpilibj.PIDOutput;
/**
 *
 * @author team1100
 */
public class CurvePIDWrite implements PIDOutput
{
    ArcadeDriveMux adm;
/**
 * 
 * @param adm
 */
    public CurvePIDWrite(ArcadeDriveMux adm)
    {
        this.adm = adm;
    }
/**
 *
 * @param curve
 */
    public void pidWrite(double curve)
    {
        adm.setCurve(curve);
    }

}
