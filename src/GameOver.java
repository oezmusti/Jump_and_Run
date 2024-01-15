import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel implements ActionListener{
    private JButton menuButton;
    private JButton restartButton;
    private int highScore;

    public GameOver() {
        implementButton();
    }

    private void implementButton() {
        menuButton = new JButton("Menü");
        menuButton.setBounds(120, 170, 160, 40);
        menuButton.addActionListener(this);
        menuButton.setVisible(true);
        add(menuButton);

        restartButton = new JButton("Reset");
        restartButton.setBounds(120, 170, 160, 40);
        restartButton.addActionListener(this);
        restartButton.setVisible(true);
        add(restartButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == menuButton){

        }

        if (event.getSource() == restartButton){

        }
    }
}
