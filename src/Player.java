import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player {

    private BufferedImage playerImg;
    public int aniTicker, aniIndex;
    public int aniSpeed = 1000;
    public BufferedImage[][] goForAni;

    //Laufanimationstellen
    public final int STAY = 1;
    public final int RUNNING_FORWARD = 2;
    public final int STAY_BACK = 3;
    public final int RUNNING_BACKWARD = 4;

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

    public int getSpriteAmount(int action){

        switch (action){
            case RUNNING_FORWARD:
            case RUNNING_BACKWARD:
                return 3;
            case STAY:
            case STAY_BACK:
                return 1;
        }
        return action;
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
            if (aniIndex >= goForAni.length){
                aniIndex = 0;
            }
        }
    }

    public BufferedImage getPlayerImg() {
        return playerImg;
    }


    public Player(){
        updateAniTick();
        importPlayerImg();
        animation();
    }
}
