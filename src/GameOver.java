import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameOver extends JPanel implements ActionListener{
    private JButton menuButton;
    private JButton restartButton;
    private BufferedImage backgroundImahe;
    private int highScore;

    public GameOver() {
        implementButton();
    }

    public void importElementImage() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Cactus.png");

        try {
            if (stream != null) {
                backgroundImahe = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/Cactus-stehend.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImahe, 0, 0, getWidth(), getHeight(), this);

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
