import java.awt.*;

public class Obstacle extends GameObject implements DamageSystem{

    public Player player;
    public int damage;
    public double speedX;
    public double speedY;
    public double x,y;
    public int width;
    public int height;
    public long dissapearTime;

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
        this.dissapearTime = System.currentTimeMillis() + 10000;
    }

    public Obstacle(GamePanel gp, Player player, int damage, int speedX, int x, int y, int width, int height) {
        super(gp);
        this.player = player;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dissapearTime = System.currentTimeMillis() + 10000;

        int disX = player.getX()-x;
        int disY = player.getY()-y;
        double distance = Math.sqrt(Math.pow(disX,2) + Math.pow(disY,2));
        double multiplier = distance/speedX;
        this.speedX = disX/multiplier;
        this.speedY = disY/multiplier;
    }

    @Override
    public void update() {
        move();
        if(x < player.getX() + 16 && y < player.getY() + 16 && x+width > player.getX() && y+height > player.getY()) {dealDamage(player, damage);}
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.magenta);
        g2.fillRect((int)x, (int)y, width, height);
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public Obstacle spawnObstacle(int newDamage, int newSpeedX, int newSpeedY, int newX, int newY, int newWidth, int newHeight) {
        return new Obstacle(gp, player, newDamage, newSpeedX, newSpeedY, newX, newY, newWidth, newHeight);
    }

    @Override
    public void dealDamage(GameObject target, int damage) {
            if (target instanceof Player) {
                if(((Player) target).immunity < System.currentTimeMillis()) {
                    ((Player) target).health -= damage;
                    ((Player) target).immunity = System.currentTimeMillis() + 1000;
                }
            }
    }
}
