/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;
import edu.wpi.first.wpilibj.PIDOutput;

public class CurvePIDWrite implements PIDOutput
{
    CameraDriveCurve cdc;
    ArcadeDriveMux adm;

    public CurvePIDWrite(ArcadeDriveMux adm)
    {
        this.adm = adm;
    }

    public void pidWrite(double curve)
    {

    }

}
