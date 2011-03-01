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
    public Compressor compressor;

    public DiagnosticRobot diagnosticRobot;
    private DigitalInput diagSwitch;

    boolean diagnostic = true;
    /**
     *
     */
    public void robotInit()
    {
        diagSwitch = new DigitalInput(13);
        diagnostic = !diagSwitch.get();
        if(!diagnostic)
        {
            log("+------------------------+");
            log("| USING REGULAR ROBOT    |");
            log("+------------------------+");
            autonomousSystem = AutonomousSystem.getInstance();
            operatorSystem = OperatorSystem.getInstance();

            cameraSystem = CameraSystem.getInstance();
            driveSystem = DriveSystem.getInstance();
            lineSystem = LineSystem.getInstance();
            manipulatorSystem = ManipulatorSystem.getInstance();
            minibotSystem = MinibotSystem.getInstance();
        }
        else if (diagnostic)
        {
            log("+------------------------+");
            log("| USING DIAGNOSTIC ROBOT |");
            log("+------------------------+");
            diagnosticRobot = DiagnosticRobot.getInstance();
        }
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
        log("AUTONOMOUS");

        if(!diagnostic)
        {
            operatorSystem.stop();
            autonomousSystem.start();

            cameraSystem.start();
            manipulatorSystem.start();
            driveSystem.start();
            lineSystem.start();
            minibotSystem.start();
        }
        else if (diagnostic)
        {
            log("DIAGNOSTIC AUTONOMOUS");
        }
    }

    /**
     *
     */
    public void operatorControl()
    {
        if (!diagnostic){

            log("Enabling teleop");

            log("Starting opsys");
            operatorSystem.start();

            log("stopping ausys");
            autonomousSystem.stop();

            log("Starting cam, drive, manip");
            cameraSystem.start();
            manipulatorSystem.start();
            driveSystem.start();
            lineSystem.start();
            minibotSystem.start();

            log("Enabled teleop");
        }
        else if (diagnostic)
        {
            while(isOperatorControl())
            {
                diagnosticRobot.teleop();
                Timer.delay(.1);
            }
        }
    }
    
    /**
     * 
     */
    public void disabled()
    {
        if (!diagnostic)
        {
            log("Enabling disabling...");

            log("stopping opsys");
            operatorSystem.stop();

            log("stopping ausys");
            autonomousSystem.stop();

            log("stopping cam, line, manip, and drive");
            cameraSystem.stop();
            manipulatorSystem.stop();
            driveSystem.stop();
            lineSystem.stop();
            minibotSystem.stop();

            log("disabled");
        }
        else if(diagnostic)
        {
            diagnosticRobot.disable();
        }
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
