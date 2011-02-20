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

    private double targetSpeed = 0.0;
/**
 *declares the channels that the jags are on and to invert them
 * @param ch1
 * @param ch2
 * @param invert
 * @param sampleSize
 */
    public JaguarPair(int ch1, int ch2, boolean invert, int sampleSize)
    {        
        j1 = new Jaguar(ch1);
        j2 = new Jaguar(ch2);

        polarity = invert;
    }
/**
 *declares both false
 * @param ch1
 * @param ch2
 */
    public JaguarPair(int ch1, int ch2)
    {
        this(ch1, ch2, false, 0);
    }
   /**
    *sets the speed of the jags
    * @param speed
    */
    public void set(double speed)
    {
        j1.set( polarity ? -speed : speed );
        j2.set( polarity ? -speed : speed );
    }

    /*
     * return the speed of the first jaguar
     */
    public double get()
    {
        return j1.get();

    }
}