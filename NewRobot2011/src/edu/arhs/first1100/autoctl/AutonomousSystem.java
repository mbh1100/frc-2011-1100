package edu.arhs.first1100.autoctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
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

    public void tick()
    {
        
    }

    public void win()
    {
        ButtonBox bb = op.buttonBox;
        
        scoreUberTube(bb.getStartingPosition(),
                      bb.getRack(),
                      bb.getColumn(),
                      bb.getRow());
    }
    
    public void scoreUberTube(int starting, boolean rack, int column, int row)
    {
        OperatorSystem op = OperatorSystem.getInstance();
        
        new SetManipulatorStateRoutine(ManipulatorSystem.STATE_DEFAULT).execute();
        
        new FollowLineRoutine(rack).execute();
        
        new SelectColumRoutine().execute();
        
        switch(row)
        {
            case 0:
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_BOTTOM_PEG).execute();
                break;
            case 1:
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_MIDDLE_PEG).execute();
                break;
            case 2:
                new SetManipulatorStateRoutine(ManipulatorSystem.STATE_TOP_PEG).execute();
                break;
        }
    }
}
