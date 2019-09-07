package TankGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//As I understand in the Tile we have a map matrix
//and we have a tile(BufferImage) we are loading those bufferImage and  in the draw function we draw for each number in the map the equivalent
//buffer image so then we have the map of tile and walls and everything
//in the tile class create some method to to put the tiles in an arraylist and later you will use the tile arraylist and the tank for the
//collision

public class Tile {

    private int[][] map;
    private BufferedImage tileSheet;
    private BufferedImage boundry;
    private BufferedImage breakWall;
    private ArrayList<Rectangle>rect = new ArrayList<>();
    private ArrayList<Rectangle>rectWall = new ArrayList<>();


    public Tile(){
        Map m = new Map();
        m.createMap();
        this.map = m.getMap();
    }

    public BufferedImage loadTile(String fileName)
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

//    public void addrectangle() {
//        for (int y = 0; y < this.map.length; y++) {
//            for (int x = 0; x < this.map[y].length; x++) {
//                Rectangle temp = new Rectangle(x,y,x*20,y*20);
//                this.rect.add(temp);
//            }
//        }
//    }


    public void addWallrectangle() {
        this.rectWall.clear();
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                int index = this.map[y][x];
                if(index == 5 || index == 1) {
                    Rectangle temp = new Rectangle(x * 20, y * 20,20, 20);
                    this.rectWall.add(temp);

                }
            }
        }
       // System.out.println(rectWall);

    }
    public ArrayList<Rectangle> getRectWall(){

        return this.rectWall;
    }
    public void breakWall(int x, int y){
        //set wall to green tile
        this.map[x][y]=0;
    }
    public int[][] getmap(){
        //set wall to green tile
        return this.map;
    }



    public int getSize(){
        return this.rect.size();
    }
    public int getWallSize(){

        return this.rectWall.size();
    }


    //
    public void drawLayers(Graphics g){
        tileSheet = loadTile("TankGame/res/wood2.jpg");
        //tileSheet = loadTile("/Users/rayafarshad/csc413-tankgame-rayafsh/jar.Resource/tile3.jpgfile:/Users/rayafarshad/csc413-tankgame-rayafsh/jar.Resource/wood2.jpg");
        boundry = loadTile("TankGame/res/bound.png");
        breakWall = loadTile("TankGame/res/wall.jpg");
        for(int y = 0; y < this.map.length; y++)
        {
            for(int x = 0; x < this.map[y].length;x++)
            {
                int index = this.map[y][x];
                if(index == 0){
                    g.drawImage(tileSheet,x*20,y*20,20,20,null);
                }else if(index == 1){
                    g.drawImage(boundry,x*20,y*20,20,20,null);
                }
                else {
                    g.drawImage(breakWall,x*20,y*20,20,20,null);
                }

            }
        }

    }



}
