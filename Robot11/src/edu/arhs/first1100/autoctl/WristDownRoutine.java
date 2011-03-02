/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.wpi.first.wpilibj.Timer;

public class WristDownRoutine extends Routine
{

    public WristDownRoutine()
    {
        super(100);
    }
    public void run()
    {
        ManipulatorSystem.getInstance().lowerWrist();
        Timer.delay(1);
        this.setDone();
    }
    protected void doCancel()
    {
        this.setDone();
    }
}
