import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject implements DamageSystem{

    public BufferedImage playerSprite;
    public KeyHandler keyH;
    public BoundingBox boundingBox;

    public int x, y;
    public int speed;
    public int health, maxHealth;

    public Player(GamePanel gp, KeyHandler keyH, int health, int x , int y, int speed, BoundingBox boundingBox) {
        super(gp);
        this.keyH = keyH;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.boundingBox = boundingBox;
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
    }

    public void draw (Graphics2D g2) {
        BufferedImage image = playerSprite;
        g2.drawImage(image, x, y, 16, 16, null);

        g2.setColor(Color.gray);
        g2.fillRect(boundingBox.getLeftBound(), boundingBox.getBottomBound() + 16, maxHealth*2, 32);
        g2.setColor(Color.red);
        g2.fillRect(boundingBox.getLeftBound()+2, boundingBox.getBottomBound() + 18, health*2-4, 28);
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
}
