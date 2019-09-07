import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Thread.sleep;

import java.util.Dictionary;
import java.util.concurrent.ThreadLocalRandom;


public class TRE extends JPanel {

    public static final int WORLD_WIDTH=1200;
    public static final int WORLD_HEIGHT=900;


    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Background background;
    private Rocket rocket;
    private CheckAsteroidCollision checkAsteroidCollision;
    private CheckMoonCollision checkMoonCollision;
    public ArrayList<Moon>moons = new ArrayList<>();
    public ArrayList<Asteroid>asteroids = new ArrayList<>();
    private Score score;
    private int nextLevel = 0;
    public static final int DIFFICULTY_LEVEL=2;



    public static void main(String[] args) {

        TRE trex = new TRE();

        trex.setMoons();
        trex.setAsteroids();
        trex.init();
        try {


            while (true) {
                trex.rocket.update();
                trex.repaint();
                if(trex.asteroids.size() != 0) {
                    for (int i = 1; i < trex.asteroids.size(); i++) {
                        trex.asteroids.get(i).update();
                        if(trex.asteroids.get(i).getX() > 1000 || trex.asteroids.get(i).getY() > 900 ){
                            int randomNumx = ThreadLocalRandom.current().nextInt(10, 900);
                            int randomNumy = ThreadLocalRandom.current().nextInt(0, 10);
                            trex.asteroids.get(i).setX(randomNumx);
                            trex.asteroids.get(i).setY(randomNumy);
                        }

                    }

                }
                if(trex.score.getSCORES() == 8000) {
                    JOptionPane.showMessageDialog(null,
                            "You hit the special score 8000",
                            "Exit",
                            JOptionPane.WARNING_MESSAGE);
                }

                if(trex.score.getSCORES() > 8000){
                    if(trex.nextLevel == 1){
                        continue;
                    }
                    trex.nextLevel = 1;

                    for (int i = 1; i != DIFFICULTY_LEVEL; i++) {
                        trex.asteroids.get(i).update();
                        if(trex.asteroids.get(i).getX() > 1000 || trex.asteroids.get(i).getY() > 900 ){
                            int randomNumx = ThreadLocalRandom.current().nextInt(0, 900);
                            int randomNumy = ThreadLocalRandom.current().nextInt(0, 10);
                            Asteroid asteroid1 = new Asteroid(randomNumx,100,randomNumy,10,50);
                            trex.asteroids.add(asteroid1);
                        }

                    }


                }

                trex.nextLevel = 0;



                    for(int i = 0; i < trex.moons.size(); i++){
                        if (trex.checkMoonCollision.checkMoonVsRocket(trex.rocket, trex.moons)){

                         trex.rocket.setIsOnMoon(true);
                         trex.score.scoreDown();


                         }else{
                          trex.rocket.setIsOnMoon(false);
                          trex.score.setSCORES(true);
                          trex.score.scoreUp();

                          }
                    }
                for(int i = 0; i < trex.asteroids.size(); i++){
                    if(trex.checkAsteroidCollision.checkRacketVsAst(trex.rocket,trex.asteroids) && !trex.rocket.isOnMoon || trex.score.getSCORES() == 0){
                        JOptionPane.showMessageDialog(null,
                                "The GAME is OVER!!!",
                                "Exit",
                                JOptionPane.WARNING_MESSAGE);

                        trex.exitSys();
                    }
                }

                sleep(100 / 14);

            }
        }  catch (InterruptedException ignored) {

        }

    }

    private void init() {

        this.jf = new JFrame("Galactic World");
        this.world = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);

        this.background = new Background();
        this.rocket = new Rocket(this.moons.get(1).getX(), this.moons.get(1).getY(),  20,  20,  0,  this);
        RocketControl rc = new RocketControl(this.rocket,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
        this.checkAsteroidCollision = new CheckAsteroidCollision();
        this.checkMoonCollision = new CheckMoonCollision();
        this.score = new Score();


        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(rc);

        this.jf.setSize(TRE.WORLD_WIDTH, TRE.WORLD_HEIGHT);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);



    }
    public void exitSys(){
        System.exit(0);
    }

    public void setMoons(){
        int randomNumx = ThreadLocalRandom.current().nextInt(60, 800);
        int randomNumy = ThreadLocalRandom.current().nextInt(50, 800);
        Moon moon1 = new Moon(randomNumx,randomNumy,4);
        this.moons.add(moon1);
        int randomNumx1 = ThreadLocalRandom.current().nextInt(30, 800);
        int randomNumy1 = ThreadLocalRandom.current().nextInt(50, 600);
        Moon moon2 = new Moon(randomNumx1,randomNumy1,6);
        this.moons.add(moon2);
        int randomNumx2 = ThreadLocalRandom.current().nextInt(100, 800);
        int randomNumy2 = ThreadLocalRandom.current().nextInt(50, 830);
        Moon moon3 = new Moon(randomNumx2,randomNumy2,12);
        this.moons.add(moon3);
        int randomNumx3 = ThreadLocalRandom.current().nextInt(150, 800);
        int randomNumy3 = ThreadLocalRandom.current().nextInt(100, 750);
        Moon moon4 = new Moon(randomNumx3,randomNumy3,9);
        this.moons.add(moon4);

    }
    public void setAsteroids(){
        Asteroid asteroid1 = new Asteroid(150,100,10,10,50);
        this.asteroids.add(asteroid1);
        Asteroid asteroid2 = new Asteroid(150,70,10,10,60);
        this.asteroids.add(asteroid2);
        Asteroid asteroid3 = new Asteroid(150,150,10,10,90);
        this.asteroids.add(asteroid3);
//        Asteroid asteroid4 = new Asteroid(90,90,10,10,47);
//        this.asteroids.add(asteroid4);
//        Asteroid asteroid5 = new Asteroid(110,110,10,10,77);
//        this.asteroids.add(asteroid5);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.drawImage(world,0,0,this);
        buffer.drawImage(background.drawBackground(),0,0,null);
        if(this.moons.size() != 0){
            for (int i = 0; i < this.moons.size(); i++) {

                this.moons.get(i).drawMoon(buffer);

            }
        }
        if(this.asteroids.size() != 0) {
            for (int i = 1; i < this.asteroids.size(); i++) {

                this.asteroids.get(i).draw(buffer);
            }
        }
        this.rocket.drawRacket(buffer);
        g2.drawImage(world,0,0,null);

        g.fillRect(435,15,250,45);
        if(this.score.getSCORES() < 2000){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.white);
        }
        g.drawRect(435,15,250,45);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 27));
        g.drawString("The Score is: "+this.score.getSCORESstr(), 448,50);



    }


}