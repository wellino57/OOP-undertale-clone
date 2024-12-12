import java.awt.*;

public abstract class GameObject {
    GamePanel gp;

    public GameObject(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void update() throws InterruptedException;
    public abstract void draw(Graphics2D g2);
}
