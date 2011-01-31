package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.util.SystemBase;

public class AutonomousSystem extends SystemBase
{
    public AutonomousSystem() { }

    public void tick()
    {
        robot.manipulatorSystem.lift.followY(robot.cameraSystem.getCenterY());
    }

    public void ScoreUberRing()
    {
        
    }
}
