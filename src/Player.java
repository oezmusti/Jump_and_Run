import javax.swing.*;
import java.awt.*;

public class Player {
    public Image playerImg;

    public void setPlayerImg(){
        ImageIcon player = new ImageIcon("src\\assets\\Charakter-stehend.png");
        playerImg = player.getImage();
    }
    public Player(){

    }
}
