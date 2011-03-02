/**
 * Moves the Lift until it reaches a certain particle (top middle
 * and open the template in the editor.
 */

package edu.arhs.first1100.autoctl;

/**
 *the routine to put pegs on the scoring board and to analysize the amount of particles that the axis camera sees.
 * @author Connor Moroney
 */
public class LiftToPegRoutine extends Routine
{
    public final static int HIGH = 1;
    public final static int MIDDLE = 2;
    public final static int LOW = 3;

    private final static int TopPegHeight = 1680;
    private final static int MiddlePegHeight = 620;
    private final static int BottomPegHeight = 5;
    
    private int positionState;

    Routine ltpr;

/**
 *
 * This is a bit silly, the state case could easily be in LiftToPositionRoutine
 * 
 */
    public LiftToPegRoutine(int state)
    {
        super(200);
        switch (state)
        {
            case HIGH:
                positionState = TopPegHeight;
                break;
            case MIDDLE:
                positionState = MiddlePegHeight;
                break;
            case LOW:
                positionState = BottomPegHeight;
                break;
        }

        ltpr = new LiftToPositionRoutine(positionState);
    }

    public void run()
    {
        ltpr.execute();
    }

    protected void doCancel()
    {
        ltpr.cancel();
    }
}
