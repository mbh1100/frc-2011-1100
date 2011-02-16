package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;

public class ManipulatorSystem extends SystemBase
{
    public static final int STATE_NONE = 0;
    public static final int STATE_VALUE = 1;
    public static final int STATE_CAMERA = 2;
    
    private int state = 0;
    
    private Lift lift;
    private AdvJaguar arm;
    
    private Solenoid wrist;
    private Solenoid claw;
    
    private Encoder liftEncoder;
    
    public ManipulatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        lift = new Lift();
    }

    public void setLiftSpeed(double speed)
    {
        lift.setSpeed(speed);
    }

    public void setLiftHeight(double height)
    {
        lift.setHeight(height);
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
        wrist.set(!wrist.get());
    }

    public void setPIDState(int state)
    {
        this.state = state;
    }

    public boolean PIDOnTarget()
    {
        return (lift.getPidError() <= 1);
    }

    public void tick()
    {
        //arm.update();
        lift.update();
    }
}