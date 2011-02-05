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
        autonomousSystem = new AutonomousSystem();
        //cameraSystem = new CameraSystem();
        driveSystem = new DriveSystem();
        //lineSystem = new LineSystem();
        //manipulatorSystem = new ManipulatorSystem();
        //minibotSystem = new MinibotSystem();
        operatorSystem = new OperatorSystem();
        
        // We need a MUCH better system than this.
        // Right now I pass a reference to RobotMain
        // so Systems can access other Systems.
        //
        // We need a ".getInstance()" instead.
        // This is a work around for now.
        //    - Nick
        autonomousSystem.setRobotMain(this);
        //cameraSystem.setRobotMain(this);
        driveSystem.setRobotMain(this);
        //lineSystem.setRobotMain(this);
        //manipulatorSystem.setRobotMain(this);
        //minibotSystem.setRobotMain(this);
        operatorSystem.setRobotMain(this);


        // Eventually we'll define sleep time as final variables in each
        // system's class
        //autonomousSystem.setSleep(50);
        //cameraSystem.setSleep(50);
        //driveSystem.setSleep(100);
        //lineSystem.setSleep(200);
        //manipulatorSystem.setSleep(100);
        //minibotSystem.setSleep(500);
        //operatorSystem.setSleep(50);

        // operator & autonomous threads started/stopped by teleopInit and autonomousInit
        
        //autonomousSystem.start();
        //cameraSystem.start();
        //driveSystem.start();
        //lineSystem.start();
        //manipulatorSystem.start();
        //minibotSystem.start();
        //operatorSystem.start();
        /*System.out.println("robot init");

        System.out.println("Starting thread");
        operatorSystem.start();
        Timer.delay(3);

        System.out.println("Stopping thread");
        operatorSystem.stop();
        Timer.delay(3);

        System.out.println("Starting thread");
        operatorSystem.start();

        System.out.println("If you can read this, it worked!!!");
*/
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
        driveSystem.start();
        operatorSystem.start();
        log("enabled teleop");
    }
    
    public void disabled()
    {
        autonomousSystem.stop();
        //cameraSystem.stop();
        driveSystem.stop();
        //lineSystem.stop();
        //manipulatorSystem.stop();
        //minibotSystem.stop();
        operatorSystem.stop();
        log("disabled");
    }

    public void log(String message)
    {
        System.out.println("RobotMain: " + message);
    }
}