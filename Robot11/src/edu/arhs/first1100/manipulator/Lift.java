package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.util.Averager;

public class Lift
{
    //private final int MAX_HEIGHT = lololololololol;
    
    // Config for PID controller
    // Will need to calibrate later
    private final double KP = 0.5;
    private final double KI = 0.5;
    private final double KD = 0.5;
    
    private double targetSpeed = 0.0;
    
    private Encoder encoder;

    private AdvJaguar liftJaguar;
    
    public Lift()
    {
        liftJaguar = new AdvJaguar(8);
        
        //pid = new PIDController(KP, KI, KD, PIDSource source, PIDOutput output);

    }
    
    public void setSpeed(double speed)
    {
        liftJaguar.set(speed);
    }
    
    public void stop()
    {
        liftJaguar.set(0.0);
    }
    
    public void update()
    {
        //currentHeight += encoder.getDistance();
    }
}
