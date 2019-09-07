

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Moon {

    private int x;
    private int y;
    private int angle;

    private BufferedImage moonImg;
    private int ROTATE_SPEED=4;
    boolean isVisited = false;



    Moon(int x, int y,int angle){
        this.x = x;
        this.y = y;

        this.angle = angle;


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
    public void selfRotate(){
        this.angle+=ROTATE_SPEED;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public boolean getVisited() {
        return this.isVisited;
    }

    public void setMoonVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void moonTypeImg(){
        if(this.isVisited){

            this.moonImg = loadImg("Visited.png");
        }else{

            this.moonImg = loadImg("moon.png");
        }
    }

    public void drawMoon(Graphics g) {
        moonTypeImg();
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.moonImg.getWidth() / 2.0, this.moonImg.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.moonImg, rotation, null);
        selfRotate();

    }
}
