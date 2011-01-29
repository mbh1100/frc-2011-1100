package edu.arhs.first1100.util;

/**
 *
 * @author team1100
 */
public class SystemBase extends Thread
{
    public void commencement()  { this.run(); }
    public void takeoff()       { this.run(); }
    public void inception()     { this.run(); }

    public SystemBase() { }

    public void log(String message)
    {
        System.out.println(this.getClass().getName().substring(10)+" : "+message);
    }
}
