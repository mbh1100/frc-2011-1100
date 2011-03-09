package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.opctl.ButtonBox;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.arhs.first1100.util.SystemBase;

public class AutonomousSystem extends SystemBase
{
    private static AutonomousSystem instance = null;
    
    public AutonomousSystem()
    {
        win();
    }
    
    public static AutonomousSystem getInstance()
    {
        if(instance == null) instance = new AutonomousSystem();
        return instance;
    }

    public void tick() { }
    
    public void win()
    {
        ButtonBox bb = OperatorSystem.getInstance().buttonBox;
        
        scoreUberTube(bb.getStartingPosition(),
                      bb.getRack(),
                      bb.getColumn(),
                      bb.getRow());
    }
    
    public void scoreUberTube(int starting, boolean rack, int column, int row)
    {
        OperatorSystem op = OperatorSystem.getInstance();
        
        Log.defcon2(this, "Running SetManipulatorStateRoutine");
        new SetManipulatorStateRoutine(ManipulatorSystem.STATE_DEFAULT).execute();
        
        Log.defcon2(this, "Running FollowLineRoutine");
        new FollowLineRoutine(rack).execute();

        Log.defcon2(this, "Running SelectColumRoutine");
        new SelectColumRoutine().execute();
        
        Log.defcon2(this, "Running SetManipulatorStateRoutine on the...");
        switch(row)
        {
            case 2:
                Log.defcon2(this, "top peg");
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_TOP_PEG).execute();
                break;
            case 1:
                Log.defcon2(this, "middle peg");
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_MIDDLE_PEG).execute();
                break;
            case 0:
                Log.defcon2(this, "bottom peg");
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_BOTTOM_PEG).execute();
                break;
        }

        Log.defcon2(this, "All done!");
    }
}
