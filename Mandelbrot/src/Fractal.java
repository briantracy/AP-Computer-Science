/// Created by Brian Clement Tracy
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Fractal extends JFrame
{
    Window window;

    private final double scaleFactor = 10;

    private String axisRep = "x = [-2, 1]\ny = [-1, 1]";

    private static final int PIXEL_SIZE = 1;

    static  Color[] color_lookup = new Color[16];


    static {
        color_lookup[0] = new Color(66, 30, 15);
        color_lookup[1] = new Color(25, 7, 26);
        color_lookup[2] = new Color(9, 1, 47);
        color_lookup[3] = new Color(4, 4, 73);
        color_lookup[4] = new Color(0, 7, 100);
        color_lookup[5] = new Color(12, 44, 138);
        color_lookup[6] = new Color(24, 82, 177);
        color_lookup[7] = new Color(57, 125, 209);
        color_lookup[8] = new Color(134, 181, 229);
        color_lookup[9] = new Color(211, 236, 248);
        color_lookup[10] = new Color(241, 233, 191);
        color_lookup[11] = new Color(248, 201, 95);
        color_lookup[12] = new Color(255, 170, 0);
        color_lookup[13] = new Color(204, 128, 0);
        color_lookup[14] = new Color(153, 87, 0);
        color_lookup[15] = new Color(106, 52, 3);
    }

    private Color getColorForIters(int iters)
    {
        int bucketWidth = 255 / 16;


        return color_lookup[iters % 16];
    }

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
        for (int x = 0; x < getWidth(); x += PIXEL_SIZE)
        {
            for (int y = 0; y < getHeight(); y += PIXEL_SIZE)
            {
                int iters = this.window.complexForPoint(x,y).escapeIters();
                g.setColor(colorForEscapeIters(iters));
                g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
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

        return getColorForIters(escapeIters);
//        if (escapeIters == -1) return Color.black;
//
//        return new Color(escapeIters / 2, escapeIters, escapeIters / 3).brighter().brighter().brighter();


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

}
