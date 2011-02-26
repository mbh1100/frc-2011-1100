package edu.arhs.first1100.util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author team1100
 */
public class PID
{
    class Input implements PIDSource
    {
        PID pid;
        public double input;
        
        public Input(PID pid)
        {
            this.pid = pid;
        }
        
        public double pidGet()
        {
            return input;
        }
    }

    class Output implements PIDOutput
    {
        PID pid;
        public double output;

        public Output(PID pid)
        {
            this.pid = pid;
        }

        public void pidWrite(double output)
        {
            this.output = output;
        }
    }
    
    private Input pidSource;
    private Output pidInput;

    public void setTarget(double value)
    {
        pid.setSetpoint(value);
    }

    public void feed(double value)
    {
        pidSource.input = value;
    }
    
    public double get()
    {
        return pidInput.output;
    }

    private PIDController pid;
    public PID(double p, double i, double d)
    {
        pid = new PIDController(p, i, d, pidSource, pidInput);
    }

    public void enable()
    {
        pid.enable();
    }

    public void disable()
    {
        pid.disable();
    }
}