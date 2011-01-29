package edu.arhs.first1100.util;

public class Averager
{
    int size = 10;
    //average
    double avg = 0;

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
     * Add a value to the array.  Oldest value is deleted.
     * @param value
     */
    public void feed(double value)
    {
        //EPIC formula courtesy of Akshay
        avg = (avg*(size-1)+value)/size;
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
