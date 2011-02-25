package edu.arhs.first1100.camera;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.Timer;

/**
 *the start of the camera system
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
    {

    }
}
