package edu.arhs.first1100.opctl;

public class XboxJoystick extends AdvJoystick
{
    public XboxJoystick(int ch)
    {
        super(ch);
    }
    
    public double getLeftStickX()
    {
        return super.getRawAxis(1);
    }

    public double getLeftStickY()
    {
        return super.getRawAxis(2);
    }

    public double getRightStickX()
    {
        return super.getRawAxis(4);
    }

    public double getRightStickY()
    {
        return super.getRawAxis(5);

    }

    /*
     * NOTE: Triggers are not independant.
     * -1.0 is right trigger, 1.0 is left.
     * both triggers can't be read at once as both triggers down or up = 0.0
     */
    public double getTriggers()
    {
        return super.getRawAxis(3);
    }
}
