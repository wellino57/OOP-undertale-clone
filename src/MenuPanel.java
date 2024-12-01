import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public MenuPanel(GameWindow gameWindow) {
        this.setBackground(Color.BLACK);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel under = new JLabel();
        under.setText("   undeRtale*");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            under.setFont(font.deriveFont(Font.PLAIN, 48));
        }catch(Exception e){}
        under.setForeground(Color.RED);

        JLabel title = new JLabel();
        title.setText("undeRtale");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            title.setFont(font.deriveFont(Font.PLAIN, 48));
            title.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Bartek Urban, Kostek Luczak", TitledBorder.TRAILING, TitledBorder.BOTTOM, font.deriveFont(Font.PLAIN, 8)));
        }catch(Exception e){}
        title.setForeground(Color.WHITE);

        JButton easy = new JButton("Easy Mode");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            easy.setFont(font.deriveFont(Font.PLAIN, 16));
        }catch(Exception e){}
        easy.setForeground(Color.WHITE);
        easy.setBackground(Color.BLACK);
        easy.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.white));
        easy.setFocusPainted(false);

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startGame(0.5f, true);
            }
        });

        JButton normal = new JButton("Normal Mode");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            normal.setFont(font.deriveFont(Font.PLAIN, 16));
        }catch(Exception e){}
        normal.setForeground(Color.WHITE);
        normal.setBackground(Color.BLACK);
        normal.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.white));
        normal.setFocusPainted(false);

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startGame(1f, true);
            }
        });

        JButton oneHit = new JButton("One Hit");
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, GameWindow.class.getResourceAsStream("fonts/MonsterFriendFore.otf"));
            oneHit.setFont(font.deriveFont(Font.PLAIN, 16));
        }catch(Exception e){}
        oneHit.setForeground(Color.WHITE);
        oneHit.setBackground(Color.BLACK);
        oneHit.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.white));
        oneHit.setFocusPainted(false);

        oneHit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startGame(1000f, false);
            }
        });

        c.insets = new Insets(4, 16, 4, 16);
        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);
        this.add(under, c);
        c.gridy = 1;
        c.ipadx = 16;
        c.ipady = 16;
        this.add(easy, c);
        c.gridy = 2;
        this.add(normal, c);
        c.gridy = 3;
        this.add(oneHit, c);
    }
}