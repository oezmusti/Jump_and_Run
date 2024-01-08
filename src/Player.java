import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static util.PlaayerConst.PlayerMovings.*;

public class Player {

    private BufferedImage playerImg;
    public int aniTicker, aniIndex;
    public int aniSpeed = 1000;
    public BufferedImage[][] goForAni;
    private Rectangle hitBox;
    private int playAct = STAY;
    public void importPlayerImg() {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Character.png");

        try {
            if (stream != null) {
                playerImg = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/Charakter-stehend.png");
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

    public void initHitBox(){
        hitBox = new Rectangle();
    }

    public void animation(){
        goForAni = new BufferedImage[4][4];

        for(int j = 0; j < goForAni.length; j++){
            for(int start = 0; start < goForAni[j].length; start++ ){
                goForAni[j][start] = playerImg.getSubimage(start*32, j*32, 32,32);
            }
        }
    }

    public void updateAniTick(){
        aniTicker++;
        if(aniTicker >= aniSpeed){
            aniTicker = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playAct)){
                aniIndex = 0;
            }
        }
    }

    @Deprecated
    public BufferedImage getPlayerImg() {
        return playerImg;
    }

    public void setPlayAct(int playAct){
        this.playAct = playAct;
    }

    public Player(){
        updateAniTick();
        importPlayerImg();
        animation();
    }

}
