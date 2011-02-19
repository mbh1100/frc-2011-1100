package edu.arhs.first1100.util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
/**
 *
 * @author team1100
 */
public class PID extends PIDController
{
    /**
     *
     * @param p
     * @param i
     * @param d
     * @param s
     * @param o
     */
    public PID(double p, double i, double d, PIDSource s, PIDOutput o)
    {
    /**
     *
     */
        super(p,i,d,s,o);

    }
}
