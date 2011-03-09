package edu.arhs.first1100.camera;

import edu.arhs.first1100.log.Log;
import edu.arhs.first1100.util.SystemBase;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * the start of the camera system
 * @author team1100
 */

public class CameraSystem extends SystemBase
{
    public final int WHITE_THRESHOLD = 1;
    public final int RED_THRESHOLD = 2;
    public final int BLUE_THRESHOLD =3;
    private final int PARTICLE_SIZE = 3;
    public Light light;

    //RGB Threshold
    private int minRed = 0;
    private int maxRed = 0;
    private int minGreen = 0;
    private int maxGreen = 0;
    private int minBlue = 0;
    private int maxBlue = 0;

    //Image related
    AxisCamera ac;
    ColorImage cImg;
    BinaryImage bImg;
    ParticleAnalysisReport[] pRep = new ParticleAnalysisReport[PARTICLE_SIZE];
    private static CameraSystem instance = null;
    
    public CameraSystem() 
    {
        ac = AxisCamera.getInstance();
        cImg = null;
        bImg = null;
        sleepTime = 200;
        light = new Light(3);

        //Camera Settings
        ac.writeCompression(0);
        ac.writeBrightness(10);
        ac.writeExposureControl(AxisCamera.ExposureT.hold);
        ac.writeRotation(AxisCamera.RotationT.k0);
        ac.writeResolution(AxisCamera.ResolutionT.k160x120);

        setThreshold(BLUE_THRESHOLD);
    }

    public static CameraSystem getInstance()
    {
        if(instance == null) instance = new CameraSystem();
        return instance;
    }

    public void tick()
    { 
        processImg();
    }

    /**
     * Sets the brightness of the camera
     * @param b Brightness
     */
    public void setBrightness(int b)
    {
        ac.writeBrightness(b);
    }

    /**
    * Gets an image from the camera to find particles within the camera's
    * RGB threshold.
    */
    private void processImg()
    {
        if(ac.freshImage())
        {
            try
            {
                cImg = ac.getImage();
                bImg = cImg.thresholdRGB(minRed, maxRed,minGreen, maxGreen, minBlue, maxBlue);
                cImg.free();
                pRep = bImg.getOrderedParticleAnalysisReports(PARTICLE_SIZE);
                bImg.free();
            }
            catch(Exception e)
            {
                Log.defcon3(this, e.getMessage());
            }
            printPRep();
        }
    }

    /**
    * Prints out the the boundaries and center of significant particles
    * found by the camera.
    */
    private void printPRep()
    {
        String info = "";
        if (pRep != null && pRep.length != 0)
        {
            for (int i = 0;i< 1;i++)
            {
                info += "PARTICLE "+i+'\n';
                info += "Top:    "+pRep[i].boundingRectTop+'\n';
                info += "Left:   "+pRep[i].boundingRectLeft+'\n';
                info += "Width:  "+pRep[i].boundingRectWidth+'\n';
                info += "Height: "+pRep[i].boundingRectHeight+'\n';
                info += "Center: "
                        +pRep[i].center_mass_x_normalized
                        +", "
                        +pRep[i].center_mass_y_normalized+"\n\n";
                
            }
            Log.defcon2(this, info);
        }
    }
    
    /**
    * Set the threshold of colors the camera should look for.
    * All parameters must be 0-255.
    * @param r Minimum RedValue
    * @param R Maximum Red Value
    * @param g Minimum Green Value
    * @param G Maximum Green Value
    * @param b Minimum Blue Value
    * @param B Maximum Blue Value
    */
    public synchronized void setThresholdRGB(int r, int R, int g, int G, int b, int B)
    {
        minRed   = (r >= 0 && r <= 255 && r <= R)? r : 0;
        maxRed   = (R >= 0 && R <= 255 && R >= r)? R : 255;
        minGreen = (g >= 0 && g <= 255 && g <= G)? g : 0;
        maxGreen = (G >= 0 && G <= 255 && G >= g)? G : 255;
        minBlue  = (b >= 0 && b <= 255 && b <= B)? b : 0;
        maxBlue  = (B >= 0 && B <= 255 && B >= b)? B : 255;
    }

    /**
    * Human way of setting the camera threshold.
    * @param t Threshold
    */
    public synchronized void setThreshold(int t){
        switch (t)
        {
            case WHITE_THRESHOLD:
                setThresholdRGB(190, 255, 210, 255, 210, 255);
                break;
            case BLUE_THRESHOLD:
                setThresholdRGB(0, 125, 160, 255, 210, 255);
                break;
        }
    }

    /**
    * Gets the center Y of the largest particle
    * @return
    */
    public double getCenterY()
    {
        light.onForAWhile();
        if(pRep.length > 0 && pRep[0] != null )
            return pRep[0].center_mass_y_normalized;
        else
            return 0.0;
    }

    /**
    * Gets the center X of the largest particle
    * @return double center mass x normalized
    */
    public double getCenterX()
    {
        light.onForAWhile();
        if(pRep.length > 0 && pRep[0] != null)
            return pRep[0].center_mass_x_normalized;
        else
            return 0.0;
    }

    /**
    * Returns the biggest particle found by the camera.
    * @return ParticleAnalysisReport
    */
    public synchronized ParticleAnalysisReport getBiggestParticle()
    {
        light.onForAWhile();
        if (pRep != null && pRep.length != 0)
        {
            return pRep[0];
        }
        else
        {
            return null;
        }
    }

     /**
     * Returns the significant particles found in array of ParticleAnalysisReports.
     * @return ParticleAnalysisReport[]
     */
    public synchronized ParticleAnalysisReport[] getParticles()
    {
        light.onForAWhile();
        return pRep;
    }
    /**
     * Return a specified number of particles in order of size.
     * @param n Number of particles
     * @return ParticleAnalysisReport[n]
     */
    public synchronized ParticleAnalysisReport[] getParticles(int n)
    {
        light.onForAWhile();
        ParticleAnalysisReport[] p = new ParticleAnalysisReport[n];
        System.arraycopy(pRep, 0, p, 0, n);
        return p;
    }

    /**
     * Sort an array of particles by their Y values.
     * @param p ParticleAnalysisReport[]
     * @return sorted ParticleAnalysisReport[]
     */
    public synchronized ParticleAnalysisReport[] SortY(ParticleAnalysisReport[] p)
    {
        int index = 0;
        ParticleAnalysisReport[] sortedP = new ParticleAnalysisReport[p.length];
        for (int i = 0; i< p.length-1; i++)
        {
            index = i;
            for (int n = i+1; n< p.length;n++)
            {
                if (p[i].center_mass_y < p[n].center_mass_y)
                    index++;
            }
            sortedP[index] = p[i];
        }
        return sortedP;
    }
}
