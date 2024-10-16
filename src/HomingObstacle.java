import java.awt.*;

public class HomingObstacle extends Obstacle {

    public HomingObstacle(GamePanel gp, Player player, int damage, int speedX, int speedY, int x, int y, int width, int height) {
        super(gp, player, damage, speedX, speedY, x, y, width, height);
    }

    int playerX;
    int playerY;

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void move() {

    }
}
