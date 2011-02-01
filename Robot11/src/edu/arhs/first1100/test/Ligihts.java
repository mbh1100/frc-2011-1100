package edu.arhs.first1100.test;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;

public class Ligihts
{
    Relay moe, larry, curly;

    public static class Color
    {
        public final int value;
        static final int Red_val = 0;
        static final int Blue_val = 1;
        static final int White_val = 2;
        public static final Color Red = new Color(Red_val);
        public static final Color Blue = new Color(Blue_val);
        public static final Color White = new Color(White_val);

        private Color(int value)
        {
            this.value = value;
        }
    };

    public Ligihts()
    {
        moe = new Relay(4, Relay.Direction.kForward);   //red light on channel 4
        larry = new Relay(5, Relay.Direction.kForward); //Blue light on channel 5
        curly = new Relay(6, Relay.Direction.kForward); //White light on channel 6
    }

    public void setLigits (Color x)
    {
        switch (x.value)
        {
            case Color.Red_val:
                moe.set(Relay.Value.kOn);
                larry.set(Relay.Value.kOff);
                curly.set(Relay.Value.kOff);
                System.out.println("Red on");
                break;

            case Color.Blue_val:
                moe.set(Relay.Value.kOff);
                larry.set(Relay.Value.kOn);
                curly.set(Relay.Value.kOff);
                System.out.println("Blue on");
                break;

            case Color.White_val:
                moe.set(Relay.Value.kOff);
                larry.set(Relay.Value.kOff);
                curly.set(Relay.Value.kOn);
                System.out.println("White on");
                break;
        }
    }
}
