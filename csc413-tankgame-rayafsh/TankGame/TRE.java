package TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import static java.lang.Thread.sleep;

/**
 *
 * @author anthony-pc
 */
public class TRE extends JPanel  {




    public static final int WORLD_WIDTH=1180;
    public static final int WORLD_HEIGHT=800;

    public static final int SCREEN_WIDTH=950;
    public static final int SCREEN_HEIGHT=550;

    private BufferedImage world;
    private BufferedImage p1w;
    private BufferedImage p2w;
    private Graphics2D buffer;
    public Tile tile;



    private BulletCollisionDetector bulletCollision;
    public ArrayList<PowerUp>powerUps = new ArrayList<>();

//    private int p1WindowBoundX, p1WindowBoundY, p2WindowBoundX, p2WindowBoundY;
   // private ArrayList<Bullet>bullets;


    private JFrame jf;
    private Tank t1;
    private Tank t2;

    private Bullet bullet;
   // private PowerUp powerUps;
    private boolean isDead = false;


    public static void main(String[] args) {



        TRE trex = new TRE();
        trex.init();
        trex.setPowerUps();


        try {

            while (true) {



                trex.t1.update();
                trex.t2.update();
                trex.isDead = trex.t1.isDead();
                trex.isDead = trex.t2.isDead();

                //trex.updateBorders();
                trex.setleftPanel(trex.t1);
                trex.setrightPanel(trex.t2);

                //update the bullets of tank 2
                if(trex.t2.getBullet().size() != 0) {
                    for (int i = 1; i < trex.t2.getBullet().size(); i++) {
                        trex.t2.getBullet().get(i).update();
                       // trex.t2.getBullet().get(i).draw(buffer);
                    }

                }
                //uodate the bullets of tank1
                if(trex.t1.getBullet().size() != 0) {
                    for (int i = 1; i < trex.t1.getBullet().size(); i++) {
                        trex.t1.getBullet().get(i).update();
                        //trex.t1.getBullet().get(i).draw(buffer);

                    }

                }

                //handle collision of player 1 and wall
//                if(trex.collision.playerVSwall(trex.t1, trex.tile)){
//                    int tempx = trex.t1.getX();
//                    int tempy = trex.t1.getY();
//                    trex.t1.setX(tempx);
//                    trex.t1.setY(tempy);
//                }
//                //handle collision of player 2 and wall
//                if(trex.collision.playerVSwall(trex.t2, trex.tile)){
//                    int tempx = trex.t2.getX();
//                    int tempy = trex.t2.getY();
//                    trex.t2.setX(tempx);
//                    trex.t2.setY(tempy);
//                }

                //handle collision of player 1  bullets and tile
                for(int i = 0; i < trex.t1.getBullet().size(); i++) {
                    trex.bullet = trex.t1.getBullet().get(i);
                    if(trex.bulletCollision.bulletVStile(trex.t1.getBullet().get(i), trex.tile)){
                        trex.t1.bullet.remove(i);
                        i--;

                    }
                }
                //handle collision of player 2  bullets and tile
                for(int i = 0; i < trex.t2.getBullet().size(); i++) {
                    trex.bullet = trex.t2.getBullet().get(i);
                    if(trex.bulletCollision.bulletVStile(trex.t2.getBullet().get(i), trex.tile)){
                        trex.t2.bullet.remove(i);
                        i--;

                    }
                }
                //handle collision of player 1 and unbreakable wall
                for(int i = 0; i < trex.t1.getBullet().size(); i++) {
                    trex.bullet = trex.t1.getBullet().get(i);
                    if(trex.bulletCollision.bulletVStile_Unbreakable(trex.t1.getBullet().get(i), trex.tile)){
                       // System.out.println("shoot unbreakable");
                        trex.t1.bullet.remove(i);
                        i--;
                    }
                }
                //handle collision of player 2 and unbreakable wall
                for(int i = 0; i < trex.t2.getBullet().size(); i++) {
                    trex.bullet = trex.t2.getBullet().get(i);
                    if(trex.bulletCollision.bulletVStile_Unbreakable(trex.t2.getBullet().get(i), trex.tile)){
                        trex.t2.bullet.remove(i);
                        i--;
                    }
                }
                //handle the collision of player 1 with the bullets of player 2
                for(int i = 0; i < trex.t2.getBullet().size();i++){
                    trex.bullet = trex.t2.getBullet().get(i);
                    if(trex.bulletCollision.playerVSbullet(trex.t1, trex.bullet)){
                       // System.out.println("shoot");
                        if(trex.t1.isDead()){
                           // System.exit(0);
                            trex.isDead = true;
                            JOptionPane.showMessageDialog(null,
                                    "Hey Blue Tank the GAME is OVER!!!",
                                    "Exit",
                                    JOptionPane.WARNING_MESSAGE);

                            trex.exitSys();
                        }


                        trex.t1.setHEALTH();
                        trex.t2.bullet.remove(i);
                        i--;
                    }
                }
                //handle the collision of player 2 with the bullets of player 1
                for(int i = 0; i < trex.t1.getBullet().size();i++){
                    trex.bullet = trex.t1.getBullet().get(i);
                    if(trex.bulletCollision.playerVSbullet(trex.t2, trex.bullet)){
                       // System.out.println("shoot");
                        if(trex.t2.isDead()){
                            trex.isDead = true;
                            JOptionPane.showMessageDialog(null,
                                    "Hey Red Tank the GAME is OVER!!!",
                                    "Exit",
                                    JOptionPane.WARNING_MESSAGE);

                            trex.exitSys();
                        }
                        trex.t2.setHEALTH();
                        trex.t1.bullet.remove(i);
                        i--;
                    }
                }

                for(int i = 0; i < trex.powerUps.size();i++){
                    if(trex.playerVSPowerUps(trex.t2, trex.powerUps.get(i))){
                        if(trex.t2.getHealth() < 100) {
                            trex.t2.healthUp();
                            trex.powerUps.remove(i);
                            System.out.println("2: "+i);
                            i--;

                        }

                    }
                }
                //handle the collision of player1 and power up
                for(int i = 0; i < trex.powerUps.size();i++){
                    if(trex.playerVSPowerUps(trex.t1, trex.powerUps.get(i))){
                        if(trex.t1.getHealth() < 100) {
                            trex.t1.healthUp();
                            trex.powerUps.remove(i);
                            System.out.println("1: "+i);
                            i--;

                        }

                    }
                }

                trex.repaint();

                sleep(1000 / 144);
            }
        }
        catch (InterruptedException ignored) {

        }
    }


