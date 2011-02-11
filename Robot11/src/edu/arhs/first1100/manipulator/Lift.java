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

    private double targetHeight = 0.0;
    private double currentHeight = 0.0;
    
    private Encoder encoder;

    private AdvJaguar liftJaguar;
    private PIDController pid;
    
    public Lift()
    {
        //liftJaguar = new AdvJaguar(1);
        
        //pid = new PIDController(KP, KI, KD, PIDSource source, PIDOutput output);

    }
    
    public void setHeight(double height)
    {
        targetHeight = height;
    }

    public void setSpeed(double speed)
    {

    }
    
    public void stop()
    {
        //liftJaguar.set(0.0);
    }
    
    public void update()
    {
        //currentHeight += encoder.getDistance();
        //liftJaguar.update();
    }
}
