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
    public void robotInit()
    {
        AutonomousSystem.getInstance().setSleep(100);
    }
    
    public void autonomous()
    {
        AutonomousSystem.getInstance().start();
    }

    public void operatorControl()
    {
        AutonomousSystem.getInstance().stop();
    }
    
    public void disabled()
    {
        AutonomousSystem.getInstance().stop();
    }
}
