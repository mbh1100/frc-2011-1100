package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.util.Averager;

public class Lift
{
    // Config for PID controller
    // Will need to calibrate later
    private final double kCAM_P = 0.5;
    private final double kCAM_I = 0.05;
    private final double kCAM_D = 0.005;
    
    private final double kLIFT_P = 0.5;
    private final double kLIFT_I = 0.05;
    private final double kLIFT_D = 0.005;
    
    private double pulseDistance = .1;

    private PID camPid;
    private PID liftPid;
    
    private Encoder encoder;
    
    private AdvJaguar liftJaguar;
    
    public Lift()
    {
        liftJaguar = new AdvJaguar(8);
        encoder = new Encoder(1,2);

        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        encoder.setDistancePerPulse(pulseDistance);
        
        camPid  = new PID(kCAM_P, kCAM_I, kCAM_D, encoder, liftJaguar);
        liftPid = new PID(kLIFT_P, kLIFT_I, kLIFT_D, null, liftJaguar);
        
        //pid = new PIDController(KP, KI, KD, PIDSource source, PIDOutput output);

    }
    
    public void setSpeed(double speed)
    {
        liftPid.disable();
        camPid.disable();
        liftJaguar.set(speed);
    }

    public void setHeight(double target)
    {
        camPid.disable();
        liftPid.setSetpoint(target);
        liftPid.enable();
    }

    public void setHeight(int point)
    {
        
    }
    
    public void stop()
    {
        camPid.disable();
        liftPid.disable();
        liftJaguar.set(0.0);
    }
    
    public void update()
    {
        liftJaguar.update();
    }
}
