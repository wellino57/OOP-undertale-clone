import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject{

    int health;
    Player player;
    BoundingBox boundingBox;
    int attackSpeed = 45;
    int attackInterval = attackSpeed;
    int attackIndex = 0;
    long changeActionTimer = 0;
    boolean changeAction = false;

    int damagerX;
    int damagerY;
    int healerX;
    int healerY;
    int helperSize = 8;
    int helpAmount = 12;

    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Enemy(GamePanel gp, Player player, BoundingBox boundingBox) {
        super(gp);
        this.player = player;
        this.boundingBox = boundingBox;
        player.setEnemy(this);
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
                            attackSpeed = 35;
                            boundingBox.setTargetWidth(200);
                            boundingBox.setTargetHeight(150);
                            break;
                    case 1: volley();
                            attackSpeed = 100;
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

                healerX = r.nextInt(boundingBox.getTargetWidth()-10)+5+boundingBox.getLeftBound();
                healerY = r.nextInt(boundingBox.getTargetHeight()-10)+5+boundingBox.getTopBound();

                damagerX = r.nextInt(boundingBox.getTargetWidth()-10)+5+boundingBox.getLeftBound();
                damagerY = r.nextInt(boundingBox.getTargetHeight()-10)+5+boundingBox.getTopBound();

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
        g2.fillRect( healerX, healerY, helperSize, helperSize);
        g2.setColor(Color.red);
        g2.fillRect( damagerX, damagerY, helperSize, helperSize);
    }

    void randomBullets() {
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

    void volley() {
        Random rand = new Random();
        int pX, pY;
        int option = rand.nextInt(4);

        if(option == 0) {
            pY = boundingBox.getTopBound() - 64;
            for(int i=0;i<boundingBox.getWidth();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 8, 3,boundingBox.getLeftBound()+i, pY, 8, 8);
                obstacles.add(ob);
            }
        }else if(option == 1) {
            pY = boundingBox.getBottomBound() + 64;
            for(int i=0;i<boundingBox.getWidth();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 8, 3,boundingBox.getLeftBound()+i, pY, 8, 8);
                obstacles.add(ob);
            }
        }else if(option == 2) {
            pX = boundingBox.getLeftBound() - 64;
            for(int i=0;i<boundingBox.getHeight();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 8, 3, pX,boundingBox.getTopBound()+i, 8, 8);
                obstacles.add(ob);
            }
        }else {
            pX = boundingBox.getRightBound() + 64;
            for(int i=0;i<boundingBox.getHeight();i+=16) {
                Obstacle ob = new Obstacle(gp, player, 16, 3, pX,boundingBox.getTopBound()+i, 8, 8);
                obstacles.add(ob);
            }
        }
    }

    public int getDamagerX() {
        return damagerX;
    }

    public int getDamagerY() {
        return damagerY;
    }

    public int getHealerX() {
        return healerX;
    }

    public int getHealerY() {
        return healerY;
    }

    public int getHelperSize() {
        return helperSize;
    }

    public int getHelpAmount() {
        return helpAmount;
    }

    public void setDamagerX(int x) {
        this.damagerX = x;
    }

    public void setDamagerY(int y) {
        this.damagerY = y;
    }

    public void setHealerX(int x) {
        this.healerX = x;
    }

    public void setHealerY(int y) {
        this.healerY = y;
    }
}
