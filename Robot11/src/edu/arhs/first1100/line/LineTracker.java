package edu.arhs.first1100.line;

import edu.wpi.first.wpilibj.DigitalInput;
/**
 *runs the line tracker
 * @author team1100
 */
public class LineTracker
{
    DigitalInput ltLeft;
    DigitalInput ltMiddle;
    DigitalInput ltRight;
    //DigitalInput ltBack;
 /**
  *declares what inputs the line trackers are on
  */
    public LineTracker()
    {
        ltLeft = new DigitalInput(1);
        ltMiddle = new DigitalInput(3);
        ltRight = new DigitalInput(2);
        //ltBack = new DigitalInput( 1);
    }
    /**
     *returns the left linetracker data
     * @return
     */
    public boolean leftline()
    {
        return ltLeft.get();       
    }
    /**
     *returns the right linetracker data
     * @return
     */
    public boolean rightline()
    {
        return ltRight.get();
    }
    /**
     *returns the middle linetracker data
     * @return
     */
    public boolean middleLine()
    {
        return ltMiddle.get();
    }
    /**
     *return the back linetracker
     * @return
     */
    /*
    public boolean backLine()
    {
        return ltBack.get();    
    }*/
}
