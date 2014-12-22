import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Fractal extends JFrame
{
    Window window;

    private static final int PIXEL_SIZE = 1;

    private double minX, maxX;
    private double minY, maxY;

    private double xRange() { return Math.abs(minX - maxX); }
    private double yRange() { return Math.abs(minY - maxY); }



    public Fractal()
    {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);


        this.window = new Window();
        window.standardZoom();

        getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                window.zoomToPoint(mouseEvent.getPoint());
            }
        });


        System.out.println("X value " + complexForPoint(1440,0).real);
    }

    public void paint(Graphics g)
    {
        for (int x = 0; x < getWidth(); x += PIXEL_SIZE)
        {
            for (int y = 0; y < getHeight(); y += PIXEL_SIZE)
            {
                Complex c = window.complexForPoint(x, y);


                int iters = c.escapeIters();

                g.setColor(colorForEscapeIters(iters));

                g.fillRect(x,y,PIXEL_SIZE,PIXEL_SIZE);
            }
        }

    }

    private Color colorForEscapeIters(int escapeIters)
    {
        if (escapeIters == -1) return Color.black;
//        else {
//            return Color.white;
//        }
//        int buckets = color_lookup.length;
//        double size = 255.0 / buckets;
//        int bucket = (int)(escapeIters / size);
//
//        return new Color(color_lookup[bucket]);

        return new Color(escapeIters, escapeIters, escapeIters).brighter().brighter().brighter();
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
        minX = -2;
        maxX = 1;
        minY = -1;
        maxY = 1;
    }

    private void zoom() {
        Rectangle r = new Rectangle(0,0,10,10);

    }






    private class Window {

        private final double scaleFactor = 10.0;

        double minX, maxX, minY, maxY;

        public double xRange() { return Math.abs(minX - maxX); }
        public double yRange() { return Math.abs(minY - maxY); }

        public void standardZoom() {
            minX = -2;
            maxX = 1;
            minY = -1;
            maxY = 1;
        }

        public Complex complexForPoint(int x, int y) {
            double xPercent = (double)x / getWidth();
            double yPercent = (double)y / getHeight();
            double xLocation = minX + (xRange() * xPercent);
            double yLocation = minY + (yRange() * yPercent);

            return new Complex(xLocation, yLocation);
        }

        public void zoomToPoint(java.awt.Point p)
        {
            System.out.println("Zooming to Point");
            int x = p.x;
            int y = p.y;

            Complex c = complexForPoint(x,y);
            System.out.println(c);

            minX /= scaleFactor;
            maxX /= scaleFactor;
            minY /= scaleFactor;
            maxY /= scaleFactor;

            double width = xRange() / 2;
            double height = yRange() / 2;



            minX = c.real - width;
            maxX = c.real + width;

            minY = c.imaginary - height;
            maxY = c.imaginary + height;

            print();
            repaint();


        }

        public void print() {
            System.out.println("X AXIS : " + minX + " - " + maxX);
            System.out.println("Y AXIS : " + minY + " - " + maxY);
        }



    }
}
