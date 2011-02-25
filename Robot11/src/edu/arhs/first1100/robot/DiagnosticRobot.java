package edu.arhs.first1100.robot;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author team1100
 */
public class DiagnosticRobot
{
    Joystick left, right, xbox;

    
    public void teleop()
    {
        log("TELEOP CYCLE");
    }

    public void disable(){
        log("Disabled");
    }

    public void auto()
    {
        log("Autonomous");
    }

    public void log(String message)
    {
        System.out.println("DIAGNOSTIC:" + message);
    }


}
