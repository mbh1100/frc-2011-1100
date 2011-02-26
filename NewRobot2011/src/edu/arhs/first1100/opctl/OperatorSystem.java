/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.opctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.util.PID;
import edu.arhs.first1100.util.SystemBase;

import edu.arhs.first1100.util.Log;

/**
 *
 * @author team1100
 */
public class OperatorSystem extends SystemBase
{
    private static OperatorSystem instance = null;

    public AdvJoystick leftJoystick;  //controls the left side of the robot
    public AdvJoystick rightJoystick; //controls the right side of the robot.
    public XboxJoystick xboxJoystick; //controls the arm and other stuff. Hi.

    private boolean xboxLeftBumperLastState = false;
    private boolean xboxRightBumperLastState = false;

    private PID pid;
    
    public OperatorSystem()
    {
        leftJoystick = new AdvJoystick(1);
        rightJoystick = new AdvJoystick(2);
        
        xboxJoystick = new XboxJoystick(3);

        pid = new PID(0.1, 0, 0);
        pid.setTarget(1.0);
        pid.setOutputRange(-0.2, 0.2);
        
        pid.enable();
    }
    
    public static OperatorSystem getInstance()
    {
        if(instance == null) instance = new OperatorSystem();
        return instance;
    }
    
    public void tick()
    {
        DriveSystem ds = DriveSystem.getInstance();
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        
        pid.feed(leftJoystick.getStickY());
        Log.defcon2(this, "Pid output: "+pid.get());
        
        //ds.setTankSpeed(-leftJoystick.getStickY(), -rightJoystick.getStickY());
        
        /*
        if(!xboxJoystick.getRightTrigger() > 0.5)
        {
            ms.setLiftSpeed(xboxJoystick.getRightStickY());
        }
        else
        {
            ms.enableCamPid();
        }
        
        ms.setArmSpeed(xboxJoystick.getLeftStickY());
        */
    }
}
