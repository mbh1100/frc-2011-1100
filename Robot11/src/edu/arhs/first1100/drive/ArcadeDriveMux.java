/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

public class ArcadeDriveMux
{
    DriveSystem ds;

    public double power;
    public double curve;

    public ArcadeDriveMux(DriveSystem ds)
    {
        this.ds = ds;
    }

    public void setCurve(double curve)
    {
        this.curve = curve;
        cameraDrive();
    }

    public void setPower(double power)
    {
        this.power = power;
        cameraDrive();
    }

    public void cameraDrive()
    {
        ds.drive(power,curve);
    }
}