    private void init() {
        this.jf = new JFrame("Tank Rotation");
        this.world = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);

        this.tile = new Tile();
        t1 = new Tank(200, 200, 0, 0, 0,this);
        t2 = new Tank(1050, 250, 0, 0, 0,this);
       // this.collision = new PlayerCollisionDetector(t1, t2);
//        this.collision = new PlayerCollisionDetector(t2, tile);
//        this.collision = new PlayerCollisionDetector(t1, tile);
        this.bulletCollision = new BulletCollisionDetector(t1, bullet);
        this.bulletCollision = new BulletCollisionDetector(t2, bullet);
        this.bulletCollision = new BulletCollisionDetector(bullet, tile);


        TankControl tc1 = new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_SPACE);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);


    }
    public void setPowerUps(){
        PowerUp tempPowers1 = new PowerUp(150, 200);
        this.powerUps.add(tempPowers1);
        PowerUp tempPowers2 = new PowerUp(550, 600);
        this.powerUps.add(tempPowers2);
        PowerUp tempPowers3 = new PowerUp(650, 600);
        this.powerUps.add(tempPowers3);
    }
    public boolean playerVSPowerUps(Tank tank, PowerUp powerUp){
        Rectangle tbox = new Rectangle(tank.getX(),tank.getY(),40,40);
        Rectangle powerbox = new Rectangle(powerUp.getX(), powerUp.getY(), 20,20);
        if(tbox.intersects(powerbox) || powerbox.intersects(tbox)){
            return true;
        }
        return false;
    }
    public void setleftPanel(Tank t1){
        if(t1.getX()>700 && t1.getY()>200){
            this.p1w=this.world.getSubimage(680,240,500,560);
        }
        else if(t1.getX()>700){
            this.p1w=this.world.getSubimage(680,t1.getY()-30,500,560);
        }
        else if(t1.getY()>200){
            this.p1w=this.world.getSubimage(t1.getX()-30,240,500,560);
        }
        else if(t1.getX()<=700 && t1.getY()<=200){
            this.p1w=this.world.getSubimage(t1.getX()-20,t1.getY()-20,500,560);
        }
    }
    public void setrightPanel(Tank t2){
        if(t2.getX()>700 && t2.getY()>200){
            this.p2w=this.world.getSubimage(680,240,500,560);
        }
        else if(t2.getX()>700){
            this.p2w=this.world.getSubimage(680,t2.getY()-30,500,600);
        }
        else if(t2.getY()>200){
            this.p2w=this.world.getSubimage(t2.getX()-20,240,500,560);
        }
        else if(t2.getX()<=700 && t2.getY()<=200){
            this.p2w=this.world.getSubimage(t2.getX()-20,t2.getY()-20,500,600);
        }
    }




    public void getBackground(Graphics2D g)
    {
        this.tile.drawLayers(g);
    }
    public void exitSys(){
        System.exit(0);
    }


    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);


        buffer = world.createGraphics();
        this.getBackground(buffer);
       // if(this.isDead){
           // buffer.drawString("GAME OVER!!!", 150, 200);

       // }

        if(this.t2.getBullet().size() != 0) {
            for (int i = 1; i < this.t2.getBullet().size(); i++) {

                this.t2.getBullet().get(i).draw(buffer);
            }
        }
        if(this.t1.getBullet().size() != 0) {
            for (int i = 1; i < this.t1.getBullet().size(); i++) {

                this.t1.getBullet().get(i).draw(buffer);
            }
        }

        if(this.powerUps.size() != 0){
            for (int i = 0; i < this.powerUps.size(); i++) {

                this.powerUps.get(i).draw(buffer);
            }
        }

        this.t1.drawImage_t1(buffer);
        this.t2.drawImage_t2(buffer);


        buffer.setColor(Color.gray);
        buffer.fillRect(this.t1.getX(),this.t1.getY()-10,10,10);
        if(this.t1.getHealth() > 50) {
            buffer.setColor(Color.green);
        }else{
            buffer.setColor(Color.red);
        }
        buffer.fillRect(this.t1.getX(),this.t1.getY()-10,this.t1.getHealth()/2,10);
        buffer.setColor(Color.white);
        buffer.drawRect(this.t1.getX(),this.t1.getY()-10,this.t1.getHealth()/2,10);
        buffer.drawString(this.t1.getHealthStr(), this.t1.getX()-20,this.t1.getY()-20);

        buffer.setColor(Color.gray);
        buffer.fillRect(this.t2.getX(),this.t2.getY()-10,10,10);
        if(this.t2.getHealth()> 50) {
            buffer.setColor(Color.green);
        }else{
            buffer.setColor(Color.red);
        }
        buffer.fillRect(this.t2.getX(),this.t2.getY()-10,this.t2.getHealth()/2,10);
        buffer.setColor(Color.white);
        buffer.drawRect(this.t2.getX(),this.t2.getY()-10,this.t2.getHealth()/2,10);
        buffer.drawString(this.t2.getHealthStr(), this.t2.getX()-20,this.t2.getY()-20);



          //draw player 1 window
        g2.drawImage(world,0,0,null);

        g2.drawImage(this.p1w,0,0,null);
        g2.drawImage(this.p2w,460,0,null);
        g2.drawLine(460,0,460,1200);

        g2.drawImage(this.world.getScaledInstance(255,150,world.SCALE_SMOOTH),350,370,null);
        g2.drawRect(350,370,255,150);

    }
    
}
