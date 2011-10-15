package edu.arhs.first1100.oopctl;
import edu.arhs.first1100.log.Log;

public class JoystickAxisHandler
{
    private String name;
    private double axis;
    
    
    public void newValue(double value)
    {   
        Log.defcon3(this, name + "Value is now " +value);
    }    

     
    public void setName(String name)
    {
        this.name = name;
    }
    
     public String getName()
    {
        return name;
    }
}