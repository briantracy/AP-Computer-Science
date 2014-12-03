import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class ASCIICreator {

    private File input, output;

    private final String ASCII = "@%#*+=-:. "; //"@%#*+=-:. " +

    public ASCIICreator(File input, File output)
    {
        this.input = input;
        this.output = output;
    }


    public void renderImage()
    {
        // Get the Image
        BufferedImage bufferedImage = null;
        PrintWriter printWriter  = null;
        try {
            BufferedImage in = ImageIO.read(input);
            bufferedImage = in;//new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);


            printWriter = new PrintWriter(output);

        } catch (IOException ioe) {
            System.err.println("Invalid Image File Name");
            System.exit(-1);
        }

        // Get the Pixel Data

        int[][][] pixelData = new int[bufferedImage.getWidth()][bufferedImage.getHeight()][3];

        for (int x = 0; x < bufferedImage.getWidth(); x++)
        {
            for (int y = 0; y < bufferedImage.getHeight(); y++)
            {
                pixelData[x][y] = getRGB(bufferedImage, x, y);
            }
        }

        // get luminosities

        int[][] luminosities = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        int max = 0;
        for (int x = 0; x < bufferedImage.getWidth(); x++)
        {
            for (int y = 0; y < bufferedImage.getHeight(); y++)
            {
                luminosities[x][y] = (int)getLuminosity(pixelData[x][y]);
                if (luminosities[x][y] > max) {
                    max = luminosities[x][y];
                }

                System.out.println("rgb = " + Arrays.toString(pixelData[x][y]) + " lumin = " + luminosities[x][y]);
            }
        }
        System.out.println(max);




        for (int y = 0; y < bufferedImage.getHeight(); y++)
        {
            for (int x = 0; x < bufferedImage.getWidth(); x++)
            {

                printWriter.write(charForLuminosity(luminosities[x][y]));
            }
            printWriter.write("\n");
        }

        printWriter.close();;

    }

    private char charForLuminosity(int lum)
    {
        int len = ASCII.length();

        double size = (255.0 / len);

        int bucket = (int)(lum / size);
        System.out.println(ASCII.charAt(bucket));
        return ASCII.charAt(bucket);
    }

    private static int[] getRGB(BufferedImage img, int x, int y)
    {
        int rgb = img.getRGB(x, y);

        Color c = new Color(rgb);

        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        return new int[] {red, green, blue};
    }

    private static double getLuminosity(int[] rgb)
    {
        return (0.2126*rgb[0] + 0.7152*rgb[1] + 0.0722*rgb[2]);
    }
}
