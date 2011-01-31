/**
 * AdvJaguar.java
 *
 * Extends the WPI jaguar class.  Empty for now, but may eventually implement
 * a built in averager.
 */

package edu.arhs.first1100.util;

import edu.wpi.first.wpilibj.Jaguar;
//import edu.arhs.first1100.util.Averager;

public class AdvJaguar extends Jaguar
{
    //Averager averager;
    private boolean polarity;

    public AdvJaguar(int ch) { this(ch,false); }

    public AdvJaguar(int ch, boolean inverted)
    {
        super(ch);
        polarity = inverted;
        //averager = new Averager(ch);
    }

    
    public void set(double speed)
    {
        //averager.feed(speed);

        // If polarity==true, set jag to the invert of speed.  else, just
        // give it speed.
        super.set( polarity ? -speed : speed);
    }
}
