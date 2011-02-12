package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.Relay;

public class Light {
    Relay relay;
    boolean on = false;

    public Light(int ch)
    {
        relay = new Relay(ch, Relay.Direction.kForward);
    }

    public void toggle(){
        if (on)
            relay.set(Relay.Value.kOff);
        else
            relay.set(Relay.Value.kOn);
    }
}

