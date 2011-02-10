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
    Relay red, white, blue;

    private final int BLINK_DELAY = 16;
    
    private final int OFF   = 0;
    private final int RED   = 1;
    private final int WHITE = 2;
    private final int BLUE  = 3;

    private int step = 0;
    private int color = 0;
    
    public GamepieceIndicator()
    {
        red   = new Relay(4, Relay.Direction.kForward); //red light on channel 4
        white = new Relay(6, Relay.Direction.kForward); //White light on channel 6
        blue  = new Relay(5, Relay.Direction.kForward); //Blue light on channel 5
    }
    
    public void setLightColorRed()
    {
        color = RED;
    }

    public void setLightColorWhite()
    {
        color = WHITE;
        
    }
    
    public void setLightColorBlue()
    {
        color = BLUE;
        
    }

    public void setLightColorClear()
    {
        color = OFF;
    }
    
    public void update()
    {
        if(step % BLINK_DELAY == 0)
        {
            switch(color)
            {
                case RED:
                    red.set(Relay.Value.kOn);
                    blue.set(Relay.Value.kOff);
                    white.set(Relay.Value.kOff);
                    break;
                case WHITE:
                    red.set(Relay.Value.kOff);
                    blue.set(Relay.Value.kOff);
                    white.set(Relay.Value.kOn);
                    break;
                case BLUE:
                    red.set(Relay.Value.kOff);
                    blue.set(Relay.Value.kOn);
                    white.set(Relay.Value.kOff);
                    break;
            }
        }
        else if(step % BLINK_DELAY == (BLINK_DELAY/2))
        {
            red.set(Relay.Value.kOff);
            blue.set(Relay.Value.kOff);
            white.set(Relay.Value.kOff);
        }
        
        step++;
        step%=BLINK_DELAY;
    }
}
