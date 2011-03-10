/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.line;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

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

    private boolean splitDir = false;
    private boolean followLine = false;
    
    public LineSystem() { }
    
    public static LineSystem getInstance()
    {
        if(instance == null) instance = new LineSystem();
        return instance;
    }
    
    public void tick()
    {
        DriveSystem ds = DriveSystem.getInstance();
        
        if(followLine)
        {
            Log.defcon1(this, "Following the line...");
            
            // Check for all
            if(left.get() && middle.get() && right.get())
            {
                Log.defcon1(this, "    Stopping on the line!");
                ds.setTankSpeed(-0.3, -0.3);
                Timer.delay(0.2);
                ds.setTankSpeed(0, 0);
                followLine = false;
            }
            
            // Check for the split
            else if(left.get() && !middle.get() && right.get())
            {
                Log.defcon1(this, "    Splitting");
                if(splitDir)
                {
                    ds.setTankSpeed(0.5, 0.3);
                }
                else
                {
                    ds.setTankSpeed(0.3, 0.5);
                }
                Timer.delay(1);
                ds.setTankSpeed(0.5, 0.5);
                Timer.delay(1);
            }
            
            else if(middle.get() && (right.get() ||left.get()))
            {
                ds.setTankSpeed(-0.3, -0.3);
                Timer.delay(0.2);
                ds.setTankSpeed(0, 0);
                followLine = false;
            }

            // Turn robot back right
            else if(left.get())
            {
                Log.defcon1(this, "    Left side triggered, turning right...");
                ds.setTankSpeed(0.6, 0.3);
                Timer.delay(0.1);
            }

            //Turn robot back left
            else if(right.get())
            {
                Log.defcon1(this, "    Right side triggered, turning left...");
                ds.setTankSpeed(0.3, 0.6);
                Timer.delay(0.1);
            }
                      
            // Teeter on the line
            else
            {
                Log.defcon1(this, "    Teetering on the middle line:");
                if(!middle.get())
                {
                    Log.defcon1(this, "        Middle off, drifting left");
                    ds.setTankSpeed(0.45, 0.55);
                }
                else
                {
                    Log.defcon1(this, "        Middle on, drifting right");
                    ds.setTankSpeed(0.55, 0.45);
                }
            }
        }
    }
    

    public void followLine(boolean splitDir)
    {
        this.splitDir = splitDir;
        followLine = true;
    }

    public boolean isStopped()
    {
        return followLine;
    }
}