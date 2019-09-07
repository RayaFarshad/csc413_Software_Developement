package TankGame;


import java.awt.*;
import java.util.ArrayList;

public class BulletCollisionDetector {
    private int bullet_x;
    private int bullet_y;
    Bullet bullet;
    Tank tank;
    Tile tile;
    ArrayList<Rectangle>blocks;

    ////////////////tank and bullet collide/////////////////////



    public BulletCollisionDetector(Tank tank, Bullet bullet) {
        this.bullet = bullet;
        this.tank = tank;

    }
    public BulletCollisionDetector(Bullet bullet, Tile tile){
        this.tile = tile;
        this.bullet = bullet;
    }
    public boolean playerVSbullet(Tank t1, Bullet bullet){

        Rectangle tbox2 = new Rectangle(t1.getX(), t1.getY(), 40, 40);
        Rectangle bulletBox = new Rectangle(bullet.getX(),bullet.getY(),5,5);
        if(tbox2.intersects(bulletBox)){
            return true;
        }
        return false;
    }


    /////////////////bullet and tile collide/////////////////
    public boolean bulletVStile(Bullet bullet, Tile tile){
        tile.addWallrectangle();
        this.blocks = tile.getRectWall();
        Rectangle bulletbox = new Rectangle(bullet.getX(), bullet.getY(), 5, 5);
        for(int i = 0; i < tile.getWallSize(); i++){
            Rectangle tilebox = new Rectangle(tile.getRectWall().get(i));
            if(bulletbox.intersects(tilebox)){
                this.bullet_y = (int)blocks.get(i).getX()/20;
                this.bullet_x = (int)blocks.get(i).getY()/20;
                if(tile.getmap()[bullet_x][bullet_y] == 5){
                    tile.breakWall(bullet_x,bullet_y);
                }

                return true;
            }
        }
        return false;
    }
    public boolean bulletVStile_Unbreakable(Bullet bullet, Tile tile){
        tile.addWallrectangle();
        this.blocks = tile.getRectWall();
        Rectangle bulletbox = new Rectangle(bullet.getX(), bullet.getY(), 5, 5);
        for(int i = 0; i < tile.getWallSize(); i++){
            Rectangle tilebox = new Rectangle(tile.getRectWall().get(i));
            if(bulletbox.intersects(tilebox)){
                this.bullet_x = (int)blocks.get(i).getX()/20;
                this.bullet_y = (int)blocks.get(i).getY()/20;
                if(tile.getmap()[bullet_x][bullet_y] == 1){


                }

                return true;
            }
        }
        return false;
    }


}
