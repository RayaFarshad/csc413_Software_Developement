package TankGame;


import java.awt.*;


public class Bullet{
    private int x;
    private int y;
    private int width;
    private int height;
    private int angle;


    public Bullet(int x, int y, int width, int height, int angle){
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

    public void update(){
        x = x + width;
        y = y + height;
        width = (int) Math.round(5 * Math.cos(Math.toRadians(angle)));
        height = (int) Math.round(5 * Math.sin(Math.toRadians(angle)));

    }

    public void draw(Graphics g) {
       // g.drawImage(img, x, y, null);
        Color c=g.getColor();
        g.setColor(Color.black);
        g.fillOval(x,y,5,5);
        g.setColor(c);

    }
}
