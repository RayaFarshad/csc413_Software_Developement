package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 *
 * @author anthony-pc
 */
public class Tank{


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private  int HEALTH = 100;
//    private int life = 4;
    private final int R = 2;//how fast to go
    private final int ROTATIONSPEED = 4;


    private TRE tre;
    private BufferedImage img1;
    private BufferedImage img2;
    private boolean UpPressed;//weather the key is pressed or not
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shootPressed;
    private PlayerCollisionDetector colide = new PlayerCollisionDetector();

    public ArrayList<Bullet>bullet = new ArrayList<>();



//    Tank(int x, int y, int vx, int vy, int angle) {
//        this.x = x;
//        this.y = y;
//        this.vx = vx;
//        this.vy = vy;
//        this.angle = angle;
//    }
    Tank(int x, int y, int vx, int vy, int angle, TRE tre){
       // this(x,y,vx,vy,angle);
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
            img = ImageIO.read(new File(fileName));
        }
        catch (IOException e)
        {

        }
        return img;

    }

    public void healthUp() {
            this.HEALTH = 100;
    }
    public int getHealth() {
        return this.HEALTH;
    }
    public void setHEALTH(){
        this.HEALTH -=20;
    }

    public boolean isDead() {
        if (this.HEALTH <= 0) {

            return true;
        }
        return false;
    }
    public String getHealthStr(){
        return String.valueOf(this.HEALTH/20);
    }



    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }
    void toggleShootPressed() { this.shootPressed = true; }
    void unToggleShootPressed() { this.shootPressed = false; }




    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.shootPressed) {
//            this.setShootPressed();
        }

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {

       // System.out.println("backward");
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        colide.playerVSwall(this , tre.tile);
        checkBorder();


    }

    private void moveForwards() {

        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        colide.playerVSwall(this , tre.tile);
        checkBorder();

    }
    public void setShootPressed(){

        Bullet tmpBullet = new Bullet(this.x+20, this.y+20, this.vx, this.vy, this.angle);
        this.bullet.add(tmpBullet);

    }


    public ArrayList<Bullet> getBullet(){
        return this.bullet;
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
    void drawImage_t1(Graphics g) {

        img1 = loadImg("TankGame/res/tank1.png");
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img1.getWidth() / 2.0, this.img1.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img1, rotation, null);
    }
    void drawImage_t2(Graphics g) {
        img2 = loadImg("TankGame/res/tank2.png");
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img2.getWidth() / 2.0, this.img2.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img2, rotation, null);
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

}