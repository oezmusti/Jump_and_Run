import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class GameOver extends JPanel implements ActionListener {
    private JButton menuButton;
    private JButton restartButton;
    private BufferedImage backgroundImahe;
    private int score;
    private int highScore;

    public GameOver() {
        setFocusable(true);
        importSavedHighScore();
        implementButton();
        //importElementImage();
    }

    public void setScore(int score) {
        this.score = score;
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

    public void importSavedHighScore(){
        File datei = new File("src/HighScore.txt");
        System.out.println(datei.isFile());
        Scanner scanner = null;
        try {
            scanner = new Scanner(datei);
            if (scanner.hasNextInt()) {
                highScore = scanner.nextInt();
                System.out.println("Highscore geladen: " + highScore);
            }
        } catch (FileNotFoundException e){
            System.out.println("Nicht gefunden");
        } finally {
            if (scanner != null) {
                scanner.close();
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
        g2d.drawString("Dein Score:" + score, 10, 70);

        // Überprüfen, ob der aktuelle Score den Highscore übertrifft
        if (score < highScore) {
            g2d.drawString("Versuch's nächstes Mal!", 10, 130);
        } else {
            highScore = score;
            g2d.drawString("Neuer Highscore: " + highScore, 10, 130);
            System.out.println("Neuer Highscore: " + highScore);
            // Hier können Sie die Methode aufrufen, um den Highscore in der Datei zu speichern
            saveHighScoreToFile();
        }
    }

    private void saveHighScoreToFile() {
        File datei = new File("src/HighScore.txt");
        try {
            java.nio.file.Files.write(datei.toPath(), String.valueOf(highScore).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
