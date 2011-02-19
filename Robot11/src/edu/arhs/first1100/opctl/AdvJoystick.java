package edu.arhs.first1100.opctl;
import edu.arhs.first1100.util.Averager;

import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author team1100
 */
public class AdvJoystick extends Joystick
{
   /**
    *
    */
    private Averager averager;
    private Averager averagerX;
    private Averager averagerY;
    private int sampleSize = 4;
/**
 *
 * @param port
 */
    public AdvJoystick(int port)
    {
        super(port);
        averager = new Averager(sampleSize);
        averagerX = new Averager(sampleSize);
        averagerY = new Averager(sampleSize);
    }
/**
 *
 * @return
 */
    public double getStickX()
    {
        averagerX.feed(super.getRawAxis(1));
        return averagerX.get();
    }
/**
 *
 * @return
 */
    public double getStickY()
    {
        averagerY.feed(super.getRawAxis(2));
        return averagerY.get();
    }
}
