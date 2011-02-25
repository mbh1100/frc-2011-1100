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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team1100
 */
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
        autonomousSystem = new AutonomousSystem(100);
        operatorSystem = new OperatorSystem(100);

        cameraSystem = new CameraSystem(100);
        driveSystem = new DriveSystem(100);
        lineSystem = new LineSystem(100);
        manipulatorSystem = new ManipulatorSystem(100);
        minibotSystem = new MinibotSystem(100);
    }
    
    public void autonomous()
    {
        operatorSystem.stop();
        autonomousSystem.start();
        
        cameraSystem.start();
        manipulatorSystem.start();
        driveSystem.start();
        lineSystem.start();
        minibotSystem.start();
    }

    public void operatorControl()
    {
        operatorSystem.start();
        autonomousSystem.stop();
        
        cameraSystem.start();
        manipulatorSystem.start();
        driveSystem.start();
        lineSystem.start();
        minibotSystem.start();
    }
    
    public void disabled()
    {
        operatorSystem.stop();
        autonomousSystem.stop();

        cameraSystem.stop();
        manipulatorSystem.stop();
        driveSystem.stop();
        lineSystem.stop();
        minibotSystem.stop();

        log("disabled");
    }
    
    public void log(String message)
    {
        System.out.println("RobotMain: " + message);
    }
}
