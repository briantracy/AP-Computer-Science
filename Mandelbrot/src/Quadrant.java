import java.awt.*;
import java.util.Arrays;

public class Quadrant implements Runnable
{
    Point topLeft, bottomRight;
    Fractal.Window window;

    public int[][] data;

    public Quadrant(Point topLeft, Point bottomRight, Fractal.Window window)
    {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.data = new int[bottomRight.x - topLeft.x + 1][bottomRight.y - topLeft.y + 1];
        this.window = window;
        System.out.println("Array Size " + this.data.length + this.data[0].length);
    }

    @Override
    public void run() {

        for (int x = topLeft.x; x < bottomRight.x; x++)
        {
            for (int y = topLeft.y; y < bottomRight.y; y++)
            {
                this.data[x - topLeft.x][y - topLeft.y] = this.window.complexForPoint(x, y).escapeIters();
            }
        }
        //System.out.println(Arrays.deepToString(data ));
    }


}
