/**
 * AutonomousGoal.java
 *
 * Holds a starting state and goal peg of the robot.
 * This object will be givin to each autonomous routine.
 * 
 */
package edu.arhs.first1100.autoctl;

public class AutonomousGoal
{
    // left line: -1, mid line: 0, right line: 1
    public int startingPosition = 0;

    // left rack: false, right rack: true
    public boolean side = false;

    // When looking at the rack from the front:
    // 0 1 2
    public int column = 0;

    // When looking at the rack from the front:
    // 2
    // 1
    // 0
    public int row = 0;
    
    public AutonomousGoal(int startingPosition, boolean side, int column, int row)
    {
        this.startingPosition = startingPosition;
        this.side = side;
        this.column = column;
        this.row = row;
    }
    
    public int getStartingPosition()
    {
        return startingPosition;
    }
    
    public int getColumn()
    {
        return column;
    }

    public int getRow()
    {
        return row;
    }

    public boolean getSide()
    {
        return side;
    }
}
