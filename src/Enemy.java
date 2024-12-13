import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject{

    int maxHealth, health, prevHealth;
    Player player;
    BoundingBox boundingBox;
    BufferedImage damagerSprite;
    BufferedImage healerSprite;
    BufferedImage bossSprite;
    int attackSpeed = 45;
    int attackInterval = attackSpeed;
    int attackIndex = 0;
    long changeActionTimer = 0;
    boolean changeAction = false;

    int damagerX;
    int damagerY;
    int healerX;
    int healerY;
    int helperSize = 16;
    int helpAmount = 12;

    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public Enemy(GamePanel gp, int health, Player player, BoundingBox boundingBox) {
        super(gp);
        this.maxHealth = health;
        this.health = maxHealth;
        this.prevHealth = this.health;
        this.player = player;
        this.boundingBox = boundingBox;
        player.setEnemy(this);

        getDamagerSprite();
        getHealerSprite();
        getBossSprite();
    }

    @Override
    public void update() {

        healthCheck();
        isAlive();

        if (attackInterval > 0) {
            attackInterval--;
        }else {
            Obstacle ob;
            if(!changeAction) {
                switch (attackIndex) {
                    case 0: randomBullets();
                        break;
                    case 1: volley();
                        break;
                    case 2: circles();
                        break;
                    case 3: burst();
                        break;
                    default:
                        System.out.println("MANGO MANGO MANGO");
                }

                attackInterval = attackSpeed;

                if(System.currentTimeMillis() > changeActionTimer) {
                    changeAction = true;
                }
            } else {
                Random r = new Random();
                int candidate = r.nextInt(4);
                if (candidate != attackIndex) {
                    obstacles.clear();
                }
                attackIndex = candidate;

                switch (attackIndex) {
                    case 0:
                        attackSpeed = 15;
                        boundingBox.setTargetWidth(300);
                        boundingBox.setTargetHeight(225);
                        break;
                    case 1:
                        attackSpeed = 90;
                        boundingBox.setTargetWidth(175);
                        boundingBox.setTargetHeight(175);
                        break;
                    case 2:
                        attackSpeed = 45;
                        boundingBox.setTargetWidth(325);
                        boundingBox.setTargetHeight(275);
                        break;
                    case 3:
                        attackSpeed = 45;
                        boundingBox.setTargetWidth(300);
                        boundingBox.setTargetHeight(200);
                        break;
                    default:
                        attackSpeed = 1000;
                        boundingBox.setTargetWidth(1000);
                        boundingBox.setTargetHeight(1000);
                }

                if(gp.spawnHealth) {
                    healerX = r.nextInt(boundingBox.getTargetWidth()-16)+boundingBox.getTargetLeft();
                    healerY = r.nextInt(boundingBox.getTargetHeight()-16)+boundingBox.getTargetTop();
                } else {
                    healerX = 1000000;
                    healerY = healerX;
                }

                damagerX = r.nextInt(boundingBox.getTargetWidth()-16)+boundingBox.getTargetLeft();
                damagerY = r.nextInt(boundingBox.getTargetHeight()-16)+boundingBox.getTargetTop();

                changeActionTimer = System.currentTimeMillis()+7500;
                changeAction = false;
            }
        }
        if(!obstacles.isEmpty()) {
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
        BufferedImage image1 = damagerSprite;
        BufferedImage image2 = healerSprite;
        BufferedImage image3 = bossSprite;

        if(!gp.gameWon) {
            g2.setColor(Color.white);
            g2.fillRect(boundingBox.getLeftBound() + boundingBox.getWidth()/2-maxHealth, boundingBox.getTopBound() - 160, maxHealth*2, 24);
            g2.setColor(Color.black);
            g2.fillRect(boundingBox.getLeftBound() + boundingBox.getWidth()/2-maxHealth+2, boundingBox.getTopBound() - 158, maxHealth*2-4, 20);
            g2.setColor(Color.red);
            g2.fillRect(boundingBox.getLeftBound() + boundingBox.getWidth()/2-maxHealth+4, boundingBox.getTopBound() - 156, prevHealth*2-8, 16);
            g2.setColor(Color.white);
            g2.fillRect(boundingBox.getLeftBound() + boundingBox.getWidth()/2-maxHealth+4, boundingBox.getTopBound() - 156, health*2-8, 16);
        }

        g2.drawImage(image3, boundingBox.getLeftBound() + boundingBox.getWidth()/2-32, boundingBox.getTopBound() - 128, 64, 64, null);

        g2.setColor(Color.black);
        g2.fillRect( healerX, healerY, helperSize, helperSize);
        g2.setColor(Color.black);
        g2.fillRect( damagerX, damagerY, helperSize, helperSize);
        g2.drawImage(image1, damagerX, damagerY, helperSize, helperSize, null);
        g2.drawImage(image2, healerX, healerY, helperSize, helperSize, null);
    }
    public void getDamagerSprite() {
        try {
            damagerSprite = ImageIO.read(getClass().getResourceAsStream("/images/Damager.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void getHealerSprite() {
        try {
            healerSprite = ImageIO.read(getClass().getResourceAsStream("/images/Healer.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void getBossSprite() {
        try {
            bossSprite = ImageIO.read(getClass().getResourceAsStream("/images/Boss.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
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

        Obstacle ob = new Obstacle(10000,gp, player, 8, sX, sY, pX, pY, 8, 8);
        obstacles.add(ob);
    }

    void volley() {
        Random rand = new Random();
        int pX, pY;
        int option = rand.nextInt(4);

        if(option == 0) {
            pY = boundingBox.getTopBound() - 64;
            for(int i=0;i<boundingBox.getWidth();i+=16) {
                Obstacle ob = new Obstacle(10000,gp, player, 8, 3,boundingBox.getLeftBound()+i, pY, 8, 8);
                obstacles.add(ob);
            }
        }else if(option == 1) {
            pY = boundingBox.getBottomBound() + 64;
            for(int i=0;i<boundingBox.getWidth();i+=16) {
                Obstacle ob = new Obstacle(10000,gp, player, 8, 3,boundingBox.getLeftBound()+i, pY, 8, 8);
                obstacles.add(ob);
            }
        }else if(option == 2) {
            pX = boundingBox.getLeftBound() - 64;
            for(int i=0;i<boundingBox.getHeight();i+=16) {
                Obstacle ob = new Obstacle(10000,gp, player, 8, 3, pX,boundingBox.getTopBound()+i, 8, 8);
                obstacles.add(ob);
            }
        }else {
            pX = boundingBox.getRightBound() + 64;
            for(int i=0;i<boundingBox.getHeight();i+=16) {
                Obstacle ob = new Obstacle(10000,gp, player, 8, 3, pX,boundingBox.getTopBound()+i, 8, 8);
                obstacles.add(ob);
            }
        }
    }

    void circles() {
        Obstacle ob;
        int speed = 3;
        int distance = 256;
        int spain = 1000;
        ob = new Obstacle(spain,gp, player, 10, speed, player.getX() - distance - distance/8, player.getY(), 8, 8);
        obstacles.add(ob);
        ob = new Obstacle(spain,gp, player, 10, speed, player.getX() + distance + distance/8, player.getY(), 8, 8);
        obstacles.add(ob);
        ob = new Obstacle(spain,gp, player, 10, speed, player.getX() - distance/2, player.getY() + distance, 8, 8);
        obstacles.add(ob);
        ob = new Obstacle(spain,gp, player, 10, speed, player.getX() + distance/2, player.getY() + distance, 8, 8);
        obstacles.add(ob);
        ob = new Obstacle(spain,gp, player, 10, speed, player.getX() - distance/2, player.getY() - distance, 8, 8);
        obstacles.add(ob);
        ob = new Obstacle(spain,gp, player, 10, speed, player.getX() + distance/2, player.getY() - distance, 8, 8);
        obstacles.add(ob);
    }

    void burst() {
        Random rand = new Random();
        int pX, pY;
        int option = rand.nextInt(4);
        Obstacle ob;
        if(option == 0) {
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getTopBound() - 128;
        }else if(option == 1) {
            pX = rand.nextInt(boundingBox.getWidth()) + boundingBox.getLeftBound();
            pY = boundingBox.getBottomBound() + 128;
        }else if(option == 2) {
            pX = boundingBox.getLeftBound() - 128;
            pY = rand.nextInt(boundingBox.getHeight()) + boundingBox.getTopBound();
        }else {
            pX = boundingBox.getRightBound() + 128;
            pY = rand.nextInt(boundingBox.getHeight()) + boundingBox.getTopBound();
        }

        for(int i=2;i<5;i++) {
            ob = new Obstacle(10000,gp, player, 8, i, pX, pY, 8, 8);
            obstacles.add(ob);
        }
    }

    public void isAlive() {
        if (health <= 0) {
            gp.setGameWon(true);
            gp.winTimer = System.currentTimeMillis() + 15000;

            try {
                bossSprite = ImageIO.read(getClass().getResourceAsStream("/images/Boss-dead.png"));
            }catch(IOException e) {
                e.printStackTrace();
            }

            try {
                Sound.stopSound();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

            try {
                Sound.playSound("victory.wav");
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void healthCheck() {
        if (prevHealth > health) {
            prevHealth--;
            try {
                bossSprite = ImageIO.read(getClass().getResourceAsStream("/images/Boss-damaged.png"));
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                bossSprite = ImageIO.read(getClass().getResourceAsStream("/images/Boss.png"));
            }catch(IOException e) {
                e.printStackTrace();
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
