/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.minibot;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author team1100
 */
public class MinibotSystem extends SystemBase
{
    private static MinibotSystem instance = null;

    private AdvJaguar armJaguar;
    private AdvJaguar beltJaguar;
    private DigitalInput beltBackSwitch;
    private DigitalInput beltFrontSwitch;
    private AnalogChannel armPOT;
    private int state;
    private final int ARM_UP_POT_VALUE = 0;
    private final int ARM_DOWN_POT_VALUE = 500;
    private boolean minibotDeployed;

    public MinibotSystem()
    {
        armJaguar = new AdvJaguar(6, 1, false);
        beltJaguar = new AdvJaguar(6, 2, false);
        beltBackSwitch = new DigitalInput(6, 3);
        beltFrontSwitch = new DigitalInput(6, 4);
        armPOT = new AnalogChannel(1, 2);


    }


    public static MinibotSystem getInstance()
    {
        if(instance == null) instance = new MinibotSystem();
        return instance;
    }

    public void tick()
    {
        if(beltFrontSwitch.get())
        {
            minibotDeployed = true;
        }
        if(Math.abs(armPOT.getValue()-ARM_UP_POT_VALUE) < 5)
        {
            minibotDeployed = false;
        }

        Log.defcon2(this, "POT:" + armPOT.getValue());

    }

    public void setArmSpeed(double speed)
    {
        if(armPOT.getValue() < ARM_DOWN_POT_VALUE)
        {
           armJaguar.set(speed);
        }
        else
        {
            armJaguar.set(0.0);
        }

        if(!(minibotDeployed && speed == 0.0))
        {
            armJaguar.set(-0.5);
        }
    }

    public void setBeltSpeed(double speed)
    {
        if(speed > 0 && beltFrontSwitch.get() && Math.abs(armPOT.getValue()-ARM_DOWN_POT_VALUE) < 5)
        {
            beltJaguar.set(0.0);
        }

        else if(speed < 0 && beltBackSwitch.get())
        {
            beltJaguar.set(0.0);
        }

        else
        {
            beltJaguar.set(speed);
        }

    }

    


}
