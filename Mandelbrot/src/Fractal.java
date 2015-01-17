import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Fractal extends JFrame
{
    Window window;

    private final double scaleFactor = 5;

    private String axisRep = "x = [-2, 1]\ny = [-1, 1]";

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
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        for (int x = 0; x < getWidth(); x += PIXEL_SIZE)
        {
            for (int y = 0; y < getHeight(); y += PIXEL_SIZE)
            {

                Complex z = window.complexForPoint(x,y);
                int iters = z.escapeIters();

                g2.setColor(colorForEscapeIters(iters));

                g2.fillRect(x,y,PIXEL_SIZE,PIXEL_SIZE);
            }
        }

        drawAxisRep(g);
    }

    private void drawAxisRep(Graphics g)
    {
        if (window.complexForPoint(0, getHeight()).escapeIters() < 40) {
            g.setColor(Color.orange);
        }
        else {
            g.setColor(Color.orange);
        }

        int inset = 15;
        Point p = new Point(inset, getHeight() - inset);
        g.setFont(new Font("Garamond", Font.PLAIN, 25));
        g.drawString(axisRep, p.x, p.y);
    }

    private Color colorForComplexAndIters(Complex z, int n)
    {
        double smooth = n + 1 - Math.log(Math.log(z.distance())) / Math.log(2.0);

        return new Color(Color.HSBtoRGB(0.95f + (float)(10 * smooth), 0.6f, 1.0f));
    }


    private Color colorForEscapeIters(int escapeIters)
    {
        if (escapeIters == -1) return Color.black;

        return new Color(escapeIters / 2, escapeIters, escapeIters / 3).brighter().brighter().brighter();

        //return new Color((escapeIters & 255) << 5);

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

            resetAxisRep();

            repaint();


        }

        public void resetAxisRep()
        {
            axisRep = String.format("x = [%f, %f]\ny = [%f, %f]", minX, maxX, minY, maxY);
            System.out.println(axisRep);
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
