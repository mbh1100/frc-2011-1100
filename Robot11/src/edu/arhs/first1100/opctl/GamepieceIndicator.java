/**
 * GamepieceIndicator.java
 *
 * Controls the LED panel that well the human player what piece to feed
 * the robot during the match.
 *
 * Future Ideas:
 *   Make this class threaded and constantly blink the LED.  This would help
 *   the human player react faster.
 *
 */

package edu.arhs.first1100.opctl;
import edu.wpi.first.wpilibj.Relay;

public class GamepieceIndicator
{
    Relay red, blue, white;
    
    public GamepieceIndicator()
    {
        red   = new Relay(4, Relay.Direction.kForward); //red light on channel 4
        blue  = new Relay(5, Relay.Direction.kForward); //Blue light on channel 5
        white = new Relay(6, Relay.Direction.kForward); //White light on channel 6

        setLightColorClear();
    }
    
    public void setLightColorRed()
    {
        red.set(Relay.Value.kOn);
        blue.set(Relay.Value.kOff);
        white.set(Relay.Value.kOff);
        System.out.println("Red on");
    }

    public void setLightColorBlue()
    {
        red.set(Relay.Value.kOff);
        blue.set(Relay.Value.kOn);
        white.set(Relay.Value.kOff);
        System.out.println("Blue on");
    }

    public void setLightColorWhite()
    {
        red.set(Relay.Value.kOff);
        blue.set(Relay.Value.kOff);
        white.set(Relay.Value.kOn);
        System.out.println("White on");
    }

    public void setLightColorClear()
    {
        red.set(Relay.Value.kOff);
        blue.set(Relay.Value.kOff);
        white.set(Relay.Value.kOff);
        System.out.println("all lights off");
    }
}
