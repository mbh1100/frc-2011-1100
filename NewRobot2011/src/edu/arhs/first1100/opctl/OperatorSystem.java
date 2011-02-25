/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.opctl;

import edu.arhs.first1100.util.SystemBase;

/**
 *
 * @author team1100
 */
public class OperatorSystem extends SystemBase
{
    private static OperatorSystem instance = null;

    public OperatorSystem() { }

    public static OperatorSystem getInstance()
    {
        if(instance == null) instance = new OperatorSystem();
        return instance;
    }

    public void tick()
    {
        
    }
}
