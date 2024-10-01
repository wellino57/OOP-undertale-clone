import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;

    public GamePanel(){
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
    }
}
