package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;

public class Lift
{
    private final double KP = 0.5;
    private final double KI = 0.5;
    private final double KD = 0.5;
    
    AdvJaguar liftJaguar;
    PIDController pid;

    public Lift()
    {
        liftJaguar = new AdvJaguar(1);
        //pid = new PIDController(KP, KI, KD,)
    }

    public void followY(double ycord)
    {
        // Get the pid to output to jag
    }
    
    public void set(double speed)
    {
        liftJaguar.set(speed);
    }
    
    public void stop()
    {
        set(0.0);
    }
}