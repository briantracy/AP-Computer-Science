import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Generate unique random points so that when creating the game, not fences mhos or players overlap.
 */
public class RandomPointGenerator
{

    private ArrayList<Point> previousPoints = new ArrayList<Point>();
    private int lowX, lowY, highX, highY;


    public RandomPointGenerator(int lowX, int highX, int lowY, int highY)
    {
        this.lowX = lowX;
        this.highX = highX;
        this.lowY = lowY;
        this.highY = highY;
    }

    /**
     * Creates a distinct point by checking to see if a point has already been used.
     * @return
     */
    public Point nextPoint()
    {
        Point randP = new Point();
        Random randG = new Random();
        boolean found = false;

        while (!found)
        {
            randP.x = randG.nextInt((highX - lowX) + 1) + lowX;
            randP.y = randG.nextInt((highY - lowY) + 1) + lowY;

            if (previousPoints.contains(randP)) {
                found = false;

            }
            else {
                previousPoints.add(randP);
                found = true;
            }

        }

        return randP;
    }


    /**
     * Creates a non distinct point - used for jumping
     * @return
     */
    public Point randomPoint()
    {
        Point randP = new Point();
        Random randG = new Random();

        randP.x = randG.nextInt((highX - lowX) + 1) + lowX;
        randP.y = randG.nextInt((highY - lowY) + 1) + lowY;

        return randP;
    }
}
