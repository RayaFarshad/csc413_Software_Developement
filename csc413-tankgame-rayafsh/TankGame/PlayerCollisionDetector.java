package TankGame;

import java.awt.*;

public class PlayerCollisionDetector {

    private int temp_x;
    private int temp_y;


    public void playerVSwall(Tank tank , Tile tile) {
        Rectangle tbox = new Rectangle(tank.getX(), tank.getY(), 40, 40);
        tile.addWallrectangle();
        for(int i = 0; i < tile.getWallSize(); i++){
            Rectangle tilebox = new Rectangle(tile.getRectWall().get(i));
            if(tbox.intersects(tilebox)||tilebox.intersects(tbox) ){
                tank.setX(this.temp_x);
                tank.setY(this.temp_y);
//                return true;
            }
        }
        for(int i = 0; i < tile.getWallSize(); i++){
            Rectangle tilebox = new Rectangle(tile.getRectWall().get(i));
            if(!tbox.intersects(tilebox)||!tilebox.intersects(tbox) ){
                this.temp_x = tank.getX();
                this.temp_y = tank.getY();
//                return true;
            }
        }
    }

}
