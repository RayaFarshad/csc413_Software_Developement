import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {

    private BufferedImage backImg;


    public BufferedImage loadTile(String fileName)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(getClass().getResource(fileName));
        }
        catch (IOException e)
        {

        }

        return img;

    }

    public BufferedImage drawBackground() {
        backImg = loadTile("background-1.png");
        return backImg;
    }

}
