import java.awt.*;

public class Obstacle extends GameObject implements DamageSystem{

    Player player;
    int damage;
    double speedX;
    double speedY;
    double x,y;
    int width;
    int height;
    long dissapearTime;

    public Obstacle(int spanishOrVanish, GamePanel gp, Player player, int damage, int speedX, int speedY, int x, int y, int width, int height) {
        super(gp);
        this.player = player;
        this.damage = damage;
        this.speedX = speedX;
        this.speedY = speedY;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dissapearTime = System.currentTimeMillis() + spanishOrVanish;
    }

    public Obstacle(int spanishOrVanish, GamePanel gp, Player player, int damage, int speedX, int x, int y, int width, int height) {
        super(gp);
        this.player = player;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dissapearTime = System.currentTimeMillis() + spanishOrVanish;

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
        g2.setColor(Color.white);
        g2.fillRect((int)x, (int)y, width, height);
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    @Override
    public void dealDamage(GameObject target, int damage) {
            if (target instanceof Player) {
                if(((Player) target).immunity < System.currentTimeMillis()) {
                    ((Player) target).health -= damage * gp.getDamageMult();
                    ((Player) target).health = Math.max(((Player) target).health, 0);
                    ((Player) target).immunity = System.currentTimeMillis() + 1000;
                }
            }
    }
}
