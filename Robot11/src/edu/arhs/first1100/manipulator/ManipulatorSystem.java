package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.camera.YPIDSource;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;

/**
 *what peg the robot should go for and how the robot figures out what peg to go for
 * @author team1100
 */
public class ManipulatorSystem extends SystemBase
{
    /*
    public static final double PEG_TOP1 = 245;
    public static final double PEG_MIDDLE1 = 80.7;
    public static final double PEG_BOTTOM1 = 10;
    
    public static final double PEG_TOP2 = 245;
    public static final double PEG_MIDDLE = 105;
    public static final double PEG_BOTTOM2 = 10;
    */
    
    public static final int STATE_DEFAULT    = 0;
    
    public static final int STATE_TOP_PEG    = 3;
    public static final int STATE_MID_PEG    = 2;
    public static final int STATE_BOTTOM_PEG = 1;
    
    public static final int STATE_FEEDER     = 4;
    public static final int STATE_FLOOR      = 5;

    private int state = STATE_DEFAULT;
    
    public Lift lift;
    public Arm arm;
    
    private Solenoid wrist;
    private Solenoid claw;

    
    /**
     *when to lift the arm claw and wrist and where they are
     * @param robot
     * @param sleepTime
     */
    public ManipulatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        
        lift = new Lift(new YPIDSource(robot.cameraSystem));
        arm = new Arm();
        
        wrist = new Solenoid(2);
        claw = new Solenoid(1);
    }

    /**
     * sets the state of the robot
     * @param state
     */
    public void setState(int state)
    {
        log("Set State:"+state);
        this.state = state;

        switch(state)
        {
            case STATE_TOP_PEG:
                lift.setState(Lift.STATE_HIGH);
                arm.setState(Arm.STATE_HIGH);
                //raiseWrist();
                break;
            case STATE_MID_PEG:
                lift.setState(Lift.STATE_MID);
                arm.setState(Arm.STATE_MID);
                //raiseWrist();
                break;
            case STATE_BOTTOM_PEG:
                lift.setState(Lift.STATE_LOW);
                arm.setState(Arm.STATE_LOW);
                //raiseWrist();
                break;
                /*
            case STATE_FEEDER:
                lift.setState(Lift.STATE_LOW);
                arm.setState(Arm.STATE_MID);
                //raiseWrist();
                break;
            case STATE_FLOOR:
                lift.setState(Lift.STATE_MID);
                arm.setState(Arm.STATE_LOW);
                //lowerWrist();
                break;*/
            case STATE_DEFAULT:
                lift.setState(Lift.STATE_LOW);
                arm.setState(Arm.STATE_HIGH);
                claw.set(false);
                //raiseWrist();
                break;
        }
    }

    public int getState()
    {
        return state;
    }
    
    /**
     *how fast the lift moves
     * @param speed
     */
    public void setLiftSpeed(double speed)
    {
        lift.setSpeed(speed);
    }
    
    public void setLiftHeight(double height)
    {
        lift.setHeight(height);
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

    public void setArmHeight(double height)
    {
        arm.setHeight(height);
    }
    
    /**
     * Grabber controls
     */
    
    public void releaseTube()
    { setClawState(false); }
    
    public void grabTube()
    { setClawState(true); }

    public void setClawState(boolean state)
    { claw.set(state); }

    public void toggleClaw()
    { claw.set(!claw.get()); }

    public void raiseWrist()
    { setWristState(false); }
    
    public void lowerWrist()
    { setWristState(true); }

    public void setWristState(boolean state)
    { wrist.set(state); }
    
    public void toggleWrist()
    { wrist.set(!wrist.get()); }
    
    /**
     *what to do when on target
     * @return
     */
    public boolean liftOnTarget()
    {
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
        /*
        log("Lift PID target: " + lift.camPid.getSetpoint());
        log("Lift Encoder:    " + lift.encoder.get());
        log("Lift PID output: " + lift.camPid.get());
        log();
        */
        
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

    public void resetEncoders()
    {
        arm.resetEncoder();
        lift.resetEncoder();
    }
}