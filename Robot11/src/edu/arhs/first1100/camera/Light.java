package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.Relay;

public class Light
{
    Relay relay;
    boolean on = false;

    public Light(int ch)
    {
        relay = new Relay(ch, Relay.Direction.kForward);
    }

    public void toggle()
    {
        if (on)
        {
            relay.set(Relay.Value.kOn);
        }
        else
        {
            relay.set(Relay.Value.kOff);
        }
        
        on = !on;
    }

    public void on()
    {
        on = true;
        relay.set(Relay.Value.kOn);
    }

    public void off()
    {
        on = false;
        relay.set(Relay.Value.kOff);
    }

}

