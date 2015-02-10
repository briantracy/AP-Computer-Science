import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Visualizer extends JFrame {

    final int CIRCLE_DIAMETER = 60;
    final int CIRCLE_RADIUS   = CIRCLE_DIAMETER / 2;

    ArrayList<Point> points = new ArrayList<Point>();

    boolean isConnecting = false;

    Point connectionA, connectionB;


    public Visualizer()
    {
        setBackground(Color.blue);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);

        addMouseListener();
    }

    private void addMouseListener()
    {
        getContentPane().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Point press = mouseEvent.getLocationOnScreen();
                press.y -= 22;
                press(press);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                Point press = mouseEvent.getLocationOnScreen();
                press.y -= 22;
                release(press);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }


    private void press(Point p)
    {
        if (hasPressedHere(p)) {
            isConnecting = true;
            connectionA = p;
        }

        repaint();

    }

    private void release(Point p)
    {
        points.add(p);

        repaint();

        System.out.println("release");
    }

    private int distance(Point a, Point b)
    {
        int x = a.x - b.x;
        int y = a.y - b.y;
        return (int)(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }

    private boolean isSameAs(Point a, Point b)
    {
        return distance(a, b) < CIRCLE_DIAMETER;
    }

    private boolean hasPressedHere(Point other)
    {
        for (Point pt : points) {
            if (isSameAs(pt, other)) return true;
        }
        return false;
    }


    @Override
    public void paint(Graphics g) {

        g.setColor(Color.orange);
        for (Point p : points) {

            Point orig = new Point(p.x - CIRCLE_RADIUS, p.y - CIRCLE_RADIUS);

            g.fillOval(orig.x, orig.y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);

        }

    }
}

