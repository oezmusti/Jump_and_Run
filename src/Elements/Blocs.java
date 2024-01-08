package Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Blocs extends Element{
    private String name = "Cactus";
    public BufferedImage elementImage;
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

    public String getName(){
        return name;
    }

    public BufferedImage getElementImage(){
        return elementImage;
    }

    public Blocs(){
        importElementImage();
    }
}
