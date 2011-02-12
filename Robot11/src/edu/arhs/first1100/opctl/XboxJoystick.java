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
    public double getLeftTrigger()
    {
        return -Math.min(super.getRawAxis(3), 0);
    }

    public double getRightTrigger()
    {
        return Math.max(super.getRawAxis(3), 0);
    }

    public double getTriggers()
    {
        return super.getRawAxis(3);
    }

    public boolean getXButton()
    {
        return super.getRawButton(3);
    }

    public boolean getYButton()
    {
        return super.getRawButton(4);
    }

    public boolean getAButton()
    {
        return super.getRawButton(1);
    }

    public boolean getBButton()
    {
        return super.getRawButton(2);
    }

    public boolean getLeftBumper()
    {
        return super.getRawButton(5);
    }

    public boolean getRightBumper()
    {
        return super.getRawButton(6);
    }
}
