import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    JLabel title;

    Thread gameThread;

    public GamePanel(){
        title = new JLabel();
        title.setText("andRewtate");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            title.setFont(font.deriveFont(Font.PLAIN, 40));
        }catch(Exception e){}
        title.setForeground(Color.WHITE);

        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        this.add(title);


        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){
            update();
            repaint();
        }
    }

    public void update() {

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(500, 500, 24, 24);
    }
}
