package edu.arhs.first1100.oopctl;
import edu.arhs.first1100.log.Log;

public class JoystickAxisHandler
{
    private String name;
    
    
    public void newXvalue(double value)
    {   
        Log.defcon3(this, name + " x Value is now " +value);
    }    
   
    public void newYvalue(double value)
    {
        Log.defcon3(this, name + "Y value is now "+value);
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