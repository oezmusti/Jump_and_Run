import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player {

    private BufferedImage playerImg;
    public void importPlayerImg() {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Character.png");
            if (stream != null) {
                playerImg = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/Charakter-stehend.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public Player(){

    }
}
