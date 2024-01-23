package Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Kreieren einer Blocs Klasse welches von Element erbt
 */
public class Blocs extends Element{
    private String name = "Cactus";
    private Rectangle hitBox;
    private BufferedImage elementImage;

    /**
     * Importieren des erforderlichen Bildes
     */
    @Override
    public void importElementImage() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/blocBrown.png");

        try {
            if (stream != null) {
                elementImage = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/blocBrown.png");
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
        hitBox.setLocation(x, y);
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

    public Blocs(){
        importElementImage();
        hitBox = new Rectangle(0,0, 896,64);
    }
}
