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
import edu.arhs.first1100.camera.CameraSystem;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;
import edu.arhs.first1100.opctl.OperatorSystem;
/**
 *declares how the robot is to drive.
 * @author team1100
 */
public class DriveSystem extends SystemBase
{
    static private DriveSystem instance;
    static private int sleepTime = 500;

    private double pidCurve;
    private double pidPower;

    private AdvJaguar rightTankmotor1;
    private AdvJaguar rightTankmotor2;
    private AdvJaguar leftTankmotor1;//left tank 1
    private AdvJaguar leftTankmotor2;//left tank 2
    private RobotDrive rd;
    private SteerPid cdc;
    private PowerPid cdp;

    /**
     * access the DriveSystem singleton
     * @return the one and only Drive system object
     */
    public static DriveSystem getInstance()
    {
        if(instance == null) instance = new DriveSystem();
        return instance;
    }
/**
 *states what motors go with what jags
 * @param robot
 * @param sleepTime
 */
    private DriveSystem()
    {
         super(sleepTime);
         
         // JaguarPair(ch1, ch2, invert, averager sample size);
         leftTankmotor1  = new AdvJaguar(2, false);
         rightTankmotor1 = new AdvJaguar(1, true);
         leftTankmotor2 =  new AdvJaguar(4, false);
         rightTankmotor2 = new AdvJaguar(3, true);
         
         rd = new RobotDrive(leftTankmotor1, leftTankmotor2 , rightTankmotor1, rightTankmotor2);
         
         cdc = new SteerPid();
         cdp = new PowerPid();
    }
    
    /**
     *says how fast to go
     * declares camera drive also
     * @param speed
     */
    public void enableCameraSteering()
    {
        // log("cdc.trackCamera()");
        cdc.enable();
    }

    public void enableCameraDriving(double targetArea)
    {   
        // log("cdp.trackCamera()");
        cdp.setSetpoint(targetArea);
        cdp.enable();
    }

    public void disableCameraControl()
    {
        cdc.disable();
        cdp.disable();
    }

    void setPidCurve(double curve)
    {
        pidCurve = curve;
        driveByPid();
    }

    public void setPidPower(double power)
    {
        pidPower = power;
        driveByPid();
    }

    void driveByPid()
    {
        rd.drive(pidPower, pidCurve);
    }

    /**
     *declares robot drive
     * @param leftSide
     * @param rightSide
     */
    public void setDriveSpeed(double leftSide, double rightSide)
    {
        disableCameraControl();
        rd.tankDrive(leftSide, rightSide);

        //log("setDriveSpeed(): " + leftTankmotor1.get() + " : " + rightTankmotor1.get());
    }
    /**
     *
     * @param power
     * @param curve
     */
    public void drive(double power, double curve)
    {
        disableCameraControl();
        rd.drive(power, curve);

        //log("drive() :" + leftTankmotor1.get() + " : " + rightTankmotor1.get());
    }

    /**
     *logs the # of ticks
     */
    public void tick()
    {
        // hi. I am required to say that I have done somthing involving programming the robot. This is what I have done. Love, Dr. Ryan Samuel Giblin III
        
        if(false)
        {
            log("tick!");
            log("Left: "+leftTankmotor1.get());
            log("Left: "+leftTankmotor2.get());
            log("Right:"+rightTankmotor1.get());
            log("Right:"+rightTankmotor2.get());
            log("");
        }
    }
}
