package edu.arhs.first1100.opctl;
import edu.arhs.first1100.util.Averager;

import edu.wpi.first.wpilibj.Joystick;

public class AdvJoystick extends Joystick
{
    private Averager averager;
    private int sampleSize = 4;

    public AdvJoystick(int port)
    {
        super(port);
        averager = new Averager(sampleSize);
    }

    public double getStickX()
    {
        averager.feed(super.getRawAxis(1));
        return averager.get();
    }

    public double getStickY()
    {
        averager.feed(super.getRawAxis(2));
        return averager.get();
    }
}
