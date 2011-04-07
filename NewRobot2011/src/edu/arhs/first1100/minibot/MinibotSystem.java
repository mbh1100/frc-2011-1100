package edu.arhs.first1100.minibot;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;


/*
 *                       HOW THIS THING WORKS
 *
 * The minibot system uses three states to go through this process:
 *
 * STATE_DROPARM -> STATE_DEPLOY -> STATE_LIFTARM -> STATE_DROPARM -> ...
 *
 * The goal of these states is to slightly switch control schemes depending
 * on where the arm is to make sure we can't deploy when we shouldn't, and
 * to bring the arm back automaticly.
 *
 * Here is a step by step process of how the bot will be deployed.
 * 
 * 1. Operator pushes back to lower arm.
 *
 * 2. Once a preset POT value is reached, operator can now hold start
 *    to push minibot on belt.  If start is depressed, the belt will
 *    move back tword the robot.
 *
 * 3. When the minibot hits the end limit switch and is launched, the
 *    operator brings back the belt by not pressing start.
 *
 * 4. When the belt is all the way back in, the arm will automaticly
 *    raise back to the up position, ready for another minibot
 *    deployment next match.
 * 
 */
public class MinibotSystem extends SystemBase
{
    private static MinibotSystem instance = null;

    private final int ARM_UP_POT_VALUE = 180;
    private final int ARM_DOWN_POT_VALUE = 475;
    
    private AdvJaguar armJaguar;
    private AdvJaguar beltJaguar;
    private DigitalInput beltBackSwitch;
    private DigitalInput beltFrontSwitch;
    private DigitalInput guideSwitch;
    private DigitalInput poleSwitch;
    
    //private AnalogChannel armPOT;
    
    private boolean minibotDeployed;
    
    public MinibotSystem()
    {
        armJaguar = new AdvJaguar(6, 1, true);
        beltJaguar = new AdvJaguar(6, 2, true);
        beltBackSwitch = new DigitalInput(6, 3);
        beltFrontSwitch = new DigitalInput(6, 4);
        guideSwitch = new DigitalInput(6, 6);
        poleSwitch = new DigitalInput(6,7);
        
        //armPOT = new AnalogChannel(1, 2);
    }
    
    public static MinibotSystem getInstance()
    {
        if(instance == null) instance = new MinibotSystem();
        return instance;
    }

    public void tick()
    {
        Log.defcon1(this, "Pole:  " + !poleSwitch.get());
        Log.defcon1(this, "Guide: " + !guideSwitch.get());

        Log.defcon1(this, "");
    }
    
    public void setArmSpeed(double speed)
    {
        Log.defcon1(this, "Setting arm speed..." + !guideSwitch.get());

        /*if(speed > 0.0 && armPOT.getValue() > ARM_DOWN_POT_VALUE)
        {
            Log.defcon1(this, ".....Stopping, too low");
            armJaguar.set(0.0);
        }
        else if(speed < 0.0 && armPOT.getValue() < ARM_UP_POT_VALUE)
        {
            Log.defcon1(this, ".....Stopping, too high");
            armJaguar.set(0.0);
        }
        else
        {
            Log.defcon1(this, "....."+speed);
            armJaguar.set(speed);
        }*/

        armJaguar.set(speed);
    }
    
    public void setBeltSpeed(double speed)
    {
        Log.defcon1(this, "Setting Belt speed..." + !guideSwitch.get());
        Log.defcon1(this, "....."+speed);
        if(speed > 0)
        {
            beltJaguar.set(speed);
            if(!beltFrontSwitch.get())
                beltJaguar.set(0.0);
        }
        else if(speed < 0)
        {
            beltJaguar.set(speed);
            if(!beltBackSwitch.get())
                beltJaguar.set(0.0);
        }
        else
        {
            beltJaguar.set(0.0);
        }
    }
}