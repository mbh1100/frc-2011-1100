/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.drive;

import edu.wpi.first.wpilibj.Jaguar;

public class JaguarPair
{
    final private boolean REVERSE_SECOND_JAG = false;
    
    Jaguar j1;
    Jaguar j2;

    public JaguarPair(int ch1, int ch2)
    {
        j1 = new Jaguar(ch1);
        j2 = new Jaguar(ch2);
    }

}
/**
 * JaguarPair.java
 *
 * This class contains two AdvJaguar objects.
 * This class acts exactly like an AdvJaguar, except commands are sent to both
 * simaltaniously.  This will be used in our drive system where two motors
 * must be used together to drive one side of the robot.
 *
 * Nick - 3:24pm 1/29
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.util.AdvJaguar;

public class JaguarPair
{
    private AdvJaguar j1;
    private AdvJaguar j2;
    
    public JaguarPair(int ch1, int ch2, boolean invert)
    {
        j1 = new AdvJaguar(ch1, invert);
        j2 = new AdvJaguar(ch2, invert);
    }

    public JaguarPair(int ch1, int ch2)
    {
        this(ch1, ch2, false);
    }

    public void set(double speed)
    {
        // Jags are individually set as inverted or not in constructor.
        j1.set(speed);
        j2.set(speed);
    }
}
