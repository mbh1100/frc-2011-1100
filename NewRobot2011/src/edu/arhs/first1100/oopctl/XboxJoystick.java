
/*
Future controls:

            Xbox Controls:
    
    Left Analog:    Arm
    Right Analog:   Lift

    Right Bumper:   Pull tube in
    Left Bumper:    Push tube out
    
    A Button:       Rotate tube down
    B Button:       Rotate tube up

    X Button:       Floor state(Lift up, gripper down)
    Y Button:       Default state(Lift fully down, gripper fully up)

    Back + Start:   Deploy minibot arm(the new swing-out one)

*/

package edu.arhs.first1100.oopctl;

import edu.wpi.first.wpilibj.Joystick;
import edu.arhs.first1100.util.SystemBase;
public class XboxJoystick extends SystemBase
{
    private Button buttonA;
    private Button buttonB;
    private Button buttonX;
    private Button buttonY;
    private Button buttonLeftBumper;
    private Button buttonRightBumper;
    private Button buttonStart;
    private Button buttonBack;
    
    
    private Joystick xboxJoystick;
    
    public XboxJoystick(int channel)
    {
        super();

        xboxJoystick = new Joystick(channel);
        buttonA = new Button(xboxJoystick, 1, "A button");
        buttonB = new Button(xboxJoystick, 2, "B button");
        buttonX = new Button(xboxJoystick, 3, "X button");
        buttonY = new Button(xboxJoystick, 4, "Y button");
        buttonLeftBumper = new Button(xboxJoystick, 5, "Left Bumper button");
        buttonRightBumper = new Button(xboxJoystick, 6, "Right Bumper button");
        buttonStart = new Button(xboxJoystick, 7, "Start button");
        buttonBack = new Button(xboxJoystick, 8, "Back button");

        
        this.start();
    }

    public void tick()
    {
        buttonA.update();
        buttonB.update();
        buttonX.update();
        buttonY.update();
        buttonLeftBumper.update();
        buttonRightBumper.update();
        buttonStart.update();
        buttonBack.update();
    }

    public void bindA(ButtonHandler h)
    {
        buttonA.bind(h);
        // set the button_a's handler to h
    }
    public void bindB(ButtonHandler h)
    {
        buttonB.bind(h);
        // set the button_b's handler to h
    }
    public void bindX(ButtonHandler h)
    {
        buttonX.bind(h);
        // set the button_x's handler to h
    }
    public void bindY(ButtonHandler h)
    {
        buttonY.bind(h);
        // set the button_y's handler to h
    }
    public void bindLeftBumper(ButtonHandler h)
    {
        buttonLeftBumper.bind(h);
        // set the button_LeftBumper's handler to h
    }
    public void bindRighBumper(ButtonHandler h)
    {
        buttonRightBumper.bind(h);
        // set the button_RighBumper's handler to h
    }
    public void bindStart(ButtonHandler h)
    {
        buttonStart.bind(h);
        // set the button_Start's handler to h
    }
    public void bindBack(ButtonHandler h)
    {
        buttonBack.bind(h);
        // set the button_Back's handler to h
    }
    
}