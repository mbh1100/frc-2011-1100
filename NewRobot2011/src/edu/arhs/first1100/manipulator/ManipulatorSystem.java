/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.arhs.first1100.manipulator;

import edu.arhs.first1100.util.SystemBase;

/**
 *
 * @author team1100
 */
public class ManipulatorSystem extends SystemBase
{
    private static ManipulatorSystem instance = null;

    public ManipulatorSystem() { }

    public static ManipulatorSystem getInstance()
    {
        if(instance == null) instance = new ManipulatorSystem();
        return instance;
    }

    public void tick()
    {

    }
}
