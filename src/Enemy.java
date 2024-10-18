import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject{

    public Obstacle ob = null;
    public int health;
    public Player player;
    public BoundingBox boundingBox;
    int attackSpeed = GamePanel.FPS / 2;
    int attackInterval = attackSpeed;

    public Enemy(GamePanel gp, Player player, BoundingBox boundingBox) {
        super(gp);
        this.player = player;
        this.boundingBox = boundingBox;
    }

    @Override
    public void update() {
        if(attackInterval > 0) {
            attackInterval--;
        }else if(ob != null && ob.x > boundingBox.getLeftBound()-130 && ob.x < boundingBox.getRightBound()+130 && ob.y > boundingBox.getTopBound()-130 && ob.y < boundingBox.getBottomBound()+130) {
            attackInterval++;
        }else {
            Random rand = new Random();
            int sX, sY, pX, pY;
            int option = rand.nextInt(4);
            if(option == 0) {
                sX = 0;
                sY = (rand.nextInt(2)+3);
                pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
                pY = boundingBox.getTopBound() - 128;
                ob = attackPlayer(sX, sY, pX, pY);
            }else if(option == 1) {
                sX = 0;
                sY = (rand.nextInt(2)+3)*(-1);
                pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
                pY = boundingBox.getBottomBound() + 128;
                ob = attackPlayer(sX, sY, pX, pY);
            }else if(option == 2) {
                sX = (rand.nextInt(2)+3);
                sY = 0;
                pX = boundingBox.getLeftBound() - 128;
                pY = rand.nextInt(boundingBox.getHeight()) + boundingBox.getTopBound();
                ob = attackPlayer(sX, sY, pX, pY);
            }else {
                sX = (rand.nextInt(2)+3)*(-1);
                sY = 0;
                pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
                pY = boundingBox.getRightBound() + 128;
                ob = attackPlayer(sX, sY, pX, pY);
            }
            attackInterval = attackSpeed;
        }
        if (ob != null) {
            ob.update();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        g2.fillRect(boundingBox.getLeftBound() + boundingBox.getWidth()/2-32, boundingBox.getTopBound() - 128, 64, 64);
    }

    public Obstacle attackPlayer(int sX, int sY, int pX, int pY) {
        Obstacle ob = new Obstacle(gp, player, 8, sX, sY, pX, pY, 8, 8);
        return ob;
    }
}
