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
    private JButton exitButton;
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

        //HighScore check
        if (score < highScore) {
            System.out.println(1);
        } else {
            highScore = score;
            saveHighScoreToFile();
        }

        g2d.setColor(Color.RED);

        String scoreText = "Dein Score: " + score;
        String curenthighScoreText = "Dein aktueller Highscore: " + highScore;
        String messageText = (score < highScore) ? "Versuch's nächstes Mal!" : "Neuer Highscore: " + highScore;

        // Setze die Schriftgröße auf 30 Pixel für den Text "Game Over"
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        int xGameOver = (getWidth() - g2d.getFontMetrics().stringWidth("Game Over")) / 2;
        g2d.drawString("Game Over", xGameOver, 50);

        // Setze die Schriftgröße auf 20 Pixel für den restlichen Text
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        int xScore = (getWidth() - g2d.getFontMetrics().stringWidth(scoreText)) / 2;
        int xCurentHighScore = (getWidth() - g2d.getFontMetrics().stringWidth(curenthighScoreText)) / 2;
        int xMessage = (getWidth() - g2d.getFontMetrics().stringWidth(messageText)) / 2;

        // Zeichne den restlichen Text
        g2d.drawString(scoreText, xScore, 100);
        g2d.drawString(curenthighScoreText, xCurentHighScore, 150);
        g2d.drawString(messageText, xMessage, 200);

        // Buttons positionieren
        exitButton.setLocation(120, 250);
        restartButton.setLocation(340, 250);
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
        exitButton = new JButton("Beenden");
        exitButton.setBounds(120, 560, 200, 80);  // Adjusted height to avoid overlapping
        exitButton.addActionListener(this);
        exitButton.setVisible(true);
        add(exitButton);

        restartButton = new JButton("Reset");
        restartButton.setBounds(340, 560, 200, 80);  // Adjusted X-coordinate to avoid overlapping
        restartButton.addActionListener(this);
        restartButton.setVisible(true);
        add(restartButton);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            System.exit(0);
        }

        if (event.getSource() == restartButton) {
            // Schließe alle vorhandenen Fenster
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame) {
                    window.dispose();
                }
            }

            // Beende den aktuellen JVM-Prozess und starte einen neuen
            try {
                String java = System.getProperty("java.home") + "/bin/java";
                ProcessBuilder builder = new ProcessBuilder(java, "-jar", "DeinSpiel.jar");
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Öffne das neue Spiel-Fenster
            JFrame game = new JFrame("Jump and Run");
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setSize(977, 540);
            game.setLocationRelativeTo(null); //Positionierung des Fensters in der Mitte
            game.setVisible(true);
            game.setResizable(false);
            game.add(new Game());
        }
    }
}
