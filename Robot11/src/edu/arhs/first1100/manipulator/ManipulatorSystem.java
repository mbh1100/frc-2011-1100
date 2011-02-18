package edu.arhs.first1100.manipulator;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;

public class ManipulatorSystem extends SystemBase
{
    

    public static final double PEG_TOP1 = 245;
    public static final double PEG_MIDDLE1 = 80.7;
    public static final double PEG_BOTTOM1 = 10;
    
    public static final double PEG_TOP2 = 245;
    public static final double PEG_MIDDLE = 105;
    public static final double PEG_BOTTOM2 = 10;
    
    public static final int STATE_DEFAULT    = 0;

    public static final int STATE_TOP_PEG    = 3;
    public static final int STATE_MID_PEG    = 2;
    public static final int STATE_BOTTOM_PEG = 1;
    
    public static final int STATE_FEEDER     = 4;
    public static final int STATE_FLOOR      = 5;

    private int state = STATE_DEFAULT;
    
    private Lift lift;
    private Arm arm;
    
    private Solenoid wrist;
    private Solenoid claw;
    
    public ManipulatorSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        
        lift = new Lift();
        arm = new Arm();
        
        wrist = new Solenoid(2);
        claw = new Solenoid(1);
    }

    public void setLiftSpeed(double speed)
    {
        lift.setSpeed(speed);
    }

    public void setArm(double speed)
    {
        arm.setSpeed(speed);
    }

    public void setState(int state)
    {
        log("Set State:"+state);
        
        switch(state)
        {
            case STATE_TOP_PEG:
                lift.setState(Lift.STATE_HIGH);
                arm.setState(Arm.STATE_HIGH);
                raiseWrist();
                break;
            case STATE_MID_PEG:
                lift.setState(Lift.STATE_LOW);
                arm.setState(Arm.STATE_MID);
                raiseWrist();
                break;
            case STATE_BOTTOM_PEG:
                lift.setState(Lift.STATE_MID);
                arm.setState(Arm.STATE_LOW);
                raiseWrist();
                break;
            case STATE_FEEDER:
                lift.setState(Lift.STATE_LOW);
                arm.setState(Arm.STATE_MID);
                raiseWrist();
                break;
            case STATE_FLOOR:
                lift.setState(Lift.STATE_MID);
                arm.setState(Arm.STATE_LOW);
                lowerWrist();
                break;
            case STATE_DEFAULT:
                lift.setState(Lift.STATE_LOW);
                arm.setState(Arm.STATE_HIGH);
                raiseWrist();
                break;
        }
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
    
    public boolean liftOnTarget()
    {
        return (lift.getPidError() <= 1);
    }

    public boolean armPIDOnTarget()
    {
        return (arm.getPidError() <= 1);
    }
    
    public void tick()
    {
        //arm.update();
        //lift.update();
        
    }
}