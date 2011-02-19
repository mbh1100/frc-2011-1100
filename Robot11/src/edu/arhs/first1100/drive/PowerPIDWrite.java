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
public class PowerPIDWrite implements PIDOutput
{
    ArcadeDriveMux adm;
/**
 *
 * @param adm
 */
    public PowerPIDWrite(ArcadeDriveMux adm)
    {
        this.adm = adm;
    }
/**
 *
 * @param power
 */
    public void pidWrite(double power)
    {
        //adm.setPower(power);
    }

}