import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject{

    public int health;
    public Player player;
    public BoundingBox boundingBox;
    int attackSpeed = 45;
    int attackInterval = attackSpeed;
    int attackIndex = 0;
    long changeActionTimer = 0;
    boolean changeAction = false;

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
            Obstacle ob;
            if(!changeAction) {
                switch (attackIndex) {
                    case 0: randomBullets();
                            attackSpeed = 45;
                            boundingBox.setTargetWidth(200);
                            boundingBox.setTargetHeight(150);
                            break;
                    case 1: volley();
                            attackSpeed = 90;
                            boundingBox.setTargetWidth(175);
                            boundingBox.setTargetHeight(175);
                            break;
                    default: randomBullets();
                }

                attackInterval = attackSpeed;

                if(System.currentTimeMillis() > changeActionTimer) {
                    changeAction = true;
                }
            } else {
                Random r = new Random();
                attackIndex = r.nextInt(2);

                changeActionTimer = System.currentTimeMillis()+10000;
                changeAction = false;
            }
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

    public void randomBullets() {
        Random rand = new Random();
        int sX, sY, pX, pY;
        int option = rand.nextInt(5);
        if(option == 0) {
            sX = 0;
            sY = 4;
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getTopBound() - 128;
        }else if(option == 1) {
            sX = 0;
            sY = -4;
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getBottomBound() + 128;
        }else if(option == 2) {
            sX = 4;
            sY = 0;
            pX = boundingBox.getLeftBound() - 128;
            pY = rand.nextInt(boundingBox.getHeight()) + boundingBox.getTopBound();
        }else {
            sX = -4;
            sY = 0;
            pX = boundingBox.getRightBound() + 128;
            pY = rand.nextInt(boundingBox.getHeight()) + boundingBox.getTopBound();
        }

        Obstacle ob = new Obstacle(gp, player, 8, sX, sY, pX, pY, 8, 8);
        obstacles.add(ob);
    }

    public void volley() {
        Random rand = new Random();
        int pX, pY;
        int option = rand.nextInt(4);

        if(option == 0) {
            pY = boundingBox.getTopBound() - 32;
            for(int i=0;i<boundingBox.getWidth();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 8, 3,boundingBox.getLeftBound()+i, pY, 8, 8);
                obstacles.add(ob);
            }
        }else if(option == 1) {
            pY = boundingBox.getBottomBound() + 32;
            for(int i=0;i<boundingBox.getWidth();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 8, 3,boundingBox.getLeftBound()+i, pY, 8, 8);
                obstacles.add(ob);
            }
        }else if(option == 2) {
            pX = boundingBox.getLeftBound() - 32;
            for(int i=0;i<boundingBox.getHeight();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 8, 3, pX,boundingBox.getTopBound()+i, 8, 8);
                obstacles.add(ob);
            }
        }else {
            pX = boundingBox.getRightBound() + 32;
            for(int i=0;i<boundingBox.getHeight();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 16, 3, pX,boundingBox.getTopBound()+i, 8, 8);
                obstacles.add(ob);
            }
        }
    }
}
