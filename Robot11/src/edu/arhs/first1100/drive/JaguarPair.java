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

import edu.arhs.first1100.util.Averager;

public class JaguarPair
{
    private Averager averager;

    private Jaguar j1;
    private Jaguar j2;

    private boolean j1polarity;
    private boolean j2polarity;

    private double targetSpeed = 0.0;

    public JaguarPair(int ch1, int ch2, boolean invert1, boolean invert2, int sampleSize)
    {
        averager = new Averager(sampleSize);
        
        j1 = new Jaguar(ch1);
        j2 = new Jaguar(ch2);

        j1polarity = invert1;
        j2polarity = invert2;
    }

    public JaguarPair(int ch1, int ch2, int sampleSize)
    {
        this(ch1, ch2, false, false, sampleSize);
    }

    public JaguarPair(int ch1, int ch2)
    {
        this(ch1, ch2, false, false, 1);
    }
    
    public void set(double speed)
    {
        targetSpeed = speed;
    }

    public void update()
    {
        averager.feed(targetSpeed);
        
        double s = averager.get();
        j1.set( j1polarity ? -s : s );
        j2.set( j2polarity ? -s : s );
    }
}