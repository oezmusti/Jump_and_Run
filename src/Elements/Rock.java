package Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static util.PlaayerConst.PlayerMovings.GetSpriteAmount;

public class Rock extends Element{

    private String name = "Cactus";
    private BufferedImage elementImage;
    public BufferedImage[] goForAni;
    public int aniTicker, aniIndex;
    public int aniSpeed = 1000;

    @Override
    public void importElementImage() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Stein-animation.png");

        try {
            if (stream != null) {
                elementImage = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/Stein-animation.png");
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

    public void animation(){
        goForAni = new BufferedImage[3];

        for(int start = 0; start < goForAni.length; start++ ){
            goForAni[start] = elementImage.getSubimage(start*32, 32, 32,32);
        }
    }

    public void updateAniTick(){
        aniTicker++;
        if(aniTicker >= aniSpeed){
            aniTicker = 0;
            aniIndex++;
            if (aniIndex >= 1){
                aniIndex = 0;
            }
        }
    }
    public String getName(){
        return name;
    }

    public BufferedImage getElementImage(){
        return elementImage;
    }

    public Rock(){
        updateAniTick();
        importElementImage();
    }
}
