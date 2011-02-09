/**
 * Averager.java
 *
 * An Averager gives the average value of an array of doubles that you
 * can feed one at a time.  With each feed, the oldest value is dropped.
 * 
 */

package edu.arhs.first1100.util;

import java.util.Vector; // added by alex 2-8

public class Averager
{
    int size = 1;
    double avg = 0; //average
    int magnitude = 1;
    int index = 0;
    
    Vector container = new Vector(); //created by alex 2-8


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
        container.setSize(sampleSize);
    }

    /**
     * Add a value to the array.  Oldest value is deleted.
     * @param value
     */
    public void feed(double value)
    {
        Double newVal = new Double(value);
        
        int newSize = index%size;
        //System.out.println("Averager newSize: "+newSize);
        container.setElementAt(newVal, newSize);
        double addCount = 0;

        index++;

        //System.out.println("Averager: "+container);
        
        for(int i = 0; i < container.size(); i++)
        {
            if(container.elementAt(i) != null)
            {
                double extractVal = ((Double)container.elementAt(i)).doubleValue();
                addCount += extractVal;
            }
        }
        avg = addCount/container.size();

        
        
        //Akshay's code
        /*if(size==1)
            avg = value;
        else
            //EPIC formula courtesy of Akshay
            avg = (avg*(size-magnitude)+value*magnitude)/size;
        */
    }
        
    /*
    public void clearValueCount()
    {
        valueCount = 0
    }
        created by alex 2-8
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
