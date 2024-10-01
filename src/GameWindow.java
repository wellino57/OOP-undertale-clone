import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    ImageIcon heart = new ImageIcon(GameWindow.class.getResource("/images/heart.png"));
    GameWindow(){
        //Title & UI
        JLabel title = new JLabel();
        title.setText("undertale");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            title.setFont(font.deriveFont(Font.PLAIN, 40));
        }catch(Exception e){}
        title.setForeground(Color.WHITE);

        title.setVerticalAlignment(JLabel.CENTER);
        title.setHorizontalAlignment(JLabel.CENTER);

        this.add(title);

        //Setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("undertale");
        this.setSize(1000,750);
        this.setResizable(false);//change later

        this.setIconImage(heart.getImage());

        this.getContentPane().setBackground(Color.BLACK);

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        gamePanel.startGameThread();

        this.setVisible(true);//has to be after ui is created
    }
}
