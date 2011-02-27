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

import edu.arhs.first1100.log.Log;

/**
 *
 * @author team1100
 */
public class RobotMain extends SimpleRobot
{
    public void robotInit()
    {
        Log.setDefconLevel(3);
        Log.addClass(AutonomousSystem.class);
        
        Log.defcon3(this, "Robot Init");
        
        OperatorSystem.getInstance().setSleep(100);
        AutonomousSystem.getInstance().setSleep(100);
        
        DriveSystem.getInstance().setSleep(100);
        ManipulatorSystem.getInstance().setSleep(100);

        CameraSystem.getInstance().setSleep(100);
        LineSystem.getInstance().setSleep(100);

        MinibotSystem.getInstance().setSleep(100);
    }
    
    public void autonomous()
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

    public void operatorControl()
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
    
    public void disabled()
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