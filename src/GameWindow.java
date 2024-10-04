import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static int screenWidth = 1000;
    public static int screenHeight = 750;

    ImageIcon heart = new ImageIcon(GameWindow.class.getResource("/images/heart-icon.png"));
    GameWindow(){
        //Setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("undertale");
        this.setSize(screenWidth,screenHeight);
        this.setResizable(false);//change later

        this.setIconImage(heart.getImage());

        this.getContentPane().setBackground(Color.BLACK);

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        gamePanel.startGameThread();

        this.setVisible(true);//has to be after ui is created
    }
}
