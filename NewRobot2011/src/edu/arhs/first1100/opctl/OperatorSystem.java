/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.opctl;

import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.util.SystemBase;

import edu.arhs.first1100.log.Log;

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
    
    private OperatorSystem()
    {
        leftJoystick = new AdvJoystick(1);
        rightJoystick = new AdvJoystick(2);
        
        xboxJoystick = new XboxJoystick(3);
    }
    
    public static OperatorSystem getInstance()
    {
        if(instance == null) instance = new OperatorSystem();
        return instance;
    }
    
    public void tick()
    {
        /*
         * !!!! A NOTE ON JOYSTICK INVERSION !!!!
         *
         * All systems will take positive values to go forward, negative to go back
         * ex. left wheels: 1.0 forward, -1.0 backward
         *     lift: 1.0 for up, -1.0 for down
         *
         * The driver joysticks are inverted here because they return -1.0 when
         * pushed forward.
         *
         * The xbox does not need inversion because when an axis is pushed
         * forward, it returns 1.0.
         *
         * IF YOU NEED TO INVERT A MOTOR, DO SO IN THE SYSTEM IT IS DECLARED,
         * DON'T INVERT THE JOYSTICK
         * 
         */

        DriveSystem ds = DriveSystem.getInstance();
        ManipulatorSystem ms = ManipulatorSystem.getInstance();
        
        /*
         * Arm Controls
         */
        if(xboxJoystick.getRightTrigger() > 0.5)
        {
            Log.defcon2(this, "Using CamPID");
            ms.useCamPID();
        }
        else
        {
            if(Math.abs(xboxJoystick.getRightStickY()) > 0.20)
            {
                ms.setLiftSpeed(xboxJoystick.getRightStickY());
            }
            else if(ms.getLiftSpeed() > 0.0)
            {
                ms.setLiftSpeed(0.0);
            }
            else
            {
                if(xboxJoystick.getXButton())
                {
                    Log.defcon2(this, "Setting state to default");
                    ms.setState(ManipulatorSystem.STATE_DEFAULT);
                }
                else if(xboxJoystick.getAButton())
                {
                    Log.defcon2(this, "Setting state to bottom");
                    ms.setState(ManipulatorSystem.STATE_BOTTOM_PEG);
                }
                else if(xboxJoystick.getBButton())
                {
                    Log.defcon2(this, "Setting state to middle");
                    ms.setState(ManipulatorSystem.STATE_MIDDLE_PEG);
                }
                else if(xboxJoystick.getYButton())
                {
                    Log.defcon2(this, "Setting state to top");
                    ms.setState(ManipulatorSystem.STATE_TOP_PEG);
                }
                else if(xboxJoystick.getDpad() == 1.0)
                {
                    Log.defcon2(this, "Setting state to floor");
                    ms.setState(ManipulatorSystem.STATE_FLOOR);
                }
            }
        }
        
        if(Math.abs(xboxJoystick.getLeftStickY()) > 0.20)
        {
            ms.setArmSpeed(xboxJoystick.getLeftStickY());
        }
        else
        {
            ms.setArmPosition(ms.getArmEncoder());
        }

        // Left Toggle Button
        if(xboxJoystick.getLeftBumper() && !xboxLeftBumperLastState)
        {
            ManipulatorSystem.getInstance().toggleClaw();
            xboxLeftBumperLastState = true;
        }
        if(!xboxJoystick.getLeftBumper())
            xboxLeftBumperLastState = false;

        // Right Bumper Toggle
        if(xboxJoystick.getRightBumper() && !xboxRightBumperLastState)
        {
            ManipulatorSystem.getInstance().toggleWrist();
            xboxRightBumperLastState = true;
        }
        if(!xboxJoystick.getRightBumper())
            xboxRightBumperLastState = false;
        
        /*
         * Drive Controls
         */
        ds.setTankSpeed(-leftJoystick.getStickY(), -rightJoystick.getStickY());
        
    }
}
