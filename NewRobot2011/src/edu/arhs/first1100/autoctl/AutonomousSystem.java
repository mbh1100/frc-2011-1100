package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.util.SystemBase;

/**
 *
 * @author team1100
 */
public class AutonomousSystem extends SystemBase
{
    private static AutonomousSystem instance = null;
    
    public AutonomousSystem() { }
    
    public static AutonomousSystem getInstance()
    {
        if(instance == null) instance = new AutonomousSystem();
        return instance;
    }

    public void tick()
    {
        
    }

    public void scoreUberTube(boolean rack, int colum, int row)
    {
        
    }

    public void followLine()
    { followLine(false); }

    public void followLine(boolean splitDir)
    {
        LineSystem ln = LineSystem.getInstance();
        DriveSystem ds = DriveSystem.getInstance();
        
        ln.followLine(splitDir);
        ln.getInstance();
    }
}
