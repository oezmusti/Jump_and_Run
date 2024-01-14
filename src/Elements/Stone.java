package Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Stone extends Element{
    private String name = "Cactus";
    private Rectangle hitBox;
    public BufferedImage elementImage;
    @Override
    public void importElementImage() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/grauerStein.png");

        try {
            if (stream != null) {
                elementImage = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/grauerStein.png");
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

    public Stone(){
        importElementImage();
        hitBox = new Rectangle(0, 0, 73, 64); // Ändere die Größe entsprechend der tatsächlichen Größe deines Cactus
    }
}
