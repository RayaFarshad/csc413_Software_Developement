import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RocketControl implements KeyListener {
    private Rocket rocket;
    private final int right;
    private final int left;
    private final int shoot;


    public RocketControl(Rocket rocket, int right, int left, int shoot) {
        this.rocket = rocket;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();

        if (keyPressed == left) {
            this.rocket.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.rocket.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.rocket.toggleShootPressed();

        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == left) {
            this.rocket.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.rocket.unToggleRightPressed();
        }
        if (keyReleased  == shoot) {
            this.rocket.unToggleShootPressed();
            this.rocket.setShootPressed();

        }
    }
}
