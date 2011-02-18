package edu.arhs.first1100.minibot;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.util.AdvJaguar;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

public class MinibotSystem extends SystemBase
{
    private final int TOWER_SWITCH_CHANNEL = 14;
    private final int MINIBOT_SOLENOID_CHANNEL = 8;
    private final int ADVJAGUAR_CHANEL = 10;

    private DigitalInput towerDetector;
    private Solenoid minibotsolenoid;
    private AdvJaguar minibotjaguar;
    
    public MinibotSystem(RobotMain robot, int sleepTime)
    {

        super(robot, sleepTime);
        towerDetector = new DigitalInput(TOWER_SWITCH_CHANNEL);
        minibotsolenoid = new Solenoid(MINIBOT_SOLENOID_CHANNEL);
        minibotjaguar = new AdvJaguar(ADVJAGUAR_CHANEL);
    }

    public void tick()
    { }

    public void deploy()
    {

    }

    public void dropArm()
    {

    }
 
}
