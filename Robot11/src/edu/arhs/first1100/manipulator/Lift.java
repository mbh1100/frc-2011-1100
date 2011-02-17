package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;

import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;

public class Lift
{
    // Config for PID controller
    // Will need to calibrate later
    private final double kCAM_P = 0.5;
    private final double kCAM_I = 0.05;
    private final double kCAM_D = 0.005;
    
    private final double kLIFT_P = 0.1;
    private final double kLIFT_I = 0.01;
    private final double kLIFT_D = 0.001;
    
    private double pulseDistance = .1;

    private PID camPid;
    private PID liftPid;
    
    private Encoder encoder;
    
    private Jaguar liftJaguar;
    
    public Lift()
    {
        liftJaguar = new Jaguar(6);
        encoder = new Encoder(9,8);

        encoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        encoder.setDistancePerPulse(pulseDistance);
        encoder.start();
        
        //camPid  = new PID(kCAM_P, kCAM_I, kCAM_D, null, liftJaguar);
        liftPid = new PID(kLIFT_P, kLIFT_I, kLIFT_D, encoder, liftJaguar);
        
        //pid = new PIDController(KP, KI, KD, PIDSource source, PIDOutput output);

    }
    
    public void setSpeed(double speed)
    {
        liftPid.disable();
        //camPid.disable();
        liftJaguar.set(speed);
    }

    public void setHeight(double target)
    {
        //camPid.disable();
        liftPid.setSetpoint(target);
        liftPid.enable();
    }

    public void setHeight(int point)
    {
        
    }
    
    public void stop()
    {
        //camPid.disable();
        liftPid.disable();
        liftJaguar.set(0.0);
    }

    public double getPidError()
    {
        return liftPid.getError();
    }
    
    public void update()
    {
        System.out.println("encoder value" + encoder.pidGet());
        //liftJaguar.update();
    }
}
