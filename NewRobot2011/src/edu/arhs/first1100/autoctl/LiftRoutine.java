/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;
import edu.arhs.first1100.manipulator.ManipulatorSystem;

/**
 *
 * @author team1100
 */
public class LiftRoutine extends Routine
{

    public LiftRoutine()
    {
        super(50);

    }

    public void tick()
    {

        if(!ManipulatorSystem.getInstance().getLiftLimitSwitch())
        {
            ManipulatorSystem.getInstance().setLiftSpeed(0.0);
            setDone();
        }
        else
        {
            ManipulatorSystem.getInstance().setLiftSpeed(-0.8);
        }

    }

    public void doCancel()
    {
        ManipulatorSystem.getInstance().setLiftSpeed(0.0);
    }

}

