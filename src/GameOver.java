import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameOver extends JPanel implements ActionListener {
    private JButton menuButton;
    private JButton restartButton;
    private BufferedImage backgroundImahe;
    private int score;
    private int highScore = 40;

    public GameOver() {
        setFocusable(true);
        implementButton();
        //importElementImage();
    }

    public void setScore(int score){
        this.score = score;
        System.out.println("Dein Übertragener Score hier:" + " " + this.score);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Zeichne das Hintergrundbild
        g2d.drawImage(backgroundImahe, 0, 0, getWidth(), getHeight(), this);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2d.setColor(Color.RED);
        g2d.drawString("Game Over", 10, 10);
        System.out.println(score);
        g2d.drawString("Dein Score:" + score, 10, 70);

        repaint();
    }

    private void implementButton() {
        menuButton = new JButton("Menü");
        menuButton.setBounds(120, 560, 200, 80);  // Adjusted height to avoid overlapping
        menuButton.addActionListener(this);
        menuButton.setVisible(true);
        add(menuButton);

        restartButton = new JButton("Reset");
        restartButton.setBounds(340, 560, 200, 80);  // Adjusted X-coordinate to avoid overlapping
        restartButton.addActionListener(this);
        restartButton.setVisible(true);
        add(restartButton);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == menuButton) {

        }

        if (event.getSource() == restartButton) {

        }
    }
}
