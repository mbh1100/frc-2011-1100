/**
 * AdvJaguar.java
 *
 * Extends the WPI jaguar class.
 *
 * Jaguar has a built-in Averager.
 * Sadly, java dosn't support multiple inheritence, meaning I couldn't make
 * it both extend Jaguar and Thread, so you'll have to call update every
 * tick.
 *
 * Future Ideas:
 *   Make this threaded somehow.
 */

package edu.arhs.first1100.util;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.arhs.first1100.util.Averager;

public class AdvJaguar extends Jaguar
{
    Averager averager;
    private boolean polarity;
    private double targetSpeed = 0.0;
    
    public AdvJaguar(int ch)
    {
        this(ch, false);
    }

    public AdvJaguar(int ch, boolean inverted)
    {
        this(ch, false, 1);
    }
    
    public AdvJaguar(int ch, boolean inverted, int sampleSize)
    {
        super(ch);
        polarity = inverted;
        averager = new Averager(sampleSize);
    }
    
    public void set(double speed)
    {
        targetSpeed = speed;
    }

    public void update()
    {
        averager.feed(targetSpeed);
        double s = averager.get();
        super.set( polarity ? -s : s);
    }
}
