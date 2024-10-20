import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    JLabel title;

    public static int FPS = 90;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    BoundingBox boundingBox = new BoundingBox(this, 200, 150, 4);
    Player player = new Player(this, keyH, 100, GameWindow.screenWidth/2-8, GameWindow.screenHeight/2-8, 2, boundingBox);
    Enemy enemy = new Enemy(this, player, boundingBox);

    public GamePanel(){
        title = new JLabel();
        title.setText("undeRtale");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            title.setFont(font.deriveFont(Font.PLAIN, 40));
        }catch(Exception e){}
        title.setForeground(Color.WHITE);

        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        this.add(title);

        this.addKeyListener(keyH);
        this.setFocusable(true);


        this.setPreferredSize(new Dimension(GameWindow.screenWidth,GameWindow.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000/FPS;
        double delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.currentTimeMillis();

            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta>=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
                System.out.println(player.health);
            }
        }
    }

    public void update() {
        enemy.update();
        player.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        boundingBox.draw(g2);
        enemy.draw(g2);
        player.draw(g2);
        if (enemy.ob != null) {enemy.ob.draw(g2);}
    }
}
