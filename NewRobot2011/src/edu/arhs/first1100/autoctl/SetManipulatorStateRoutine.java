package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

public class SetManipulatorStateRoutine extends Routine
{
    public SetManipulatorStateRoutine(int state)
    {
        super(50);
        ManipulatorSystem.getInstance().setState(state);
        //this.state = state;
    }
    
    public void tick()
    {
        if(ManipulatorSystem.getInstance().getLiftMUXState() == ManipulatorSystem.LIFTMUX_OPERATOR &&
           ManipulatorSystem.getInstance().getArmMUXState()  == ManipulatorSystem.ARMMUX_OPERATOR)
            Log.defcon2(this, "Stopping routine");
            setDone();
    }
}