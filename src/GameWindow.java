import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

        try {
            Sound.loopSound("menu.wav");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void startGame(float mult, boolean heal) {
        cardLayout.show(mainPanel, "GamePanel");
        gamePanel.startGameThread();
        gamePanel.setDamageMult(mult);
        gamePanel.setSpawnHealth(heal);

        try {
            Sound.loopSound("fight.wav");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
