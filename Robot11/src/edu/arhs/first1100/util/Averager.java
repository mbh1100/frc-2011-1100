package edu.arhs.first1100.util;

public class Averager
{
    int size = 1;
    double[] values;
    int pointer = 0;

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
        values = new double[size];
        for(int i=0; i<size; ++i)
        {
            values[i] = starting;
        }
    }

    /**
     * Add a value to the array.  Oldest value is deleted.
     * @param value
     */
    public void feed(double value)
    {
        values[pointer] = value;
        pointer++;
        pointer %= size; // wrap around array
    }

    /**
     * Return the average of the values in the array
     * @return the average
     */
    public float get()
    {
        int total = 0;

        for(int i=0; i<size; ++i)
            total += values[i];
        total /= size;
        
        return total;
    }
}
