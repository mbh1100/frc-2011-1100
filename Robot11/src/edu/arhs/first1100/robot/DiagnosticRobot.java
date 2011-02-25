package edu.arhs.first1100.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author team1100
 */
public class DiagnosticRobot
{
    Joystick left, right, xbox;
    Jaguar r1, r2, l1, l2, arm, lift;

    public DiagnosticRobot()
    {
        left = new Joystick(1);
        right = new Joystick(2);
        xbox = new Joystick(3);
        r1 = new Jaguar(1);
        r2 = new Jaguar(3);
        l1 = new Jaguar(2);
        l2 = new Jaguar(4);
    }
    
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
