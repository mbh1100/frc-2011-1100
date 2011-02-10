/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
 * Go team 1100!!!
 */

package edu.arhs.first1100.robot;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

import edu.arhs.first1100.autoctl.AutonomousSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.minibot.MinibotSystem;
import edu.arhs.first1100.opctl.OperatorSystem;

public class RobotMain extends SimpleRobot
{
    public AutonomousSystem autonomousSystem;
    public CameraSystem cameraSystem;
    public DriveSystem driveSystem;
    public LineSystem lineSystem;
    public ManipulatorSystem manipulatorSystem;
    public MinibotSystem minibotSystem;
    public OperatorSystem operatorSystem;

    public void robotInit()
    {
        autonomousSystem = new AutonomousSystem(this, 100);
        operatorSystem = new OperatorSystem(this, 100);
        cameraSystem = new CameraSystem(this, 100);
        driveSystem = new DriveSystem(this, 100);
        lineSystem = new LineSystem(this, 100);
        manipulatorSystem = new ManipulatorSystem(this, 100);
        minibotSystem = new MinibotSystem(this, 100);
        
        // operator & autonomous threads started/stopped by teleopInit and autonomousInit
        
        cameraSystem.start();
        driveSystem.start();
        lineSystem.start();
        manipulatorSystem.start();
        minibotSystem.start();
    }

    // Periodic = called when driver station data is updated
    // Continuous = called as fast as possible
    
    public void autonomous()
    {
        operatorSystem.stop();
        autonomousSystem.start();
        log("enabled autonomous");
    }
    
    public void operatorControl()
    {
        autonomousSystem.stop();
        operatorSystem.start();
        log("enabled teleop");
    }
    
    public void disabled()
    {
        autonomousSystem.stop();
        cameraSystem.stop();
        lineSystem.stop();
        manipulatorSystem.stop();
        minibotSystem.stop();
        operatorSystem.stop();
        log("disabled");
    }

    public void log(String message)
    {
        System.out.println("RobotMain: " + message);
    }
}
