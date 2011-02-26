/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import com.sun.squawk.util.MathUtils;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.AdvJaguar;

/**
 *
 * @author team1100
 */
public class DriveSystem extends SystemBase
{
    private static DriveSystem instance = null;
    
    private AdvJaguar leftJaguars;
    private AdvJaguar rightJaguars;

    public DriveSystem()
    {
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
}
