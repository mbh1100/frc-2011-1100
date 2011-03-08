/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import com.sun.squawk.util.MathUtils;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.AdvJaguar;

/**
 *
 * @author team1100
 */
public class DriveSystem extends SystemBase
{
    private static DriveSystem instance = null;

    private SteerPid steerPid;
    
    private AdvJaguar leftJaguars;
    private AdvJaguar rightJaguars;

    private double curve = 0.0;
    private double power = 0.0;
    
    public DriveSystem()
    {
        steerPid = new SteerPid();
        steerPid.enable();
        
        leftJaguars  = new AdvJaguar(4, 2, 4, false);
        rightJaguars = new AdvJaguar(4, 1, 3, false);
    }
    
    public static DriveSystem getInstance()
    {
        if(instance == null) instance = new DriveSystem();
        return instance;
    }
    
    public void tick()
    {
        Log.defcon1(this, "Left:  "+leftJaguars.get());
        Log.defcon1(this, "Right: "+rightJaguars.get());
        Log.defcon1(this, "");
        
    }

    public void setTankSpeed(double leftSide, double rightSide)
    {
        leftJaguars.set(leftSide);
        rightJaguars.set(rightSide);
    }

    public void setArcadeSpeed(double outputMagnitude, double curve)
    {
        setArcadeSpeed(outputMagnitude, curve, 0.5);
    }
    
    public void setArcadeSpeed(double outputMagnitude, double curve, double sensitivity)
    {
        double leftOutput, rightOutput;
        
        if (curve < 0)
        {
            double value = MathUtils.log(-curve);
            double ratio = (value - sensitivity) / (value + sensitivity);
            if (ratio == 0)
            {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude / ratio;
            rightOutput = outputMagnitude;
        }
        else if (curve > 0)
        {
            double value = MathUtils.log(curve);
            double ratio = (value - sensitivity) / (value + sensitivity);
            if (ratio == 0)
            {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude / ratio;
        }
        else
        {
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude;
        }
        setTankSpeed(leftOutput, rightOutput);
    }
    
    public void setCurve(double curve)
    {
        Log.defcon1(this, "Setting Curve to "+curve);
        this.curve = curve;
        setArcadeSpeed(this.power, this.curve);
    }

    public void setPower(double power)
    {
        this.power = power;
        setArcadeSpeed(this.power, this.curve);
    }


}
