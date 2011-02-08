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
    int size = 1;
    double avg = 0; //average
    int magnitude = 1;

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
        size = sampleSize;
        avg = starting;
    }

    /**
     * recalculates average with newest input
     * @param value the newest value
     */
    public void feed(double value)
    {
        if(size==1)
            avg = value;
        else
            //EPIC formula courtesy of Akshay
            avg = (avg*(size-magnitude)+value*magnitude)/size;
    }

    /**
     * Sets magnitude of averager. Higher magnitude will cause average to move faster.
     * @param m magnitude
     */
    public void setMagnitude(int m)
    {
        magnitude = Math.abs(m);
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
