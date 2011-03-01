/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.manipulator.ManipulatorSystem;

import edu.wpi.first.wpilibj.Timer;

public class GrabATubeRoutine extends Routine
{

    public GrabATubeRoutine()
    {
        super(100);
    }
    public void run()
    {
        ManipulatorSystem.getInstance().grabTube();
        Timer.delay(.75);
        this.setDone();
    }
}
