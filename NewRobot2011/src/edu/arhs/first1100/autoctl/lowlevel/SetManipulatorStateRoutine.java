package edu.arhs.first1100.autoctl.lowlevel;

import edu.arhs.first1100.autoctl.Routine;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

public class SetManipulatorStateRoutine extends Routine
{
    private int targetState;
    
    public SetManipulatorStateRoutine(int state) //USE ManipulatorSystem.STATE_*
    {
        super(100);
        targetState = state;
    }

    public void run()
    {
        ManipulatorSystem.getInstance().setState(targetState);
        while(true) //ManipulatorSystem.getInstance())
        {
            
        }
    }
}
