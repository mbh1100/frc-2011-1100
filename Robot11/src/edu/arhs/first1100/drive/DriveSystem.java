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
    private final int STATE_TANK = 0;
    private final int STATE_RAISING = 1;
    private final int STATE_LOWERING = 2;
    private final int STATE_SIDESTEP = 3;

    private int state = STATE_TANK;
    
    //private JaguarPair leftTankMotor;
    //private JaguarPair rightTankMotor;
    private AdvJaguar rightTankmotor1;
    private AdvJaguar rightTankmotor2;
    private AdvJaguar leftTankmotor1;//left tank 1
    private AdvJaguar leftTankmotor2;//left tank 2
    private AdvJaguar liftMotor; //motor that raises lowers the side step wheels
    private AdvJaguar sidestepDriveMotor; // drives the side step wheel
    private RobotDrive rd;
    private ArcadeDriveMux adm;
    public CameraDriveCurve cdc;
    public CameraDrivePower cdp;
/**
 *states what motors go with what jags
 * @param robot
 * @param sleepTime
 */
    public DriveSystem(RobotMain robot, int sleepTime)
    {
         super(robot, sleepTime);
         
         // JaguarPair(ch1, ch2, invert, averager sample size);
         leftTankmotor1  = new AdvJaguar(2, false);
         rightTankmotor1 = new AdvJaguar(1, true);
         leftTankmotor2 =  new AdvJaguar(4, false);
         rightTankmotor2 = new AdvJaguar(3, true);
         
         rd = new RobotDrive(leftTankmotor1, leftTankmotor2 , rightTankmotor1, rightTankmotor2);
         //sidestepDriveMotor = new AdvJaguar(5);
         
         //this.setDriveSpeed(0.0, 0.0);
         
         adm = new ArcadeDriveMux(this);
         cdc = new CameraDriveCurve(adm, robot.cameraSystem);
         cdp = new CameraDrivePower(adm, robot.cameraSystem);
    }
    /**
     *says how fast to go
     * declares camera drive also
     * @param speed
     */
    public void testCameraDrive(double speed)
    {
        //log("testCameraDrive");

        // log("cdc.trackCamera()");
        cdc.trackCamera();
        
        adm.setPower(speed);

        // log("cdp.trackCamera()");
        cdp.trackCamera();
    }
    public void runCameraDrive()
    {
        adm.cameraDrive();
    }
    /**
     *declares robot drive
     * @param leftSide
     * @param rightSide
     */
    public void setDriveSpeed(double leftSide, double rightSide)
    {
        cdp.stopTrackCamera();
        if(state == STATE_TANK)
        {
            rd.tankDrive(leftSide, rightSide);
        }
        else
        {
            rd.stopMotor();
        }
    }
    /**
     *
     * @param power
     * @param curve
     */
    public void drive(double power, double curve)
    {
        if(state == STATE_TANK)
        {
            rd.drive(power, curve);
        }
        else
        {
            rd.stopMotor();
        }
       // log("curve:" + curve);
    }
    /**
     *
     * @param speed
     */
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
    /**
     *how to drive in tank mode
     */
    public void setDriveModeTank()
    {
        if(state == STATE_SIDESTEP)
            state = STATE_RAISING;
    }
/**
 * how to drive with the side step motors
 */
    public void setDriveModeSideStep()
    {
        if(state == STATE_TANK)
            state = STATE_LOWERING;
    }
}
