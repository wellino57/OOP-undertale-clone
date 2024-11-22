import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject implements DamageSystem{

    BufferedImage playerSprite;
    KeyHandler keyH;
    BoundingBox boundingBox;
    Enemy enemy;

    int x, y;
    int speed;
    int health, maxHealth;
    long immunity = 0;

    public Player(GamePanel gp, KeyHandler keyH, int health, int x , int y, int speed, BoundingBox boundingBox, Enemy enemy) {
        super(gp);
        this.keyH = keyH;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.boundingBox = boundingBox;
        this.enemy = enemy;
        this.health = health;
        this.maxHealth = health;

        getPlayerSprite();
    }

    public void update() {
        if(keyH.upPressed && y > boundingBox.getTopBound()){
            y -= speed;
        }
        if(keyH.downPressed && y < boundingBox.getBottomBound()-16){
            y += speed;
        }
        if(keyH.leftPressed && x > boundingBox.getLeftBound()){
            x -= speed;
        }
        if(keyH.rightPressed && x < boundingBox.getRightBound()-16){
            x += speed;
        }

        if(x < boundingBox.getLeftBound()){
            x = boundingBox.getLeftBound();
        }
        if(x+16 > boundingBox.getRightBound()){
            x = boundingBox.getRightBound()-16;
        }
        if(y < boundingBox.getTopBound()){
            y = boundingBox.getTopBound();
        }
        if(y+16 > boundingBox.getBottomBound()){
            y = boundingBox.getBottomBound()-16;
        }

        //helper detection
        if(x < enemy.getHealerX() + enemy.getHelperSize() && y < enemy.getHealerY() + enemy.getHelperSize() && x+16 > enemy.getHealerX() && y+16 > enemy.getHealerY()){
            enemy.setHealerX(-1000000);
            enemy.setHealerY(-1000000);
            enemy.setDamagerX(-1000000);
            enemy.setDamagerY(-1000000);
            System.out.println("Touch");
            health = Math.min(health+enemy.getHelpAmount(), maxHealth);
        }
        if(x < enemy.getDamagerX() + enemy.getHelperSize() && y < enemy.getDamagerY() + enemy.getHelperSize() && x+16 > enemy.getDamagerX() && y+16 > enemy.getDamagerY()){
            enemy.setHealerX(-1000000);
            enemy.setHealerY(-1000000);
            enemy.setDamagerX(-1000000);
            enemy.setDamagerY(-1000000);
            System.out.println("Touch");
            dealDamage(enemy, enemy.getHelpAmount());
        }
    }

    public void draw (Graphics2D g2) {
        BufferedImage image = playerSprite;
        g2.drawImage(image, x, y, 16, 16, null);

        g2.setColor(Color.gray);
        g2.fillRect(0, 0, maxHealth*2, 32);
        g2.setColor(Color.red);
        g2.fillRect(2, 2, health*2-4, 28);
    }

    @Override
    public void dealDamage(GameObject target, int damage) {
        if (target instanceof Enemy) {
            ((Enemy) target).health -= damage;
        }
    }

    public void getPlayerSprite() {
        try {
            playerSprite = ImageIO.read(getClass().getResourceAsStream("/images/heart.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
