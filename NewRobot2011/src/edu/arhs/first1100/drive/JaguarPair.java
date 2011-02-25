package edu.arhs.first1100.drive;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *sends commands to the jaguars
 * @author team1100
 */
public class JaguarPair
{
    
    private Jaguar j1;
    private Jaguar j2;

    private boolean polarity;
/**
 *declares the channels that the jags are on and to invert them
 * @param ch1
 * @param ch2
 * @param invert
 */
    public JaguarPair(int ch1, int ch2, boolean invert)
    {
        j1 = new Jaguar(ch1);
        j2 = new Jaguar(ch2);
        polarity = invert;
    }

    public JaguarPair(int ch1, int ch2)
    {
        this(ch1, ch2, false);
    }
    
    public void set(double speed)
    {
        j1.set( polarity ? -speed : speed );
        j2.set( polarity ? -speed : speed );
    }

    public double get()
    {
        return j1.get();

    }
}