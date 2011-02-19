package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author team1100
 */
public class Light
{
    Solenoid solenoid;
    boolean on = false;
    java.util.Timer timer;
    
    /**
     *
     */
    class Timeout extends TimerTask
    {
        Light light;
        
        public Timeout(Light light)
        {
            this.light = light;
        }
       /**
        *
        */
        public void run()
        {
            light.off();
        }
        
    }
    /**
     *
     * @param ch
     */
    public Light(int ch)
    {
        solenoid = new Solenoid(ch);
    }
    /**
     *
     */
    public void toggle()
    {
        if (on)
        {
            this.off();
        }
        else
        {
            this.on();
        }
    }
    /**
     *
     */
    public void onForAWhile()
    {
        this.on();
        this.scheduleOff();
    }
    /**
     *
     */
    public void scheduleOff()
    {        
        if (timer != null)
        {
            timer.cancel();
        }
        
        timer = new Timer();
        timer.schedule(new Timeout(this), 2000);
    }
    /**
     *
     */
    public void on()
    {
        on = true;
        System.out.println("Light: on called");
        solenoid.set(true);
    }
    /**
     *
     */
    public void off()
    {
        on = false;
        System.out.println("Light: off called");
        solenoid.set(false);
    }

}

