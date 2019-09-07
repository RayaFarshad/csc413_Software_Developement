import java.awt.*;
import java.util.ArrayList;

public class CheckMoonCollision {

    private Rocket rocket;
    private Score score;
    public TRE tre=new TRE();

    CheckMoonCollision(){
        this.score = new Score();
    }

    public boolean checkMoonVsRocket(Rocket rocket, ArrayList<Moon> moons){
        this.rocket = rocket;
        this.tre.moons = moons;


        for(int i = 0; i < moons.size(); i++){
            Rectangle rbox = new Rectangle(this.rocket.getX(), this.rocket.getY(), 20,20);
            Rectangle moonbox = new Rectangle(this.tre.moons.get(i).getX(),this.tre.moons.get(i).getY(), 20,20);
            if(rbox.intersects(moonbox)){
                this.tre.moons.get(i).setMoonVisited(true);

                return true;
            }
        }

        return false;

    }
}
