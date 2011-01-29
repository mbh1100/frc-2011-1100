package edu.arhs.first1100.util;

public class SystemBase extends Thread
{
    public double sleep_delay = 0.1;
    
    public void commencement()  { this.run(); }
    public void takeoff()       { this.run(); }
    public void inception()     { this.run(); }

    /**
     * Sets sleep_delay to .1
     */
    public SystemBase() { }

    public SystemBase(double delay)
    {
        sleep_delay = delay;
    }

    public void stop()
    {
        
    }
    
    public void log(String message)
    {
        System.out.println(this.getClass().getName().substring(10)+" : "+message);
    }
}
