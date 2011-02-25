package edu.arhs.first1100.autoctl;

/**
 *
 * @author team1100
 */
public class AutonomousSystem
{
    private static AutonomousSystem instance;

    public AutonomousSystem(int sleepTime)
    {
        if(instance != null)
            instance = new AutonomousSystem(sleepTime);
    }
    
    public static AutonomousSystem getInstance()
    {
        return instance;
    }

    public void doSomething()
    {
        System.out.println("Something");
    }
}
