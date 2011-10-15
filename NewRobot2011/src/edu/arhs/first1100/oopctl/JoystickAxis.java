package edu.arhs.first1100.oopctl;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickAxis
{
    String name;
    int channel;
    Joystick js;
    JoystickAxisHandler jah = new JoystickAxisHandler();
    public JoystickAxis(Joystick jStick, int channel, String name)
    {
           
        this.channel = channel;
        js = jStick;
        this.name = name;
        bind(new JoystickAxisHandler());
    }
    public void update()
    {
        jah.newValue(js.getY());
    }
    public void bind(JoystickAxisHandler j)
    {
        jah = j;
        jah.setName("Name");
    }

}

