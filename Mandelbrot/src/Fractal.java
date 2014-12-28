import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Fractal extends JFrame
{
    Window window;

    private final double scaleFactor = 10;


    private static final int PIXEL_SIZE = 1;



    public Fractal()
    {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);

        this.window = new Window();
        window.standardZoom();



        getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                java.awt.Point click = mouseEvent.getPoint();
                click.y += 22;

                if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    window.zoomToPoint(click, 1.0 / scaleFactor);
                } else if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    window.zoomToPoint(click, scaleFactor);
                }
            }
        });



        System.out.println("X value " + window.complexForPoint(1440,0).real);
    }

    public void paint(Graphics g)
    {
        for (int x = 0; x < getWidth(); x += PIXEL_SIZE)
        {
            for (int y = 0; y < getHeight(); y += PIXEL_SIZE)
            {
                int iters = window.complexForPoint(x, y).escapeIters();

                g.setColor(colorForEscapeIters(iters));

                g.fillRect(x,y,PIXEL_SIZE,PIXEL_SIZE);


            }
        }

    }




    private Color colorForEscapeIters(int escapeIters)
    {
        if (escapeIters == -1) return Color.black;

        return new Color((escapeIters & 255) << 5);

//        int buckets = color_lookup.length;
//        double size = 255.0 / buckets;
//        int bucket = (int)(escapeIters / size);
//
//        return color_lookup[bucket];

    }






    private class Window {


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

        public void zoomToPoint(java.awt.Point p, double scaleFactor)
        {
            System.out.println("Zooming to Point " + p);

            int x = p.x;
            int y = p.y;

            Complex c = this.complexForPoint(x,y);
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


    private Color[] color_lookup = new Color[] {
            Color.red,
            Color.green,
            Color.orange,
            Color.yellow,
            Color.white
    };
}
