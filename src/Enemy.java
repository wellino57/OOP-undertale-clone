import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject{

    public int health;
    public Player player;
    public BoundingBox boundingBox;
    int attackSpeed = 20;
    int attackInterval = attackSpeed;

    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Enemy(GamePanel gp, Player player, BoundingBox boundingBox) {
        super(gp);
        this.player = player;
        this.boundingBox = boundingBox;
    }

    @Override
    public void update() {
        if (attackInterval > 0) {
            attackInterval--;
        }else {
            Obstacle ob = attackPlayer();
            obstacles.add(ob);
            attackInterval = attackSpeed;
        }
        if(obstacles.size() > 0) {
            for (Obstacle obstacle : obstacles) {
                obstacle.update();
            }

            while(obstacles.getFirst().dissapearTime <= System.currentTimeMillis()) {
                obstacles.removeFirst();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        g2.fillRect(boundingBox.getLeftBound() + boundingBox.getWidth()/2-32, boundingBox.getTopBound() - 128, 64, 64);
    }

    public Obstacle attackPlayer() {
        Random rand = new Random();
        int sX, sY, pX, pY;
        int option = rand.nextInt(5);
        if(option == 0) {
            sX = 0;
            sY = (rand.nextInt(2)+3);
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getTopBound() - 128;
        }else if(option == 1) {
            sX = 0;
            sY = (rand.nextInt(2)+3)*(-1);
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getBottomBound() + 128;
        }else if(option == 2) {
            sX = (rand.nextInt(2)+3);
            sY = 0;
            pX = boundingBox.getLeftBound() - 128;
            pY = rand.nextInt(boundingBox.getHeight()) + boundingBox.getTopBound();
        }else {
            sX = (rand.nextInt(2)+3)*(-1);
            sY = 0;
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getRightBound() + 128;
        }

        Obstacle ob = new Obstacle(gp, player, 8, sX, sY, pX, pY, 8, 8);
        return ob;
    }
}
