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
        ltLeft = new DigitalInput(2);
        ltMiddle = new DigitalInput(3);
        ltRight = new DigitalInput(4);
        ltBack = new DigitalInput(1);
    }
    
    public boolean leftline()
    {
        return ltLeft.get();       
    }
    
    public boolean rightline()
    {
        return ltRight.get();       
    }
    
    public boolean middleLine()
    {
        return ltMiddle.get();
    }
    
    public boolean backLine()
    {
        return ltBack.get();    
    }
}
