package Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Kreieren einer Cactus Klasse welches von Element erbt
 */
public class Cactus extends Element{

    private String name = "Kaktus";
    private Rectangle hitBox;
    private BufferedImage elementImage;

    /**
     * Importieren des erforderlichen Bildes
     */
    @Override
    public void importElementImage() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Cactus.png");

        try {
            if (stream != null) {
                elementImage = ImageIO.read(stream);
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

    /**
     * Updaten der Hitbox für jedes Objekt
     *
     * @param x Übergabe der X-Koordinate
     * @param y Üvergabe der Y-Koordinate
     * */
    @Override
    public void updateHitboxPosition(int x, int y) {
        // Aktualisiere die Position der Hitbox basierend auf der Position des Spielers
        this.hitBox.setLocation(x, y);
    }

    public String getName(){
        return name;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }
    public BufferedImage getElementImage(){
        return elementImage;
    }

    public Cactus(){
        importElementImage();
        this.hitBox = new Rectangle(0, 0, 64, 64); // Ändere die Größe entsprechend der tatsächlichen Größe deines Cactus
    }
}
