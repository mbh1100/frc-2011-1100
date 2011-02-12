package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;

public class ManipulatorSystem extends SystemBase
{
    private AdvJaguar lift;
    private AdvJaguar arm;
    private Solenoid wrist;
    private Solenoid claw;
    
    public ManipulatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
    }

    public void setLiftSpeed(double speed)
    {
        lift.set(speed);
    }

    public void setArm(double speed)
    {
        arm.set(speed);
    }

    public void openClaw()
    {
        log("Open claw");
        setClaw(true);
    }

    public void closeClaw()
    {
        log("Close claw");
        setClaw(true);
    }

    public void setClaw(boolean state)
    {
        log("Set claw" + state);
        claw.set(state);
    }

    public void toggleClaw()
    {
        log("toggle claw");
        claw.set(!claw.get());
    }
    
    public void raiseWrist()
    {
        log("Raise wrist");
        setWrist(true);
    }

    public void lowerWrist()
    {
        log("Lower wrist");
        setWrist(true);
    }

    public void setWrist(boolean state)
    {
        log("Set wrist" + state);
        wrist.set(state);
    }

    public void toggleWrist()
    {
        log("Toggle wrist");
        wrist.set(!wrist.get()); }
    
    public void tick()
    {
        arm.update();
        lift.update();

        // TODO: have POTs on each motor, compare each value, and make
        // limiting code to make sure the arm can't crash to the ground
    }
}