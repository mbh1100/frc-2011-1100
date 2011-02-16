/**
 * JaguarPair.java
 *
 * This class contains two Jaguar objects.
 * This class acts exactly like an Jaguar, except commands are sent to both
 * simultaneously.  This will be used in our drive system where two motors
 * must be used together to drive one side of the robot.
 *
 * This suffers the same fate as AdvJaguar and must manually be updated each
 * tick().
 *
 */

package edu.arhs.first1100.drive;

import edu.wpi.first.wpilibj.Jaguar;


public class JaguarPair
{
    
    private Jaguar j1;
    private Jaguar j2;

    private boolean polarity;

    private double targetSpeed = 0.0;

    public JaguarPair(int ch1, int ch2, boolean invert, int sampleSize)
    {        
        j1 = new Jaguar(ch1);
        j2 = new Jaguar(ch2);

        polarity = invert;
    }

    public JaguarPair(int ch1, int ch2)
    {
        this(ch1, ch2, false, 0);
    }
    
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