package edu.arhs.first1100.opctl;
import edu.arhs.first1100.util.Averager;

import edu.wpi.first.wpilibj.Joystick;
/**
 *the joystick
 * @author team1100
 */
public class AdvJoystick extends Joystick
{
    private Averager averager;
    private Averager averagerX;
    private Averager averagerY;
    private int sampleSize = 4;
/**
 *says what port the joystick is on
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
 *get the x axis
 * @return
 */
    public double getStickX()
    {
        averagerX.feed(super.getRawAxis(1));
        return averagerX.get();
    }
/**
 *gets the y axis
 * @return
 */
    public double getStickY()
    {
        averagerY.feed(super.getRawAxis(2));
        return averagerY.get();
    }
}
