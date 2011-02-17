package edu.arhs.first1100.line;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;

public class LineSystem extends SystemBase
{   //these need to be static for FollowLineRoutine;
    public static final int STATE_MIDDLE = 0;
    public static final int STATE_RIGHT = -1;
    public static final int STATE_LEFT = 1;
    public static final int STATE_NONE = 2;
    public static final int STATE_ALL = 3;
    public static final int STATE_SPLIT = 4;
   
    int lineStatus = STATE_MIDDLE;

    boolean done = false;
    
    LineTracker lt;

    public LineSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        lt = new LineTracker();
    }

    /*
    synchronized public void waitUntilDone()
    {
        if (!done)
        {
            try
            {
                wait();
            }
            catch (InterruptedException x) {}
        }
    }

    synchronized void imDone()
    {
        done = true;
        this.notify();
    }
    */
    
    public void tick()
    {
        if(lt.leftline() && lt.middleLine() && lt.rightline())
            lineStatus = STATE_ALL;
        else if(!lt.leftline() && !lt.middleLine() && !lt.rightline())
            lineStatus = STATE_NONE;
        else if(lt.middleLine())
            lineStatus = STATE_MIDDLE;
        else if(lt.leftline())
            lineStatus = STATE_LEFT;
        else if(lt.rightline())
            lineStatus = STATE_RIGHT;
        else if(lt.leftline() && !lt.middleLine() && lt.rightline())
            lineStatus = STATE_SPLIT;
    }

    public int getState()
    {
        return lineStatus;
    }
    
}
