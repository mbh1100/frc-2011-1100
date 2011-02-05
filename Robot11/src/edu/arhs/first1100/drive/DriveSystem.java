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

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.util.AdvJaguar;

public class DriveSystem extends SystemBase
{
    private final int STATE_TANK = 0;
    private final int STATE_RAISING = 1;
    private final int STATE_LOWERING = 2;
    private final int STATE_SIDESTEP = 3;

    private int state;
    
    private JaguarPair leftTankMotor;
    private JaguarPair rightTankMotor;

    private AdvJaguar liftMotor; //motor that raises lowers the side step wheels
    private AdvJaguar sidestepDriveMotor; // drives the side step wheel
    
    public DriveSystem() 
    {
         super();
         this.setDriveSpeed(0.0, 0.0);
         leftTankMotor = new JaguarPair(1,2);
         leftTankMotor = new JaguarPair(3,4);
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
        leftTankMotor.update();
        rightTankMotor.update();
        liftMotor.update();
        sidestepDriveMotor.update();

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
