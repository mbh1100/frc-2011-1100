package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.opctl.ButtonBox;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class AutonomousSystem extends SystemBase
{
    private static AutonomousSystem instance = null;

    public AutonomousSystem()
    { }

    public void start()
    {
        super.start();
        
        if(!DriverStation.getInstance().getDigitalIn(1))
        {
            OperatorSystem.getInstance().dsPrint(2, "Running autonomous(but not really)");
            win();
        }
        else
        {
            OperatorSystem.getInstance().dsPrint(2, "autonomous skipped");
        }
    }
    
    public static AutonomousSystem getInstance()
    {
        if(instance == null) instance = new AutonomousSystem();
        return instance;
    }
    
    public void tick() { }
    /*  public void run() {
        DriveSystem.getInstance().driveByCamera();
        Timer.delay(10.0);
        DriveSystem.getInstance().disablePids();
    }*/
    
    public void win()
    {
        /*ButtonBox bb = OperatorSystem.getInstance().getButtonBox();
        
        scoreUberTube(bb.getStartingPosition(),
                      bb.getRack(),
                      bb.getColumn(),
                      bb.getRow());
         *
         */

        boolean mode = true;//DriverStation.getInstance().getEnhancedIO().getDigital(15);
        if(mode)
        {
            Log.defcon1(this, "No range autonomous");
            new NoCamNoRangeAutonomous().execute();
        }
        else
        {
            Log.defcon1(this, "Range mode for autonomous");
            new RangeAutonomous().execute();

        }
    }
    
    public void scoreUberTube(int starting, boolean rack, int column, int row)
    {
        //OperatorSystem op = OperatorSystem.getInstance();
        
        new HeckerAutonomousRoutine().execute();
        
        /*
        Log.defcon2(this, "Running SetManipulatorStateRoutine");
        new SetManipulatorStateRoutine(ManipulatorSystem.STATE_DEFAULT).execute();

        Timer.delay(1);// possibly change to .5?
        
        Log.defcon2(this, "Running FollowLineRoutine");
        FollowLineRoutine followline = new FollowLineRoutine(rack);
        followline.start();

        Timer.delay(3);


        //Log.defcon2(this, "Running SelectColumRoutine");
        //new SelectColumRoutine().execute();

        //SelectColumRoutine scr = new SelectColumRoutine();
        //Timer.delay(1);
        
        Log.defcon2(this, "Running SetManipulatorStateRoutine on the...");
        switch(row)
        {
            case 2:
                Log.defcon2(this, "Top Peg");
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_TOP_PEG).execute();
                break;
            case 1:
                Log.defcon2(this, "Middle Peg");
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_MIDDLE_PEG).execute();
                break;
            case 0:
                Log.defcon2(this, "Bottom Peg");
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_BOTTOM_PEG).execute();
                break;
        }
        followline.waitForDone();
        Timer.delay(1); //why do we need this after the wait for done?
        
        Log.defcon2(this, "All done!");
        */
    }
}
