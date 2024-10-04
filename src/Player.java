import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject {

    public BufferedImage playerSprite;
    public KeyHandler keyH;
    public BoundingBox boundingBox;

    public int x, y;
    public int speed;

    public Player(GamePanel gp, KeyHandler keyH, int x , int y, int speed, BoundingBox boundingBox) {
        super(gp);
        this.keyH = keyH;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.boundingBox = boundingBox;

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
    }

    public void getPlayerSprite() {
        try {
            playerSprite = ImageIO.read(getClass().getResourceAsStream("/images/heart.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
