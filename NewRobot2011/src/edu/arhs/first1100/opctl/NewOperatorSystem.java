package edu.arhs.first1100.opctl;

import edu.arhs.first1100.oopctl.WingMan;
import edu.arhs.first1100.oopctl.XboxJoystick;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.minibot.MinibotSystem;


class MinibotBelt extends edu.arhs.first1100.oopctl.ButtonHandler
{
     public void pressed()
    {
        super.pressed();
        MinibotSystem.getInstance().setBeltSpeed(0.3);
    }

    public void released()
    {
       super.released();
       MinibotSystem.getInstance().setBeltSpeed(0.0);
    }
}

public class NewOperatorSystem extends SystemBase
{
    private WingMan leftJoystick;
    private WingMan rightJoystick;
    private XboxJoystick xboxGamepad;
    
    public NewOperatorSystem()
    {
        leftJoystick = new WingMan(1);
        rightJoystick = new WingMan(2);
        xboxGamepad = new XboxJoystick(3);
        
        bindControls();
    }

    private static NewOperatorSystem instance = null;
    public static NewOperatorSystem getInstance()
    {
        if(instance == null) instance = new NewOperatorSystem();
        return instance;
    }

    private void bindControls()
    {
        //leftJoystick.bindY(new LiftHandler());
        //xboxGamepad.bindX(new SetManipulatorState(ManipulatorSystem.STATE_DEFAULT));
        xboxGamepad.bindBack(new MinibotBelt());
    }
}
