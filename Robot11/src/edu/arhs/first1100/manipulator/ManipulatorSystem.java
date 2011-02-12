package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;

public class ManipulatorSystem extends SystemBase
{
    public Arm arm;
    public Claw claw;
    public Lift lift;

    public ManipulatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
    }
    
    public void tick()
    {
        lift.update();
        claw.update();
        arm.update();
    }
}