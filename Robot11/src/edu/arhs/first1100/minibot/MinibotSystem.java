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
    private Solenoid mSolenoid;
    private AdvJaguar mJaguar;
    double mJagSpeed = 0.4;
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
        mSolenoid = new Solenoid(MINIBOT_SOLENOID_CHANNEL);
        mJaguar = new AdvJaguar(ADVJAGUAR_CHANEL);
    }
/**
 *
 */
    public void deploy()
    {
        if (towerDetector.get())
        {
            mSolenoid.set(true);
        }
    }
/**
 *
 */
    public void down()
    {
        if(!towerDetector.get())
        {
            mJaguar.set(mJagSpeed);
        }
    }
}
