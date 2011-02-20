package edu.arhs.first1100.minibot;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author team1100
 */
public class MinibotSystem extends SystemBase
{
    /**
     *
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
     *
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
    public void setArmSpeed(double speed)
    {
        mArmJaguar.set(speed);
    }

    public void setDeployerSpeed(double speed)
    {
        mBeltJaguar.set(speed);
    }
/**
 *
 */
    public void deploy()
    {
        //if (towerDetector.get())
       // {
            mBeltJaguar.set(0.5);
       // }
    }
/**
 *
 */
    public void down()
    {
        //if(!towerDetector.get())
        //{
            mArmJaguar.set(0.5);
        //}
    }
}
