package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Encoder;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.PID;

public class Arm
{
    private final double KP = 0.5;
    private final double KI = 0.5;
    private final double KD = 0.5;

    private double targetSpeed = 0.0;

    private PID pid;

    private Encoder encoder;

    private AdvJaguar armJaguar;

    public Arm()
    {
        armJaguar = new AdvJaguar(8);
        encoder = new Encoder(10, 11);
        pid = new PID(KP, KI, KD, encoder, armJaguar);
    }

    public void setSpeed(double speed)
    {
        armJaguar.set(speed);
    }

    public void stop()
    {
        armJaguar.set(0.0);
    }

}
