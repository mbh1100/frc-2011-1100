/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

public class PickUpTubeFromFloorRoutine extends Routine
{
    GrabATubeRoutine gatr = new GrabATubeRoutine();
    DefaultPositionRoutine dpr = new DefaultPositionRoutine();

    public PickUpTubeFromFloorRoutine()
    {
        super(100);
    }

    public void run()
    {
        gatr.execute();
        dpr.execute();

        this.setDone();
    }

    protected void doCancel()
    {
        gatr.cancel();
        dpr.cancel();
    }
}
