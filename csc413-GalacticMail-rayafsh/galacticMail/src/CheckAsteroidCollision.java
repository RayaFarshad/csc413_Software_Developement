import java.awt.*;
import java.util.ArrayList;

public class CheckAsteroidCollision {

    private Rocket rocket;
    public TRE tre=new TRE();



    public boolean checkRacketVsAst(Rocket rocket, ArrayList<Asteroid> asteroids){
        this.rocket = rocket;
        this.tre.asteroids = asteroids;
        for(int i = 0; i < asteroids.size(); i++) {
            Rectangle rbox = new Rectangle(this.rocket.getX(), this.rocket.getY(), 20, 20);
            Rectangle astbox = new Rectangle(this.tre.asteroids.get(i).getX(), this.tre.asteroids.get(i).getY(), 20, 20);
            if (rbox.intersects(astbox)) {
                return true;
            }
        }
        return false;
    }
}
