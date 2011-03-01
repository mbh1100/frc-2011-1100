package edu.arhs.first1100.minibot;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

/**
 *deploys the minibot
 * @author team1100
 */
public class MinibotSystem extends SystemBase
{
    static private MinibotSystem instance;
    static private int sleepTime = 100;
    /**
     *what the bot is going to do
     */
    
    private final int MINIBOT_IO_SLOT = 6;
    
    private Victor beltVic;
    private Victor armVic;

    private DigitalInput towerDetector;

    private DigitalInput armTopDetector;
    private DigitalInput armBottomDetector;
    
    private DigitalInput beltInDetector;
    private DigitalInput beltOutDetector;
    
    public static MinibotSystem getInstance()
    {
        if(instance == null) instance = new MinibotSystem();
        return instance;
    }
    /**
     *sets how long the robot should sleep
     * sets when the robot should deploy the minibot
     * @param robot
     * @param sleepTime
     */
    public MinibotSystem()
    {
        super(sleepTime);
        
        armTopDetector = new DigitalInput(MINIBOT_IO_SLOT, 1);
        armBottomDetector = new DigitalInput(MINIBOT_IO_SLOT, 2);

        beltInDetector = new DigitalInput(MINIBOT_IO_SLOT, 3);
        beltOutDetector = new DigitalInput(MINIBOT_IO_SLOT, 4);

        towerDetector = new DigitalInput(MINIBOT_IO_SLOT, 5);
        
        beltVic = new Victor(MINIBOT_IO_SLOT, 2);
        armVic = new Victor(MINIBOT_IO_SLOT, 1);
    }
    
    /**
     * Sets the speed of the
     * @param speed
     */
    public void setArmSpeed(double speed)
    {
        /*
        if(speed > 0)
        {
            if(!armBottomDetector.get())
                armVic.set(speed);
            else
                armVic.set(0.0);
        }
        else if(speed < 0)
        {
            if(!armTopDetector.get())
                armVic.set(speed);
            else
                armVic.set(0.0);
        }*/

        armVic.set(speed);

    }
    
    public void setDeployerSpeed(double speed)
    {
        /*
        if(speed > 0)
        {
            if(!beltOutDetector.get())
                beltVic.set(speed);
            else
                beltVic.set(0.0);
        }
        else if(speed < 0)
        {
            if(!beltInDetector.get())
                beltVic.set(speed);
            else
                beltVic.set(0.0);
        }
        */

        beltVic.set(speed);
    }

    public double getArmSpeed()
    {
        return armVic.get();
    }
}
