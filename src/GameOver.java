import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {
    private JButton menuButton;
    private JButton restartButton;
    private int highScore;
    private String deathAlert;

    public GameOver() {
        // Set layout manager for the panel
        setLayout(new BorderLayout());

        // Create buttons
        menuButton = new JButton("Main Menu");
        restartButton = new JButton("Restart");

        // Add action listeners to the buttons
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle menu button click (you can add your logic here)
                System.out.println("Main Menu Button Clicked");
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle restart button click (you can add your logic here)
                System.out.println("Restart Button Clicked");
            }
        });

        // Create a panel for buttons and add buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(menuButton);
        buttonPanel.add(restartButton);

        // Add components to the main panel
        add(buttonPanel, BorderLayout.SOUTH);

        // Set other properties as needed
        highScore = 1000; // Set your high score value
        deathAlert = "Game Over! Your High Score: " + highScore; // Set your death alert message with high score

        // Display death alert message using JOptionPane

    }

    public void DeathAlertMessage() {
        // You can use this method to display the death alert message if needed
        JOptionPane.showMessageDialog(this, deathAlert, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

}
