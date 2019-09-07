package TankGame;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PowerUp {
    private int x;
    private int y;
    BufferedImage img;



    public PowerUp(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public BufferedImage loadPower(String fileName)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(fileName));
        }
        catch (IOException e)
        {

        }
        return img;
    }


    public void draw(Graphics g) {
        img = loadPower("TankGame/res/heart.png");
        g.drawImage(img, x, y, null);
    }
}
