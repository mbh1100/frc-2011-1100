/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.minibot;

import edu.arhs.first1100.util.SystemBase;

/**
 *
 * @author team1100
 */
public class MinibotSystem extends SystemBase
{
    private static MinibotSystem instance = null;

    public MinibotSystem() { }

    public static MinibotSystem getInstance()
    {
        if(instance == null) instance = new MinibotSystem();
        return instance;
    }

    public void tick()
    {

    }
}
