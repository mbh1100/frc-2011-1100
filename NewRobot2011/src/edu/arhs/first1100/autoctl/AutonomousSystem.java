package edu.arhs.first1100.autoctl;

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
        System.out.println("Autonomous tick");
    }
}
