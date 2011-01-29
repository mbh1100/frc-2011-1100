package edu.arhs.first1100.util;

public class SystemBase extends Thread
{
    private int sleepTime = 100;

    public SystemBase() 
    {

    }

    /**
     *
     * @param delay sets the amount of time the system should sleep
     */
    public SystemBase(int delay)
    {
        setSleep(delay);
    }

    public void stop()
    {
        
    }

    /**
     * Prints out messages to the console
     * @param String message 
     */
    private void log(String message)
    {
        System.out.println(this.getClass().getName().substring(10)+" : "+message);
    }

    /**
     * Set the amount of time that the system should sleep
     * @param int time How long the component should sleep in milliseconds
     */
    public void setSleep(int time){
        sleepTime = time;
    }
}
