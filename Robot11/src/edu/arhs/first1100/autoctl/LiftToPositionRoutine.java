/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.wpi.first.wpilibj.Timer;

public class LiftToPositionRoutine extends Routine
{
    double encoder = 0;

    public LiftToPositionRoutine(double encoder)
    {
        super(100);
        this.encoder = encoder;
    }

    public void run()
    {
        ManipulatorSystem.getInstance().setLiftPosition(encoder);
        while (Math.abs(ManipulatorSystem.getInstance().getLiftHeight() - encoder) > 5 &&
                ! isCancelled())
        {
            Timer.delay(.2);
        }

        this.setDone();
    }

    protected void doCancel()
    {
        ManipulatorSystem.getInstance().setLiftSpeed(0.0);
    }
}