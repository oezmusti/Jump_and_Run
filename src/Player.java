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
    private int playAct = STAY;
    private Game game;
    private Rectangle hitbox;
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

    public void updateHitboxPosition(int x, int y, int width) {
        // Aktualisiere die Position der Hitbox basierend auf der Position des Spielers
        hitbox.setLocation(x+25, y+25);
        hitbox.setSize(width, 67);
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

    public Rectangle getHitbox() {
        return hitbox;
    }
    public void setPlayAct(int playAct){
        this.playAct = playAct;
    }

    public Player(){
        updateAniTick();
        importPlayerImg();
        animation();
        hitbox = new Rectangle(0, 0, 40, 96); // Ändere die Größe entsprechend der tatsächlichen Größe deines Spielers
    }


}
