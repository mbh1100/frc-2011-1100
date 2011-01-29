/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.arhs.first1100.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.arhs.first1100.autoctl.AutonomousSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.minibot.MinibotSystem;
import edu.arhs.first1100.opctl.OperatorSystem;

public class RobotMain extends IterativeRobot
{
    AutonomousSystem autonomousSystem;
    CameraSystem cameraSystem;
    DriveSystem driveSystem;
    LineSystem lineSystem;
    ManipulatorSystem manipulatorSystem;
    MinibotSystem minibotSystem;
    OperatorSystem operatorSystem;

    public void robotInit()
    {
        autonomousSystem = new AutonomousSystem();
        cameraSystem = new CameraSystem();
        driveSystem = new DriveSystem();
        lineSystem = new LineSystem();
        manipulatorSystem = new ManipulatorSystem();
        minibotSystem = new MinibotSystem();
        operatorSystem = new OperatorSystem();
        
        autonomousSystem.start();
        cameraSystem.start();
        driveSystem.start();
        lineSystem.start();
        manipulatorSystem.start();
        minibotSystem.start();
        operatorSystem.start();
    }

    // Periodic = called when driver station data is updated
    // Continuous = called as fast as possible
    
    public void autonomousInit() { }
    public void autonomousPeriodic() { }

    public void teleopInit() { }
    public void teleopPeriodic() { }

    public void disabledInit() { }
}