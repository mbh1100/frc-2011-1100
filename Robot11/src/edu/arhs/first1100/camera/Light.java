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
    boolean state = false;
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
            light.state = false;
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
        state = !state;
        System.out.println("Light toggled");
        solenoid.set(state);
    }

    public void on()
    {
        if(timer != null)
            timer.cancel();
        
        state = true;
        solenoid.set(state);
    }

    public void off()
    {
        state = false;
        solenoid.set(state);
    }
    
    /**
     *
     */
    public void onForAWhile()
    {
        on();
        this.scheduleOff();
    }
    /**
     *
     */
    public void scheduleOff()
    {
        timer = new Timer();
        timer.schedule(new Timeout(this), 2000);
    }
}

