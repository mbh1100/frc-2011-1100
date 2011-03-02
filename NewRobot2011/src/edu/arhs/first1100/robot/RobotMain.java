/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.arhs.first1100.robot;

import edu.wpi.first.wpilibj.SimpleRobot;

import edu.arhs.first1100.autoctl.AutonomousSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.minibot.MinibotSystem;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.arhs.first1100.diag.DiagnosticRobot;

import edu.arhs.first1100.log.Log;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author team1100
 */
public class RobotMain extends SimpleRobot
{
    private DigitalInput diagSwitch;
    private boolean diagnostic;
    private DiagnosticRobot diagRobot;

    public void robotInit()
    {        
        Log.defcon3(this, "Robot Init");

        diagSwitch = new DigitalInput(13);
        diagnostic = !diagSwitch.get();


        if(!diagnostic)
        {
            //Set Logging Levels
            Log.addClass(MinibotSystem.class, 1);
            Log.addClass(OperatorSystem.class, 2);
            Log.addClass(RobotMain.class, 3);
            OperatorSystem.getInstance().setSleep(100);
            AutonomousSystem.getInstance().setSleep(100);

            DriveSystem.getInstance().setSleep(100);
            ManipulatorSystem.getInstance().setSleep(100);

            CameraSystem.getInstance().setSleep(100);
            LineSystem.getInstance().setSleep(100);

            MinibotSystem.getInstance().setSleep(100);
        }
        else
        {
            diagRobot = new DiagnosticRobot();
        }
            Log.defcon3(this, "+------------------------+");
            Log.defcon3(this, "| USING "
                    +((diagnostic)?"DIAGNOSTIC ":"  REGULAR ")
                    +"ROBOT |");
            Log.defcon3(this, "+------------------------+");
    }
    
    public void autonomous()
    {
        if(!diagnostic)
        {
            Log.defcon3(this, "Autonomous Mode Activated");

            OperatorSystem.getInstance().stop();
            AutonomousSystem.getInstance().start();

            DriveSystem.getInstance().start();
            ManipulatorSystem.getInstance().start();

            CameraSystem.getInstance().start();
            LineSystem.getInstance().start();

            MinibotSystem.getInstance().start();
        }
    }

    public void operatorControl()
    {
        if(!diagnostic)
        {
            Log.defcon3(this, "Operator Mode Activated");

            OperatorSystem.getInstance().start();
            AutonomousSystem.getInstance().stop();

            DriveSystem.getInstance().start();
            ManipulatorSystem.getInstance().start();

            CameraSystem.getInstance().start();
            LineSystem.getInstance().start();

            MinibotSystem.getInstance().start();
        }
        else
        {
            while(this.isOperatorControl())
            {
                diagRobot.teleop();
            }
        }
    }
    
    public void disabled()
    {
        if(!diagnostic)
        {
            Log.defcon3(this, "Robot Disabled");

            OperatorSystem.getInstance().stop();
            AutonomousSystem.getInstance().stop();

            DriveSystem.getInstance().stop();
            ManipulatorSystem.getInstance().stop();

            CameraSystem.getInstance().stop();
            LineSystem.getInstance().stop();

            MinibotSystem.getInstance().stop();
        }
    }
}