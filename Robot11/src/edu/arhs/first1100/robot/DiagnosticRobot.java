package edu.arhs.first1100.robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
        arm = new Jaguar(8);
        lift = new Jaguar(6);
    }
    
    public void teleop()
    {
        log("TELEOP CYCLE");
        //Driving
        l1.set(left.getY());
        l2.set(left.getY());
        r1.set(right.getY());
        r2.set(right.getY());
        //Manipulator
        arm.set(xbox.getRawAxis(2));
        lift.set(xbox.getRawAxis(5));


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
