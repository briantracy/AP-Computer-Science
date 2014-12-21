import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Fractal extends JFrame
{
    private static final int PIXEL_SIZE = 1;

    private double minX, maxX;
    private double minY, maxY;

    private double xRange() { return Math.abs(minX - maxX); }
    private double yRange() { return Math.abs(minY - maxY); }

    private int[] color_lookup = {
            0xC2FFC2,
            0x7ACC7A,
            0xFF99CC,
            0xFF66FF,
            0x662966,
            0x99CCFF,
            0x3366FF,
            0x142966,
            0xFF9966,
            0xFF6600


    };

    Random r = new Random();

    int maxIters = -2;
    Complex max = null;


    public Fractal()
    {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
        stdZoom();

        System.out.println("Screen size is " + getSize().toString().substring(19));
        System.out.println("X range is " + xRange());
        System.out.println("Y range is " + yRange());





        System.out.println("X value " + complexForPoint(1440,0).real);
    }

    public void paint(Graphics g)
    {
        for (int x = 0; x < getWidth(); x += PIXEL_SIZE)
        {
            for (int y = 0; y < getHeight(); y += PIXEL_SIZE)
            {
                Complex c = complexForPoint(x,y);


                int iters = c.escapeIters();

                if (iters > maxIters) { maxIters = iters; max = c; }
                g.setColor(colorForEscapeIters(iters));

                g.fillRect(x,y,PIXEL_SIZE,PIXEL_SIZE);
            }
        }

        System.out.println(maxIters);
        System.out.println(max);

    }

    private Color colorForEscapeIters(int escapeIters)
    {
        if (escapeIters == -1) return Color.black;
//        int buckets = color_lookup.length;
//        double size = 255.0 / buckets;
//        int bucket = (int)(escapeIters / size);
//
//        return new Color(color_lookup[bucket]);

        return new Color(escapeIters).brighter().brighter().brighter();
    }

    private Complex complexForPoint(int x, int y)
    {
        // determine where in the visible range this point is.

        double xPercent = (double)x / getWidth();
        double yPercent = (double)y / getHeight();
        double xLocation = minX + (xRange() * xPercent);
        double yLocation = minY + (yRange() * yPercent);

        return new Complex(xLocation, yLocation);
    }

    private void stdZoom() {
        minX = -1.3;
        maxX = -1.1;
        minY = -.25;
        maxY = .25;
    }

    private void zoom() {
        Rectangle r = new Rectangle(0,0,10,10);

    }

    private Color randomColor()
    {
        return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()).darker().darker();
    }
}
