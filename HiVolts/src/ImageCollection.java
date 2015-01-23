import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * This is a utility class to get images from the rescoures of this project. It is very useful in Controller.java
 */
public class ImageCollection {


    public static BufferedImage MHO_IMAGE()
    {
        return getImageByName("Mho.png");
    }

    public static BufferedImage PLAYER_IMAGE()
    {
        return getImageByName("Player.png");
    }

    public static BufferedImage FENCE_IMAGE()
    {
        return getImageByName("Fence.png");
    }


    /**
     * Try to find the image, if it does not exist, return null
     * @param name Name of sprite
     * @return The sprite
     */
    public static BufferedImage getImageByName(String name)
    {
        BufferedImage img;

        try {
            img = ImageIO.read(new File(name));

        } catch (IOException e) {
            System.err.println("You have requested a file that does not exist");
            e.printStackTrace();
            img = null;
        }

        return img;
    }

}
