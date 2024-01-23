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
    private Rectangle hitbox;

    /**
     * Importieren des erforderlichen Bildes
     */
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

    /**
     * Updaten der Hitbox des Spielers beim bewegen
     *
     * @param x
     * @param y
     * @param width
     */
    public void updateHitboxPosition(int x, int y, int width) {
        hitbox.setLocation(x+25, y+25);
        hitbox.setSize(width, 67);
    }

    /**
     * Ausführung der Animation, iteration
     */
    public void animation(){
        goForAni = new BufferedImage[4][4];

        for(int j = 0; j < goForAni.length; j++){
            for(int start = 0; start < goForAni[j].length; start++ ){
                goForAni[j][start] = playerImg.getSubimage(start*32, j*32, 32,32);
            }
        }
    }

    /**
     * Dauerausführung der Animation für den Spieler
     */
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
