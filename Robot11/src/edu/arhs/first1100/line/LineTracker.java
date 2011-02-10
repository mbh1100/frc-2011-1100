package edu.arhs.first1100.line;

import edu.wpi.first.wpilibj.DigitalInput;

public class LineTracker
{
    DigitalInput ltLeft;
    DigitalInput ltMiddle;
    DigitalInput ltRight;
    DigitalInput ltBack;

    public LineTracker()
    {
        ltLeft = new DigitalInput(1);
        ltMiddle = new DigitalInput(2);
        ltRight = new DigitalInput(3);
        ltBack = new DigitalInput(4);
    }
    
    public boolean leftline()
    {
        if (ltLeft.get())
        {
            return true;
        }
        else
        {
            return false;
        }        
    }
    
    public boolean rightline()
    {
        if (ltRight.get())
        {
            return true;
        }
        else
        {
            return false;
        }        
    }
    
    public boolean middleLine()
    {
        if (ltMiddle.get())
        {
            return true;
        }
        else
        {
            return false;
        }        
    }
    
    public boolean backLine()
    {
        if (ltBack.get())
        {
            return true;
        }
        else
        {
            return false;
        }     
    }
}
