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
    /**
     * the pattern to score in autonomous
     * @param startingPosition
     * @param side
     * @param column
     * @param row
     */
    public AutonomousGoal(int startingPosition, boolean side, int column, int row)
    {
        this.startingPosition = startingPosition;
        this.side = side;
        this.column = column;
        this.row = row;
    }
    /**
     * returns the starting position
     * @return
     */
    public int getStartingPosition()
    {
        return startingPosition;
    }
    /**
     * returns what column we are on
     * @return
     */
    public int getColumn()
    {
        return column;
    }
/**
 * returns what row we are on
 * @return
 */
    public int getRow()
    {
        return row;
    }
/**
 * returns what side we are on.
 * @return
 */
    public boolean getSide()
    {
        return side;
    }
}
