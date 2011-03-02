package edu.arhs.first1100.autoctl;
import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
/**
 * Runs routines
 * @author akshay
 */
public class RoutineManager extends SystemBase {

    public final int ROUTINE_NONE = 0;
    public final int ROUTINE_ARM_TO_FLOOR = 1;
    public final int ROUTINE_ARM_TO_POSITION = 2;
    public final int ROUTINE_GRAB_A_TUBE = 3;
    public final int ROUTINE_LIFT_TO_POSITION = 4;
    public final int ROUTINE_TUBE_FROM_FLOOR = 5;
    public final int ROUTINE_RELEASE_TUBE = 6;
    
    private static RoutineManager instance = null;
    private int state = 0;
    private boolean running = false;

    public RoutineManager(){ }

    public static RoutineManager getInstance(){
        if (instance == null) instance = new RoutineManager();
        return instance;
    }

    public void tick(){
        if (running && state != ROUTINE_NONE){
            //do something according to current state
        }
    }

    /**
     * Sets which routine should be run.
     * @param r routine to run
     */
    public void setRoutine(int r){
        if (!running) state = r;
    }

    /**
     * Starts running the current set routine
     */
    public void startRoutine(){
        running = true;
    }

    /**
     * Stops running all routines
     */
    public void stopRoutine(){
        running = false;
    }

    /**
     * Return whether or not a routine is running
     * @return boolean is running or not
     */
    public boolean isRunning(){
        return running;
    }

    /**
     * Return the current Routine that RoutineManager is set to run
     * @return current routine
     */
    public int getRoutine(){
        return state;
    }

    /* +------------------------+
     * | LET THE ROUTINES BEGIN |
     * +------------------------+ */

    private void armToFloor(){
        ManipulatorSystem.getInstance().wristDown();
    }

    private void armToPosition(){
    }

    private void defaultPosition(){
    }

    private void grabATube(){
    }

    private void liftToPos(){        
    }

    private void pickUpTube(){
    }

    private void releaseTube(){
    }

}
