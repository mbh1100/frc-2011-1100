package edu.arhs.first1100.opctl;
import edu.arhs.first1100.util.Averager;
/**
 *
 * @author team1100
 */
public class XboxJoystick extends AdvJoystick
{
    /**
     *
     */
    private Averager averager;
    private int sampleSize = 4;
/**
 *
 * @param ch
 */
    public XboxJoystick(int ch)
    {
        super(ch);
        averager = new Averager(sampleSize);
    }
/**
 *
 * @return
 */
    public double getLeftStickX()
    {
        averager.feed(super.getRawAxis(1));
        return averager.get();
    }
/**
 *
 * @return
 */
    public double getLeftStickY()
    {
        averager.feed(super.getRawAxis(2));
        return averager.get();
    }
/**
 *
 * @return
 */
    public double getRightStickX()
    {
        averager.feed(super.getRawAxis(4));
        return averager.get();
    }
/**
 *
 * @return
 */
    public double getRightStickY()
    {
        averager.feed(super.getRawAxis(5));
        return averager.get();
    }

    /*
     * NOTE: Triggers are independant.
     * 1.0 to 0 is get right trigger, 1.0 to 0 is get left trigger.
     * both triggers can't be read at once as both triggers down or up = 0.0
     */
    public double getLeftTrigger()
    {
        /**
         *
         */
        return -Math.min(super.getRawAxis(3), 0);
    }
/**
 *
 * @return
 */
    public double getRightTrigger()
    {
        return Math.max(super.getRawAxis(3), 0);
    }
    
    /*
     * NOTE: Triggers are not independant.
     * 1.0 is right trigger, -1.0 is left trigger.
     * both triggers can't be read at once as both triggers down or up = 0.0
     */
   /**
    * 
    * @return
    */
    public double getTriggers()
    {
        return super.getRawAxis(3);
    }
/**
 *
 * @return
 */
    public boolean getXButton()
    {
        return super.getRawButton(3);
    }
/**
 *
 * @return
 */
    public boolean getYButton()
    {
        return super.getRawButton(4);
    }

   /**
    *
    * @return
    */ public boolean getAButton()
    {
        return super.getRawButton(1);
    }
/**
 *
 * @return
 */
    public boolean getBButton()
    {

        return super.getRawButton(2);
    }
/**
 *
 * @return
 */
    public boolean getLeftBumper()
    {
        return super.getRawButton(5);
    }
/**
 *
 * @return
 */
    public boolean getRightBumper()
    {
        return super.getRawButton(6);
    }
/**
 *
 * @return
 */
    public boolean getBackButton()
    {
        return super.getRawButton(7);
    }
/**
 *
 * @return
 */
    public boolean getStartButton()
    {
        return super.getRawButton(8);
    }
}