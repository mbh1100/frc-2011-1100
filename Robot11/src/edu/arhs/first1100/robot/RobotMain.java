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

import edu.arhs.first1100.autoctl.AutonomousSystem;
import edu.arhs.first1100.camera.CameraSystem;
import edu.arhs.first1100.drive.DriveSystem;
import edu.arhs.first1100.line.LineSystem;
import edu.arhs.first1100.manipulator.ManipulatorSystem;
import edu.arhs.first1100.minibot.MinibotSystem;
import edu.arhs.first1100.opctl.OperatorSystem;
import edu.wpi.first.wpilibj.Compressor;
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
    public Compressor compressor;
/**
 *
 */
    public void robotInit()
    {
        autonomousSystem = new AutonomousSystem(this, 100);
        operatorSystem = new OperatorSystem(this, 100);
        
        cameraSystem = new CameraSystem(this, 100);
        driveSystem = new DriveSystem(this, 100);
        lineSystem = new LineSystem(this, 100);
        manipulatorSystem = new ManipulatorSystem(this, 100);
        minibotSystem = new MinibotSystem(this, 100);

        compressor = new Compressor(6, 1);
        compressor.start();
        
        // operator & autonomous threads started/stopped by teleopInit and autonomousInit
        
    }

    // Periodic = called when driver station data is updated
    // Continuous = called as fast as possible
   /**
    *
    */
    public void autonomous()
    {
        operatorSystem.stop();
        autonomousSystem.start();
        
        cameraSystem.start();
        driveSystem.start();
        manipulatorSystem.start();
        minibotSystem.start();

        log("enabled autonomous");
    }

    /**
     *
     */
    public void operatorControl()
    {
        operatorSystem.start();
        autonomousSystem.stop();
        
        cameraSystem.start();
        driveSystem.start();
        manipulatorSystem.start();
        minibotSystem.start();
        
        log("Enabled teleop");
    }
    
    /**
     *
     */
    public void disabled()
    {
        operatorSystem.stop();
        autonomousSystem.stop();
        
        cameraSystem.stop();
        lineSystem.stop();
        manipulatorSystem.stop();
        minibotSystem.stop();
        
        log("disabled");
    }
    
/**
 *
 * @param message
 */
    public void log(String message)
    {
        System.out.println("RobotMain: " + message);
    }
}
