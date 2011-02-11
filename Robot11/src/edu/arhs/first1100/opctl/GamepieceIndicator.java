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
import edu.wpi.first.wpilibj.Timer;


public class GamepieceIndicator extends Thread
{
    Relay red, white, blue;

    private final double BLINK_DELAY = 0.5;
    
    public final int OFF   = 0;
    public final int RED   = 1;
    public final int WHITE = 2;
    public final int BLUE  = 3;
    
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

    public void setLight(int C)
    {
        switch(C)
        {
            case RED:
                color = RED;
                break;
            case WHITE:
                color = WHITE;
                break;
            case BLUE:
                color = BLUE;
                break;
            case OFF:
                color = OFF;
                break;
        }
    }

    
    public void run()
    {
        while(true)
        {
            System.out.println("GPI: run repeat");
            switch(color)
            {
                case RED:
                    red.set(Relay.Value.kOn);
                    blue.set(Relay.Value.kOff);
                    white.set(Relay.Value.kOff);
                    System.out.println("GPI:red");
                    break;
                case WHITE:
                    red.set(Relay.Value.kOff);
                    blue.set(Relay.Value.kOff);
                    white.set(Relay.Value.kOn);
                    System.out.println("GPI:white");
                    break;
                case BLUE:
                    red.set(Relay.Value.kOff);
                    blue.set(Relay.Value.kOn);
                    white.set(Relay.Value.kOff);
                    System.out.println("GPI:blue");
                    break;
            }
            
            Timer.delay(BLINK_DELAY);
            
            red.set(Relay.Value.kOff);
            blue.set(Relay.Value.kOff);
            white.set(Relay.Value.kOff);
            
            System.out.println("GPI:OFF");
            System.out.println("");

            Timer.delay(BLINK_DELAY);
        }
    }
}
