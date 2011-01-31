package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.util.SystemBase;

public class ManipulatorSystem extends SystemBase
{
    public Arm arm;
    public Claw claw;
    public Lift lift;

    public ManipulatorSystem() { }

    public void setLiftSpeed(double speed)
    {
        lift.set(speed);
    }
    
    public void tick()
    {
        //
    }
}