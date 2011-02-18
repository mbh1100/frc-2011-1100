/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.arhs.first1100.drive;
import edu.wpi.first.wpilibj.PIDOutput;

public class PowerPIDWrite implements PIDOutput
{
    CameraDrivePower cdp;
    ArcadeDriveMux adm;

    public PowerPIDWrite(ArcadeDriveMux adm)
    {
        this.adm = adm;
    }

    public void pidWrite(double curve)
    {

    }

}