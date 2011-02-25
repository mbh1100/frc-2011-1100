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
/**
 *
 * @author team1100
 */
public class AdvJaguar
{
    private boolean polarity;
    private Jaguar j1;
    private Jaguar j2;

    /**
     *
     * @param ch
     * @param inverted
     */
    public AdvJaguar(int slot, int ch1, int ch2, boolean inverted)
    {
        super(ch);
        polarity = inverted;
    }

   /**
    *
    * @param ch
    */
    public AdvJaguar(int ch)
    {
        this(ch, false);
    }

    public AdvJaguar(int slot, int ch)
    {

    }

    public AdvJaguar(int slot, int ch1, int ch2)
    {

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
