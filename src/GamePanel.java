import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    GameWindow gameWindow;
    public static float damageMult;
    boolean spawnHealth;
    boolean gameLost = false;
    boolean gameWon = false;
    long loseTimer = Long.MAX_VALUE;
    long winTimer = Long.MAX_VALUE;

    public static int FPS = 90;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    BoundingBox boundingBox = new BoundingBox(this, 200, 150, 4);
    Player player = new Player(this, keyH, 100, GameWindow.screenWidth/2-8, GameWindow.screenHeight/2-8, 2, boundingBox, null);
    Enemy enemy = new Enemy(this, 150, player, boundingBox);

    public GamePanel(GameWindow gameWindow){
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.setPreferredSize(new Dimension(GameWindow.screenWidth,GameWindow.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.gameWindow = gameWindow;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        this.requestFocusInWindow();
    }

    public void stopGameThread() {
        if (gameThread != null) {
            Thread temp = gameThread;
            gameThread = null; // Signal the thread to stop
            try {
                temp.join(1000); // Ensure the thread is fully terminated
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000 / FPS;
        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            if(gameLost && loseTimer <= System.currentTimeMillis()) {
                gameWindow.backToMenu();
            }

            if(gameWon && winTimer <= System.currentTimeMillis()) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gameWindow.backToMenu();
            }

            currentTime = System.currentTimeMillis();

            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
                System.out.println(player.health);
            }
        }
    }

    public void update() {
        if(!gameLost) {
            player.update();
            if(!gameWon) {
                enemy.update();
                boundingBox.update();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(!gameLost) {
            boundingBox.draw(g2);
            enemy.draw(g2);
            if (enemy.obstacles.size() > 0 && !gameWon) {
                for (Obstacle ob : enemy.obstacles) {
                    ob.draw(g2);
                }
            }
        }
        player.draw(g2);
    }

    void setDamageMult(float mult) {
        damageMult = mult;
    }

    float getDamageMult() {
        return damageMult;
    }

    void setSpawnHealth(boolean set) {
        spawnHealth = set;
    }

    boolean getSpawnHealth() {
        return spawnHealth;
    }

    void setGameLost(boolean set) {
        gameLost = set;
    }

    void setGameWon(boolean set) {
        gameWon = set;
    }
}
