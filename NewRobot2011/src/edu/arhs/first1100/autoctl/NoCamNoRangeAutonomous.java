package edu.arhs.first1100.autoctl;

//import edu.arhs.first1100.util.SystemBase;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.log.Log;
import edu.wpi.first.wpilibj.Timer;


public class NoCamNoRangeAutonomous extends Routine{
     //lift 4 arm 5
    private final double DRIVE_TIME = 5;

    private ArmRoutine ar = new ArmRoutine();
    private ScoreRoutine sr = new ScoreRoutine();
    private LiftRoutine lr = new LiftRoutine();



    public NoCamNoRangeAutonomous()
    {
        super(50);

    }

    public void run()
    {
        ar.start();
        lr.start();
        DriveSystem.getInstance().setTankSpeed(0.8, 0.8);
        Log.defcon1(this, "No cam driving");
        Timer.delay(DRIVE_TIME);
        Log.defcon1(this, "Done driving");
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);


        ar.waitForDone();
        lr.waitForDone();

        if(!isCancelled()) sr.start();
        sr.waitForDone();
    }

    protected void doCancel()
    {
        ar.cancel();
        sr.cancel();
        lr.cancel();
        DriveSystem.getInstance().setTankSpeed(0.0, 0.0);
    }

}
