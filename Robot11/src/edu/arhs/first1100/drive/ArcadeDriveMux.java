/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;
/**
 *
 * @author team1100
 */
public class ArcadeDriveMux
{
    DriveSystem ds;
/**
 *
 */
    public double power;
    public double curve;
/**
 *
 * @param ds
 */
    public ArcadeDriveMux(DriveSystem ds)
    {
        this.ds = ds;
    }
/**
 *
 * @param curve
 */
    public void setCurve(double curve)
    {
        this.curve = curve;
        cameraDrive();
    }
/**
 *
 * @param power
 */
    public void setPower(double power)
    {
        this.power = power;
        cameraDrive();
    }
/**
 *
 */
    public void cameraDrive()
    {
        //ds.drive(power,curve);
    }
}
