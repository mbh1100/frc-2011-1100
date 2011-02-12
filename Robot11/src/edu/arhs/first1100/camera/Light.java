package edu.arhs.first1100.camera;

import edu.wpi.first.wpilibj.Relay;

public class Light {
    Relay relay;

    public Light(int ch)
    {
        relay = new Relay(ch, Relay.Direction.kForward);
    }

    public void on()
    {
        relay.set(Relay.Value.kOn);
    }
    public void off()
    {
        relay.set(Relay.Value.kOff);
    }
}

