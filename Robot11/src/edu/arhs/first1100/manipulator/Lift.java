package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Jaguar;

import edu.arhs.first1100.util.Averager;

public class Lift
{
    Averager averager;
    Jaguar liftJaguar;
    
    public Lift()
    {
        averager = new Averager(5);
        liftJaguar = new Jaguar(1);
    }
    
    public void set(double speed)
    {
        averager.feed(speed);
    }

    public void stop()
    {
        set(0.0);
    }

    public void update()
    {
        liftJaguar.set(averager.get());
    }
}
