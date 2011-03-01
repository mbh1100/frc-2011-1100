package edu.arhs.first1100.line;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *create a line system
 * @author team1100
 */
public class LineSystem extends SystemBase
{
    static private LineSystem instance;
    static private int sleepTime = 100;

    public static final int STATE_MIDDLE = 0;
    public static final int STATE_RIGHT = -1;
    public static final int STATE_LEFT = 1;
    public static final int STATE_NONE = 2;
    public static final int STATE_ALL = 3;
    public static final int STATE_SPLIT = 4;
    
    int lineStatus = STATE_NONE;
    
    boolean done = false;
    
    DigitalInput left;
    DigitalInput middle;
    DigitalInput right;
    
    public static LineSystem getInstance()
    {
        if(instance == null) instance = new LineSystem();
        return instance;
    }
/**
     *creates new line trackers
     * specifies how long the robot should sleep
     * @param robot
     * @param sleepTime
     */
    private LineSystem()
    {
        super(sleepTime);
        
        left = new DigitalInput(1);
        middle = new DigitalInput(3);
        right = new DigitalInput(2);
    }

    public boolean getLeft()
    {
        return left.get();
    }

    public boolean getMiddle()
    {
        return middle.get();
    }

    public boolean getRight()
    {
        return right.get();
    }
    
    /**
     * how often the robot should tick when it sees the line
     */
    public int getState()
    {
        if(left.get() && middle.get() && right.get())
            lineStatus = STATE_ALL;
        else if(!left.get() && !middle.get() && !right.get())
            lineStatus = STATE_NONE;
        else if(left.get() && !middle.get() && right.get())
            lineStatus = STATE_SPLIT;
        else if(middle.get())
            lineStatus = STATE_MIDDLE;
        else if(left.get())
            lineStatus = STATE_LEFT;
        else if(right.get())
            lineStatus = STATE_RIGHT;
        
        return lineStatus;
    }
}
