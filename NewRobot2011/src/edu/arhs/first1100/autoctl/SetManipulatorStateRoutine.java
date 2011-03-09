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
        if(ManipulatorSystem.getInstance().getLiftMUXState() == ManipulatorSystem.LIFTMUX_OPERATOR)
            setDone();
    }
}
