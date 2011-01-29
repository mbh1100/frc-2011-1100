package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Jaguar;

public class Lifter
{
    Jaguar liftJaguar;
    
    public Lifter()
    {
        liftJaguar = new Jaguar(1);
    }

    public void set(double speed)
    {
        liftJaguar.set(speed);
    }
}
