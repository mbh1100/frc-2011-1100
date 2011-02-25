/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.AdvJaguar;

/**
 *
 * @author team1100
 */
public class DriveSystem extends SystemBase
{
    private static DriveSystem instance = null;

    private AdvJaguar leftJaguar1;
    private AdvJaguar rightJaguar1;
    private AdvJaguar leftJaguar2;
    private AdvJaguar rightJaguar2;
    
    public DriveSystem()
    {
        
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
        
    }
    
    public void setArcadeSpeed(double speed, double dir)
    {
        
    }
}
