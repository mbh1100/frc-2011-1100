package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.CameraSystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.AdvJaguar;

/**
 *what peg the robot should go for and how the robot figures out what peg to go for
 * @author team1100
 */
public class ManipulatorSystem extends SystemBase
{

    static private ManipulatorSystem instance;
    static private int sleepTime = 100;

    public  Lift lift;
    public  Arm arm;
    
    private Solenoid wrist;
    private Solenoid claw;

    
    /**
     *when to lift the arm claw and wrist and where they are
     * @param robot
     * @param sleepTime
     */
    private ManipulatorSystem()
    {
        super(sleepTime);
        
        lift = new Lift();
        arm = new Arm();
        
        wrist = new Solenoid(2);
        claw = new Solenoid(1);
    }

    public static ManipulatorSystem getInstance()
    {
        if(instance == null) instance = new ManipulatorSystem();
        return instance;
    }
    
    /**
     *how fast the lift moves
     * @param speed
     */
    public void setLiftSpeed(double speed)
    {
        lift.setSpeed(speed);
    }

    public void setLiftPosition(double height)
    {
        lift.setPosition(height);
    }

    public double getLiftHeight()
    {
        return lift.getEncoder();
    }
    
    /**
     *how fast the arm moves
     * @param speed
     */
    public void setArmSpeed(double speed)
    {
        arm.setSpeed(speed);
    }

    public void setArmPosition(double height)
    {
        arm.setPosition(height);
    }

    public double getArmPosition()
    {
        return arm.getEncoder();
    }
    
    /**
     * Grabber controls
     */
    
    public void releaseTube()
    { setClawState(true); }
    
    public void grabTube()
    { setClawState(false); }

    private void setClawState(boolean state)
    { claw.set(state); }

    public void toggleClaw()
    { setClawState(!claw.get()); }

    public void raiseWrist()
    { setWristState(true); }
    
    public void lowerWrist()
    { setWristState(false); }

    private void setWristState(boolean state)
    { wrist.set(state); }
    
    public void toggleWrist()
    { setWristState(!wrist.get()); }
    
    /**
     *what to do when on target
     * @return
     */
    public boolean liftOnTarget()
    {
        //System.out.println("lift on Target----------------------------------------------");
        return (lift.getPidError() <= 1);
    }
    
    /**
     *what to do when on target
     * @return
     */
    public boolean armOnTarget()
    {
        return (arm.getPidError() <= 1);
    }
    
    /**
     *what to do when tick happens
     */
    public void tick()
    {
        
        //log("Lift PID target: " + lift.camPid.getSetpoint());
        //log("Lift Encoder:    " + lift.encoder.get());
        //log("Lift PID output: " + lift.camPid.get());
        //log();
        
        
        // log("Arm PID target: " + arm.pid.getSetpoint());
        // log("Arm encoder:    " + arm.encoder.get());
        //log("Arm PID output: " + arm.pid.get());
        //log("Arm Encoder:"  + robot.manipulatorSystem.arm.encoder.get());
        
        if(liftOnTarget() && lift.pidEnabled())
        {
            log("Target Reached: Disabling pid");
            lift.stopPID();
        }
    }

    public void disable()
    {
        lowerWrist();
        grabTube();
    }
    public void resetEncoders()
    {
        arm.resetEncoder();
        lift.resetEncoder();
    }
}
