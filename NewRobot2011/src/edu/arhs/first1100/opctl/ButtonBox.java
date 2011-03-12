package edu.arhs.first1100.opctl;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;

public class ButtonBox extends SystemBase
{
    // 1 is the left knob when looking at box with knobs on bottom
    private DSKnob knob1;
    private DSKnob knob2;
    private DSKnob knob3;
    private DSKnob knob4;
    
    public ButtonBox()
    {
        knob1 = new DSKnob(1);
        knob2 = new DSKnob(2);
        knob3 = new DSKnob(3);
        knob4 = new DSKnob(4);
    }
    /*
    public tick()
    {

        Log.defcon1(this, "Knob1 :"+knob1);
    }*/
    public void update()
    {
        
    }
    
    public int getStartingPosition()
    {
        return 0;
    }

    public boolean getRack()
    {
        return !DriverStation.getInstance().getDigitalIn(1);
    }

    public int getColumn()
    {
        return 2;
    }

    public int getRow()
    {
        if(!DriverStation.getInstance().getDigitalIn(2)) return 2;
        else if(!DriverStation.getInstance().getDigitalIn(3)) return 1;
        else if(!DriverStation.getInstance().getDigitalIn(4)) return 0;
        return 2;
    }
    
    /*
     *
     * WE NEED THE RIGHT LED PORTS!!!!!!!!
     * 
     */
    
    public void setMinibotGuideLight(boolean state)
    {
        DriverStation.getInstance().setDigitalOut(0, state);
    }

    public void setMinibotPoleLight(boolean state)
    {
        DriverStation.getInstance().setDigitalOut(1, state);
    }

    public void setMinibotDeployedLight(boolean state)
    {
        DriverStation.getInstance().setDigitalOut(3, state);
    }


}
