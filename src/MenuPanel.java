import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    private JButton startButton, normalButton, infHealthButton, oneHitButton;

    public MenuPanel() {
        setLayout(new GridLayout(4, 1));
        setBackground(Color.BLACK);

        JLabel title = new JLabel("Welcome to undeRtale", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        add(title);

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        add(startButton);

        normalButton = new JButton("Normal Mode");
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNormalMode();
            }
        });
        add(normalButton);

        infHealthButton = new JButton("Infinite Health");
        infHealthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startInfiniteHealthMode();
            }
        });
        add(infHealthButton);

        oneHitButton = new JButton("One Hit Mode");
        oneHitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startOneHitMode();
            }
        });
        add(oneHitButton);
    }

    private void startGame() {
        //GameWindow.changePanel(new GamePanel()); // Switch to the game panel when starting the game
    }

    private void startNormalMode() {
        // Set necessary parameters for normal mode (like FPS)
        GamePanel.FPS = 90; // Example: normal FPS
        startGame();
    }

    private void startInfiniteHealthMode() {
        GamePanel.FPS = 90; // Example: set parameters based on mode
        //GamePanel.infiniteHealthEnabled = true; // Enable infinite health
        startGame();
    }

    private void startOneHitMode() {
        GamePanel.FPS = 90; // Example: set parameters based on mode
        //GamePanel.oneHitEnabled = true; // Enable one-hit mode
        startGame();
    }
}