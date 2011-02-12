package edu.arhs.first1100.camera;

import edu.arhs.first1100.util.SystemBase;
import edu.arhs.first1100.robot.RobotMain;
import edu.arhs.first1100.camera.Light;
import edu.wpi.first.wpilibj.camera.*;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;


public class CameraSystem extends SystemBase
{
    public final int WHITE_THRESHOLD = 1;
    private final int PARTICLE_SIZE = 3;
    private Light light;
    
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
    
    public CameraSystem(RobotMain robot, int sleepTime)
    {
        super(robot, sleepTime);
        
        ac = AxisCamera.getInstance();
        cImg = null;
        bImg = null;
        sleepTime = 200;

        light = new Light(5);

        //Camera Settings
        ac.writeCompression(0);
        ac.writeBrightness(10);
        ac.writeExposureControl(AxisCamera.ExposureT.hold);
        ac.writeRotation(AxisCamera.RotationT.k0);
        ac.writeResolution(AxisCamera.ResolutionT.k160x120);
    }

     /**
     * Adjusts camera settings. Then processes camera images until
     * the thread is terminated.
     */
    public void tick()
    {
        processImg();
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
                log(e.getMessage());
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
        if (pRep != null && pRep.length != 0)
        {
            for (int i = 0;i< 1;i++)
            {
                System.out.println("PARTICLE "+i);
                System.out.println("Top:    "+pRep[i].boundingRectTop);
                System.out.println("Left:   "+pRep[i].boundingRectLeft);
                System.out.println("Width:  "+pRep[i].boundingRectWidth);
                System.out.println("Height: "+pRep[i].boundingRectHeight);
                System.out.println("Center: "
                        +pRep[i].center_mass_x_normalized
                        +", "
                        +pRep[i].center_mass_y_normalized);
                System.out.println();
            }
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
        maxRed   = (R >= 0 && R <= 255 && r >= r)? R : 255;
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
        }
    }

    public double getCenterY()
    {
        if(!(pRep.length >= 0))
            return pRep[0].center_mass_y_normalized;
        else
            return 0.0;
    }

    public double getCenterX()
    {
        if(!(pRep.length >= 0))
            return pRep[0].center_mass_x_normalized;
        else
            return 0.0;
    }

     /**
     * Returns the significant particles found in array of ParticleAnalysisReports.
     * @return ParticleAnalysisReport[]
     */
    public synchronized ParticleAnalysisReport[] getParticles()
    {
        return pRep;
    }
    /**
     * Return a specified number of particles in order of size.
     * @param n Number of particles
     * @return ParticleAnalysisReport[n]
     */
    public synchronized ParticleAnalysisReport[] getParticles(int n)
    {
        ParticleAnalysisReport[] p = new ParticleAnalysisReport[n];
        System.arraycopy(pRep, 0, p, 0, n);
        return p;
    }


     /**
     * Returns the biggest particle found by the camera.
     * @return ParticleAnalysisReport
     */
    public synchronized ParticleAnalysisReport getBiggestParticle()
    {
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
