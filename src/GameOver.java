import Elements.Sting;

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

/**
 * Erstellung der GameOver Klasse, welches vom JPanel erbt und das Interface ActionListener besitzt
 */
public class GameOver extends JPanel implements ActionListener {
    private JButton exitButton;
    private JButton restartButton;
    private BufferedImage backgroundImahe;
    private int score;
    private int highScore;
    private String killObject;

    public GameOver() {
        setFocusable(true);
        importSavedHighScore();
        implementButton();
        importElementImage();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setKillObject(String KO){
        this.killObject = KO;
    }

    /**
     * Import des Hintergrundbildes
     */
    public void importElementImage() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/GameOverImage.png");

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

    /**
     * Importierung des gespeicherten Highscores aus der HighScore.txt
     */
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

    /**
     * Zeichnen aller Elemente für das Spielfenster
     * Zeichnen des Hintergrundbildes
     * prüfen ob Score größer als Highscore ist und gegebenenfalls saveHighScoreToFile(); aufrufen
     *Text anzeige von Score, Highscore und Todesgrund
     * Anzeige der beiden Buttons
     *
     * @param g the <code>Graphics</code> object to protect
     */
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

        g2d.setColor(new Color(0000000));

        String scoreText = "Dein Score: " + score;
        String curenthighScoreText = "Dein aktueller Highscore: " + highScore;
        String killMessage;

        switch (killObject){
            case "Kaktus":
                killMessage = "Du wurdest von einem Kaktus getötet";
                break;
            case "Loch":
                killMessage = "Du bist tief gefallen";
                break;
            case "Stachel":
                killMessage = "Pass auf wo du hintrittst";
                break;
            case "Stein":
                killMessage = "Du bist über ein Stein gestolpert";
                break;
            default:
                killMessage = "Du wurdest auser gefächt gesetzt";
                break;
        }

        String messageText = (score < highScore) ? "Versuch's nächstes Mal!" : "Neuer Highscore: " + highScore;

        // die Schriftgröße auf 30 Pixel für den Text "Game Over"
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        int xGameOver = (getWidth() - g2d.getFontMetrics().stringWidth("Game Over")) / 2;
        g2d.drawString("Game Over", xGameOver, 50);

        // die Schriftgröße auf 20 Pixel für den restlichen Text
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        int xScore = (getWidth() - g2d.getFontMetrics().stringWidth(scoreText)) / 2;
        int xCurentHighScore = (getWidth() - g2d.getFontMetrics().stringWidth(curenthighScoreText)) / 2;
        int xMessage = (getWidth() - g2d.getFontMetrics().stringWidth(messageText)) / 2;
        int xKillMessage = (getWidth() - g2d.getFontMetrics().stringWidth(killMessage)) / 2;

        // Zeichne den restlichen Text
        g2d.drawString(scoreText, xScore, 100);
        g2d.drawString(curenthighScoreText, xCurentHighScore, 130);
        g2d.drawString(killMessage, xKillMessage, 160);
        g2d.drawString(messageText, xMessage, 190);

        // Buttons positionieren
        exitButton.setLocation(120, 250);
        exitButton.setSize(100, 40);
        restartButton.setLocation(320, 250);
        restartButton.setSize(100, 40);
    }

    /**
     * Speichern des neuen Highscores
     */
    private void saveHighScoreToFile() {
        File datei = new File("src/HighScore.txt");
        try {
            java.nio.file.Files.write(datei.toPath(), String.valueOf(highScore).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Erstellung der Buttons
     */
    private void implementButton() {
        exitButton = new JButton("Beenden");
        exitButton.setBounds(120, 560, 200, 80);
        exitButton.addActionListener(this);
        exitButton.setVisible(true);
        add(exitButton);

        restartButton = new JButton("Reset");
        restartButton.setBounds(340, 560, 200, 80);
        restartButton.addActionListener(this);
        restartButton.setVisible(true);
        add(restartButton);
    }

    /**
     * Überprüfung ob ein Button gecklickt worden ist, ausüben des entsprechenden Codeblocks
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            System.exit(0);
        }

        if (event.getSource() == restartButton) {
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame) {
                    window.dispose();
                }
            }

            try {
                String java = System.getProperty("java.home") + "/bin/java";
                ProcessBuilder builder = new ProcessBuilder(java, "-jar", "DeinSpiel.jar");
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
