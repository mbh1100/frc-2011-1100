package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

public class ArmRoutine extends Routine
{

    public ArmRoutine()
    {
        super(50);

    }

    public void tick()
    {
        if(!ManipulatorSystem.getInstance().getArmLimitSwitch())
        {
            ManipulatorSystem.getInstance().setArmSpeed(0.0);
            setDone();
        }
        else
        {
            ManipulatorSystem.getInstance().setArmSpeed(0.25);
        }
    }

    public void doCancel()
    {
        ManipulatorSystem.getInstance().setArmSpeed(0.0);
    }
}
