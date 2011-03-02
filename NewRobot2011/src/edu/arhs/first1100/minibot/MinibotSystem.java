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

    private final int ARM_UP_POT_VALUE = 180;
    private final int ARM_DOWN_POT_VALUE = 475;
    
    private final int STATE_DROPARM = 0;
    private final int STATE_DEPLOY = 1;
    private final int STATE_LIFTARM = 2;
    private int state = STATE_DROPARM;
    
    private AdvJaguar armJaguar;
    private AdvJaguar beltJaguar;
    private DigitalInput beltBackSwitch;
    private DigitalInput beltFrontSwitch;
    private AnalogChannel armPOT;
    
    private boolean minibotDeployed;
    private boolean startButton;
    private boolean backButton;
    
    public MinibotSystem()
    {
        armJaguar = new AdvJaguar(6, 1, true);
        beltJaguar = new AdvJaguar(6, 2, true);
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
        if(state == STATE_DROPARM)
        {
            Log.defcon1(this, "*****State droparm");
            
            if(backButton)
            {
                Log.defcon1(this, "back button: drop arm");
                setArmSpeed(0.1);
            }
            else
            {
                Log.defcon1(this, "not back button: stop arm");
                setArmSpeed(0.0);
            }

            setBeltSpeed(0.0);
            
            if(Math.abs(armPOT.getValue() - ARM_DOWN_POT_VALUE) < 15)
                state = STATE_DEPLOY;

            Log.defcon1(this, "***************");
        }

        else if(state == STATE_DEPLOY)
        {
            Log.defcon1(this, "*****State deploy");
            
            if(backButton)
            {
                Log.defcon1(this, "back button: drop arm");
                setArmSpeed(0.1);
            }
            else
            {
                Log.defcon1(this, "not back button: stop arm");
                setArmSpeed(0.0);
            }
            
            if(startButton)
            {
                Log.defcon1(this, "start button: belt out");
                setBeltSpeed(1.0);
            }
            else
            {
                Log.defcon1(this, "not start button: belt in");
                setBeltSpeed(-1.0);
            }
            
            if(!beltFrontSwitch.get())
            {
                Log.defcon1(this, "minbot deploted trigger = true");
                minibotDeployed = true;
            }
            
            if(minibotDeployed && !beltBackSwitch.get())
            {
                Log.defcon1(this, "SETTING STATE TO LIFTARM");
                state = STATE_LIFTARM;
            }

            Log.defcon1(this, "minibotDeployed: "+minibotDeployed);
            
            Log.defcon1(this, "***************");
        }
        
        else if(state == STATE_LIFTARM)
        {
            Log.defcon1(this, "*****State liftarm");
            
            Log.defcon1(this, "Rasing arm");
            
            setArmSpeed(-0.25);
            
            if(Math.abs(armPOT.getValue() - ARM_UP_POT_VALUE) < 15)
            {
                Log.defcon1(this, "arm value reached");
                Log.defcon1(this, "minbot deployed trigger = false");
                minibotDeployed = false;

                setArmSpeed(0.0);
                
                Log.defcon1(this, "SETTING STATE TO DROPARM");
                state = STATE_DROPARM;
            }

            Log.defcon1(this, "***************");
        }
        
        Log.defcon2(this, "POT:" + armPOT.getValue());
    }
    
    private void setArmSpeed(double speed)
    {
        if(speed > 0.0 && armPOT.getValue() > ARM_DOWN_POT_VALUE)
           armJaguar.set(0.0);
        else if(speed < 0.0 && armPOT.getValue() < ARM_UP_POT_VALUE)
            armJaguar.set(0.0);
        else
            armJaguar.set(speed);
    }

    private void setBeltSpeed(double speed)
    {
        if(speed > 0 && !beltFrontSwitch.get())
            beltJaguar.set(0.0);
        else if(speed < 0 && !beltBackSwitch.get())
            beltJaguar.set(0.0);
        else
            beltJaguar.set(speed);
    }

    public void setStartButton(boolean start)
    {
        startButton = start;
    }

    public void setBackButton(boolean back)
    {
        backButton = back;
    }
}