/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.line;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author team1100
 */
public class LineSystem extends SystemBase
{
    private static LineSystem instance = null;
    
    private DigitalInput left;
    private DigitalInput middle;
    private DigitalInput right;

    private boolean followLineRunning = false;
    
    public LineSystem() { }
    
    public static LineSystem getInstance()
    {
        if(instance == null) instance = new LineSystem();
        return instance;
    }

    public void tick()
    {
    }

    public void followLine(boolean splitDir)
    {
        if(!middle.get())
        {
            DriveSystem.getInstance().setTankSpeed(0.5, 0.5);
        }
        else
        {
            imDone();
        }
    }
}
