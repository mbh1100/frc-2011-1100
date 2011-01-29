package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.util.SystemBase;

public class ManipulatorSystem extends SystemBase
{
    Arm arm;
    Claw claw;
    Lifter lift;

    public ManipulatorSystem() { }

    public ManipulatorSystem(double delay)
    {
        super(delay);
    }

}