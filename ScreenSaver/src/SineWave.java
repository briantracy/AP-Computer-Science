import javax.swing.*;
import java.awt.*;

public class SineWave extends JFrame
{
    public SineWave()
    {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);

        yAxis = getHeight() / 2;

        for (; x < getWidth(); x++) {

            try {Thread.sleep(10);}catch (Exception e){}

            repaint();


        }

    }
    int x = 0;
    int yAxis;
    Point prev = new Point(0, yAxis);

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics = (Graphics2D)g;

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


            int y = sine(x) + yAxis;

            graphics.setStroke(new BasicStroke(40));
            graphics.setColor(Color.orange);
            graphics.drawLine(prev.x, prev.y, x, y);

            prev.x = x;
            prev.y = y;



    }

    private int sine(int x)
    {
        return (int)((getHeight() / 2) * Math.sin(((2*Math.PI) / 200) * x));
    }
}
