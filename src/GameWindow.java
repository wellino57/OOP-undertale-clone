import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static int screenWidth = 1000;
    public static int screenHeight = 750;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel gamePanel;

    ImageIcon heart = new ImageIcon(GameWindow.class.getResource("/images/heart-icon.png"));

    GameWindow(){
        //Setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("undertale");
        this.setSize(screenWidth,screenHeight);
        this.setResizable(false);//change later

        this.setIconImage(heart.getImage());

        this.getContentPane().setBackground(Color.BLACK);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        MenuPanel menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel();

        mainPanel.add(menuPanel, "MenuPanel");
        mainPanel.add(gamePanel, "GamePanel");

        this.add(mainPanel);

        this.setVisible(true);//has to be after ui is created
    }

    public void startGame(float mult, boolean heal) {
        cardLayout.show(mainPanel, "GamePanel");
        gamePanel.startGameThread();
        gamePanel.setDamageMult(mult);
        gamePanel.setSpawnHealth(heal);
    }
}
