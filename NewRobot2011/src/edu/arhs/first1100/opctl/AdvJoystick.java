package edu.arhs.first1100.opctl;
import edu.arhs.first1100.util.Averager;

import edu.wpi.first.wpilibj.Joystick;
/**
 *the joystick
 * @author team1100
 */
public class AdvJoystick extends Joystick
{
    private Averager averagerX;
    private Averager averagerY;
    private final int SAMPLE_SIZE = 4;

    /**
     *says what port the joystick is on
     * @param port
     */
    public AdvJoystick(int port)
    {
        super(port);
        averagerX = new Averager(SAMPLE_SIZE);
        averagerY = new Averager(SAMPLE_SIZE);
    }
    
    /**
     *get the x axis
     * @return
     */
    public double getStickX()
    {
        averagerX.feed(super.getRawAxis(1));
        return averagerX.get();
    }
    
    /**
     *gets the y axis
     * @return
     */
    public double getStickY()
    {
        averagerY.feed(super.getRawAxis(2));
        return averagerY.get();
    }
    
    public double getZAxis()
    {
        return super.getRawAxis(3);
    }
    
    public void toggleAVG(){
        averagerX.toggle();
        averagerY.toggle();
    }
}
