/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.oopctl;

import edu.wpi.first.wpilibj.Joystick;
import edu.arhs.first1100.util.SystemBase;

public class XboxJoystick extends SystemBase
{
    private Button button_a;
    
    private Joystick xboxJoystick;
    
    public XboxJoystick(int channel)
    {
        super();

        xboxJoystick = new Joystick(channel);
        button_a = new Button(xboxJoystick, 1);
        
        this.start();
    }

    public void tick()
    {
        // update button a
    }

    public void bindA(ButtonHandler h)
    {
        // set the button_a's handler to h
    }
}