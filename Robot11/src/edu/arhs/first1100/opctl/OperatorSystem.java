package edu.arhs.first1100.opctl;

import edu.arhs.first1100.util.SystemBase;

public class OperatorSystem extends SystemBase
{
    Joystick leftJoystick;
    Joystick rightJoystick;
    ButtonBox buttonBox;

    public OperatorSystem() { }

    public OperatorSystem(double delay)
    {
        super(delay);
    }

}
