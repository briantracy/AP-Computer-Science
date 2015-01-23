
import javax.swing.*;
import java.awt.*;

public class Fractal extends JFrame {





    public void paint(Graphics g) {
            int x = 1;
            for (int i = 0; i <= getWidth(); i += 20)
            {
                for (int j = 0; j <= getHeight(); j += 20)
                {
                    x++;
                    if (escapes(x)) {
                        g.setColor(Color.red);
                    }
                    else {
                        g.setColor(Color.blue);
                    }
                    g.fillRect(i, j, 20, 20);
                    g.setColor(Color.black);
                    g.setFont(new Font("Helvetica", 10,10));
                    g.drawString(""+x, i, j);
                }
            }
    }



    private boolean escapes(long x) {
        return collatz(x)[1] > 400;
    }

    private long[] collatz(long x) {
        long generations = 0;
        long max = 0;

        while (x != 1) {
            generations++;
            if (x > max) max = x;

            if (x % 2 == 0) {
                x /= 2;
            }
            else {
                x *= 3; x++;
            }
        }
        return new long[] {generations, max};
    }

}
