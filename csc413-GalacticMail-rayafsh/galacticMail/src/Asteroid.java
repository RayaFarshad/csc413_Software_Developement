import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Asteroid {
    private int x;
    private int y;
    private int width;
    private int height;
    private int angle;
    private BufferedImage AsteroidImg;


    public Asteroid(int x, int y, int width, int height, int angle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;

    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
         this.y = y;
    }


    public void update(){
        x = x + width;
        y = y + height;
        width = (int) Math.round(1 * Math.cos(Math.toRadians(angle)));
        height = (int) Math.round(1 * Math.sin(Math.toRadians(angle)));

    }

    public BufferedImage loadImg(String fileName)
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
    public void draw(Graphics g) {
        AsteroidImg = loadImg("Asteroid.png");
        g.drawImage(AsteroidImg, x, y, null);


    }

}
