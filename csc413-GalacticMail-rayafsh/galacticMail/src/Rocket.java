
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Thread.sleep;


public class Rocket {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private final int ROTATIONSPEED = 1;
    private final int R = 1;//how fast to go
    private BufferedImage flying;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shootPressed;
    private TRE tre;
    boolean isOnMoon = true;


    Rocket(int x, int y, int vx, int vy, int angle, TRE tre) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
        this.tre=tre;
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

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }


    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void toggleShootPressed() { this.shootPressed = false; }
    void unToggleShootPressed() { this.shootPressed = true; }

    public void update() {
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }

        if (this.shootPressed && checkForMoon()) {
         //   this.setShootPressed();

                moveForwards();

            //checkForMoon();

        }
        if (!this.shootPressed && !checkForMoon()) {
            //   this.setShootPressed();

            moveForwards();

            //checkForMoon();

        }


    }

    private boolean checkForMoon() {
      if(this.isOnMoon){

          return false;
      }
      return true;

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }
    private void moveForwards() {

        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();

    }
    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= TRE.WORLD_WIDTH - 88) {
            x = TRE.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= TRE.WORLD_HEIGHT - 80) {
            y = TRE.WORLD_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    //u can use for every other obj
    void drawRacket(Graphics g) {

        flying = loadImg("Flying.png");
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.flying.getWidth() / 2.0, this.flying.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(flying, rotation, null);
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public boolean getOnMoon() {
        return isOnMoon;
    }
    public void setIsOnMoon(boolean isOnMoon){
        this.isOnMoon = isOnMoon;
    }

    public void setShootPressed() {

    }
}
