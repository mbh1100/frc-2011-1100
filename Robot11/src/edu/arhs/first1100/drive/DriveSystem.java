/**
 * DriveSystem.java
 *
 * This class contains all motor objects related to driving, and code for
 * managing the motors.
 *
 * So far, we control 4 motors.
 * left and right tank drive, a lift motor, and the drive motor for side stepping.
 *
 */

package edu.arhs.first1100.drive;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.drive.JaguarPair;
import edu.arhs.first1100.opctl.OperatorSystem;

public class DriveSystem extends SystemBase
{
    private final int STATE_TANK = 0;
    private final int STATE_RAISING = 1;
    private final int STATE_LOWERING = 2;
    private final int STATE_SIDESTEP = 3;

    private int state = STATE_TANK;
    
    private JaguarPair leftTankMotor;
    private JaguarPair rightTankMotor;

    private AdvJaguar liftMotor; //motor that raises lowers the side step wheels
    private AdvJaguar sidestepDriveMotor; // drives the side step wheel
    
    public DriveSystem(RobotMain robot, int sleepTime)
    {
         super(robot, sleepTime);
         
         // JaguarPair(ch1, ch2, invert, averager sample size);
         leftTankMotor  = new JaguarPair(1, 3, false, 3);
         rightTankMotor = new JaguarPair(2, 4, true, 3);
         
         sidestepDriveMotor = new AdvJaguar(5);
         
         //this.setDriveSpeed(0.0, 0.0);
    }
    
    public void setDriveSpeed(double leftSide, double rightSide)
    {
        if(state == STATE_TANK)
        {
            leftTankMotor.set(leftSide);
            rightTankMotor.set(rightSide);
        }
        else
        {
            leftTankMotor.set(0.0);
            rightTankMotor.set(0.0);
        }
    }

    public void setSideSpeed(double speed)
    {
        if(state == STATE_SIDESTEP)
        {
            sidestepDriveMotor.set(speed);
        }
        else
        {
            sidestepDriveMotor.set(0.0);
        }
    }
    
    public void tick()
    {
 // hi. I am required to say that I have done somthing involving programming the robot. This is what I have done. Love, Dr. Ryan Samuel Giblin III
        leftTankMotor.update();
        rightTankMotor.update();
        //liftMotor.update();
        sidestepDriveMotor.update();

        if(false)
        {
            log("tick!");
            log("Left: "+leftTankMotor.get());
            log("Right:"+rightTankMotor.get());
            log("");
        }
        
        if(state == STATE_RAISING)
        {
            // move motors up based on POT value
            // when wheels are all the way up, state = STATE_TANK
        }
        else if(state == STATE_LOWERING)
        {
            // move motors down slowly basd on POT reading
            // when wheels are all the way down, state = STATE_SIDESTEP
        }
    }
    
    public void setDriveModeTank()
    {
        if(state == STATE_SIDESTEP)
            state = STATE_RAISING;
    }

    public void setDriveModeSideStep()
    {
        if(state == STATE_TANK)
            state = STATE_LOWERING;
    }
}
