/**
 * Averager.java
 *
 * An Averager gives the average value of an array of doubles that you
 * can feed one at a time.  With each feed, the oldest value is dropped.
 * 
 */

package edu.arhs.first1100.util;

public class Averager
{
    double avg = 0; //average
    int magnitude = 1;
    double data[];
    int index;
    
    
    /**
     * Make a new Averager object.
     * An Averager gives the average value of an array of doubles that you
     * can feed one at a time.  With each feed, the oldest value is dropped.
     *
     * @param sampleSize The size of the array
     */
    public Averager(int sampleSize)
    {
        this(sampleSize, 0.0);
    }
    
     /**
     * Make a new Averager object.
     * An Averager gives the average value of an array of doubles that you
     * can feed one at a time.  With each feed, the oldest value is dropped.
     *
     * @param sampleSize The size of the array
     * @param starting The array will be filled with this value instead of 0.0
     */
    public Averager(int sampleSize, double starting)
    {
        avg = starting;
        data = new double[sampleSize];
        for (int i = 0; i<sampleSize;i++){
            data[i] = starting;
        }
    }

    /**
     * Add a value to the array.  Oldest value is dropped.
     * @param value
     */
    public void feed(double value)
    {
        index++;
        index = index%data.length;
        avg = 0;
        data[index] = value * magnitude;
        for (int i = 0; i < data.length; i++)
        {
            avg += data[i];
        }
        avg = avg / (data.length + (magnitude - 1));
    }
    
        

    /**
     * Sets the speed magnitude of averager. This changes how much new value affects the average. 
     * @param m magnitide
     */
    public void setMagnitude(int m)
    {
        magnitude = m;
    }
    
    /**
     * Return the average of the values in the array
     * @return the average
     */
    public double get()
    {
        return avg;
    }
}

