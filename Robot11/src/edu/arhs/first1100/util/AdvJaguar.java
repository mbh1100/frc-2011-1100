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
/**
 *
 * @author team1100
 */
public class AdvJaguar extends Jaguar
{
    private boolean polarity;
   /**
    *
    * @param ch
    */
    public AdvJaguar(int ch)
    {
        this(ch, false);
    }
/**
 *
 * @param ch
 * @param inverted
 */
    public AdvJaguar(int ch, boolean inverted)
    {
        super(ch);
        polarity = inverted;
    }
   /**
    *
    * @param speed
    */
    public void set(double speed)
    {
        super.set( polarity ? -speed : speed);
    }
}
