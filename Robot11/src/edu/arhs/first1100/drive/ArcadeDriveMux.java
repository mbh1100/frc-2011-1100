/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;


/**
 *declares how the robot will drive
 * @author team1100
 */
public class ArcadeDriveMux
{
    DriveSystem ds;
    
    /**
     *how the robot will turn
     */
    public double power;
    public double curve;

    /**
     *declares the drive system
     * @param ds
     */
    public ArcadeDriveMux(DriveSystem ds)
    {
        this.ds = ds;
    }

    /**
     *sets the curve
     * @param curve
     */
    public void setCurve(double curve)
    {
        System.out.println("ArcadeDriveMux running setCurve    BAD!");
        this.curve = curve;
       // cameraDrive();
    }

    /**
     *sets the power
     * @param power
     */
    public void setPower(double power)
    {
        System.out.println("ArcadeDriveMux running setPower    BAD!");
        this.power = power;
        //cameraDrive();
    }

    /**
     *sets camera drive
     */
    private void cameraDrive()
    {
        System.out.println("ADM  camDrive CALLED!");
        /*
        System.out.println("ArcadeDriveMux: PID Power: " + power);
        System.out.println("ArcadeDriveMux: PID Curve: " + curve);
        System.out.println("");
        */
       //ds.drive(power, -curve);
    }
}
