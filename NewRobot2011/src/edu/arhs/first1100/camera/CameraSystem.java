package edu.arhs.first1100.camera;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.SystemBase;

/**
 * the start of the camera system
 * @author team1100
 */

public class CameraSystem extends SystemBase
{
    private static CameraSystem instance = null;
    
    public CameraSystem() { }

    public static CameraSystem getInstance()
    {
        if(instance == null) instance = new CameraSystem();
        return instance;
    }

    public void tick()
    { }

    public double getCenterY()
    {
        Log.defcon3(this,"getCenterY() needs code!");
        return 0.0;
    }
    
    public double getCenterX()
    {
        Log.defcon3(this,"getCenterX() needs code!");
        return 0.0;
    }
}
