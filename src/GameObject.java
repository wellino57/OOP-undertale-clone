import java.awt.*;

public abstract class GameObject {
    GamePanel gp;

    public GameObject(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}
