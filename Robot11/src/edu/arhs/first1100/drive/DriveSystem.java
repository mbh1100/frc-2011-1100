/**
 * DriveSystem.java
 *
 * This class contains all motor objects related to driving, and code for
 * managing the motors.
 *
 * Nick - 3:24pm 1/29
 */

package edu.arhs.first1100.drive;

import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 *
 * @author team1100
 */
public class DriveSystem extends SystemBase
{
    private JaguarPair leftMotor;
    private JaguarPair rightMotor;
    
    public DriveSystem() 
    {
         super();
         this.setDriveSpeed(0.0, 0.0);
    }

    public void setDriveSpeed(double leftSide, double rightSide)
    {
       leftMotor.set(leftSide);
       rightMotor.set(rightSide);
    }

    public void tick()
    {
        
    }
}
