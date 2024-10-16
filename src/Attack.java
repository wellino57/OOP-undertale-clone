import java.awt.*;

public class Attack extends GameObject{

    public Player player;
    public int damage;
    public int speed;
    public int x,y;
    public int width;
    public int height;

    public Attack(GamePanel gp, Player player, int damage, int speed, int x, int y, int width, int height) {
        super(gp);
        this.player = player;
        this.damage = damage;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }

    public void showdown() {

    }
}
