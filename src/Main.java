import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Storm Runner
 *
 * @author Jana Retterath & Mustafa Öz
 * @version 1.0
 */

public class Main extends JFrame {

   /**
     * Ausführung des Programmes
     *
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        JFrame startframe = new JFrame("Menü");
        startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startframe.setSize(400, 540);
        startframe.setLocationRelativeTo(null);
        startframe.setResizable(false);

        Frame frame = new Frame();
        //gameOverPanel.importElementImage();

        // Icon-Bild laden
        try {
            BufferedImage iconImage = ImageIO.read(Main.class.getResource("assets/Icon.png"));
            // Setze das Icon für das JFrame
            startframe.setIconImage(iconImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startframe.add(frame);
        startframe.setVisible(true);
    }

}