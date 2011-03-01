/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

public class PickUpTubeFromFloorRoutine extends Routine
{
    public PickUpTubeFromFloorRoutine()
    {
        super(100);
    }

    public void run()
    {
        GrabATubeRoutine gatr = new GrabATubeRoutine();
        gatr.execute();

        DefaultPositionRoutine dpr = new DefaultPositionRoutine();
        dpr.execute();

        this.setDone();
    }
}
