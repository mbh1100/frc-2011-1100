package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.util.SystemBase;

public class ManipulatorSystem extends SystemBase
{
    Arm arm;
    Claw claw;
    Lift lift;

    public ManipulatorSystem() { }

    public void setLiftSpeed(float speed)
    {
        lift.set(speed);
    }
    
    public void run()
    {
        lift.update();
        claw.update();
        arm.update();
    }
}