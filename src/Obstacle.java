import java.awt.*;

public class Obstacle extends GameObject implements DamageSystem{

    public Player player;
    public int damage;
    public int speedX;
    public int speedY;
    public int x,y;
    public int width;
    public int height;

    public Obstacle(GamePanel gp, Player player, int damage, int speedX, int speedY, int x, int y, int width, int height) {
        super(gp);
        this.player = player;
        this.damage = damage;
        this.speedX = speedX;
        this.speedY = speedY;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.magenta);
        g2.fillRect(x, y, width, height);
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public Obstacle spawnFixedObstacle(int newDamage, int newSpeedX, int newSpeedY, int newX, int newY, int newWidth, int newHeight) {
        return new Obstacle(gp, player, newDamage, newSpeedX, newSpeedY, newX, newY, newWidth, newHeight);
    }

    public Obstacle spawnHomingObstacle(int newDamage, int newSpeedX, int newSpeedY, int newX, int newY, int newWidth, int newHeight) {
        return new Obstacle(gp, player,newDamage, newSpeedX, newSpeedY, newX, newY, newWidth, newHeight);
    }

    @Override
    public void dealDamage(GameObject target, int damage) {
        if(target instanceof Player){
            ((Player) target).health -= damage;
        }
    }
}
