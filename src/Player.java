import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
    GamePanel gp;
    KeyHandler keyH;

    public BufferedImage playerSprite;

    public int x, y;
    public int speed;

    public Player(GamePanel gp, KeyHandler keyH, int x , int y, int speed) {
        this.gp = gp;
        this.keyH = keyH;
        this.x = x;
        this.y = y;
        this.speed = speed;

        getPlayerSprite();
    }

    public void update() {
        if(keyH.upPressed){
            y -= speed;
        }
        if(keyH.downPressed){
            y += speed;
        }
        if(keyH.leftPressed){
            x -= speed;
        }
        if(keyH.rightPressed){
            x += speed;
        }
    }

    public void draw (Graphics2D g2) {
        BufferedImage image = playerSprite;
        g2.drawImage(image, x, y, 16, 16, null);
    }

    public void getPlayerSprite() {
        try {
            playerSprite = ImageIO.read(getClass().getResourceAsStream("/images/heart.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
