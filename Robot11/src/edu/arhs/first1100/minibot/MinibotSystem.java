package edu.arhs.first1100.minibot;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *deploys the minibot
 * @author team1100
 */
public class MinibotSystem extends SystemBase
{
    /**
     *what the bot is going to do
     */
    private final int TOWER_SWITCH_CHANNEL = 14;
    private final int MINIBOT_SOLENOID_CHANNEL = 8;
    private final int ADVJAGUAR_CHANEL = 10;

    private DigitalInput towerDetector;
    private AdvJaguar mBeltJaguar;
    private AdvJaguar mArmJaguar;
    
    // Checks if the minibot deployer is down
    private boolean deployReady = false;
    /**
     *sets how long the robot should sleep
     * sets when the robot should deploy the minibot
     * @param robot
     * @param sleepTime
     */
    public MinibotSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        towerDetector = new DigitalInput(TOWER_SWITCH_CHANNEL);
        mBeltJaguar = new AdvJaguar(9);
        mBeltJaguar.set(0.0);
        mArmJaguar = new AdvJaguar(ADVJAGUAR_CHANEL);
    }
    /**
     * how fast the motor should be going
     * @param speed
     */
    public void setArmSpeed(double speed)
    {
        mArmJaguar.set(speed);
    }

    public void setDeployerSpeed(double speed)
    {
        mBeltJaguar.set(speed);
    }
/**
 *sets how the minibot is deployed
 */
    public void deploy()
    {
        //if (towerDetector.get())
       // {
            mBeltJaguar.set(0.5);
       // }
    }
/**
 *when to put down the deployer
 */
    public void down()
    {
        //if(!towerDetector.get())
        //{
            mArmJaguar.set(0.5);
        //}
    }
}
