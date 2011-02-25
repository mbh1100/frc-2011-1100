/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.line;

import edu.arhs.first1100.util.SystemBase;

/**
 *
 * @author team1100
 */
public class LineSystem extends SystemBase
{
    private static LineSystem instance = null;

    public LineSystem() { }

    public static LineSystem getInstance()
    {
        if(instance == null) instance = new LineSystem();
        return instance;
    }

    public void tick()
    {

    }
}
