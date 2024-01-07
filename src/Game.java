import Elements.Cactus;
import Elements.Rock;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static util.PlaayerConst.PlayerMovings.*;

public class Game extends JPanel implements ActionListener {

    //Variablen
    private Image img;
    //private Image playerImg;
    private int left;
    private int player_Y = 371;
    private int frames = 0;
    private long lastCheck = 0;

    private Timer time;
    private int xImg;
    private int move;
    private int nx, nx2;
    private int anzahl = 0;
    private int anzahl2 = 0;
    private JButton resetButton;


    //Assosiations
    private Movement movement;;
    private Jump jump;
    private Player player;
    private Rock rock;
    private Cactus cactus;
    public int playAct = STAY;

    public Game() {
        move = 0;
        nx = 0;
        nx2 = 960;

        initClass();
        setFocusable(true);
        importBackgroundImg();
        rock.importElementImage();
        player.importPlayerImg();
        addKeyListener(movement);
        timerRestart();

        //ResetButton
        resetButton = new JButton("Reset");
        resetButton.setBounds(600, 0, 80, 30);
        resetButton.addActionListener(this);
        add(resetButton);
    }

    private void initClass() {
        movement = new Movement(this);
        jump = new Jump();
        player = new Player();
        rock = new Rock();
        cactus = new Cactus();
    }

    private void importBackgroundImg() {
        ImageIcon image = new ImageIcon("src\\assets\\Hintergrund.png");
        img = image.getImage();
        setPreferredSize(new Dimension(800, 600));
    }

    private void backgroundLoop() {
        if(xImg >= 960 + (anzahl * 1900)) {
            anzahl += 1;
            nx = 0;
        }
        if(xImg >= 1920 + (anzahl2 * 1900)) {
            anzahl2 += 1;
            nx2 = 0;
        }
    }

    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D g2d = (Graphics2D) graphic;
        backgroundLoop();

        // Zeichne den Hintergrund
        g2d.drawImage(img, 960 - nx, 0, getWidth(), getHeight(), this);
        g2d.drawImage(img, 960 - nx2, 0, getWidth(), getHeight(), this);

        // Zeichne den Spieler
        player.updateAniTick();

        g2d.drawImage(player.goForAni[playAct][player.aniIndex], left, player_Y, 96, 96, null);
        g2d.drawImage(cactus.elementImage, 0-nx, 400, 64, 64, null);
        g2d.drawImage(cactus.elementImage, 960-nx, 400, 64, 64, null);


        fpsCounter();
        repaint();
    }

    private void timerRestart() {
        time = new Timer(15, this);
        time.start();
    }

    public void actionPerformed(ActionEvent event) {
        player.setPlayAct(playAct);
        player_Y = jump.jumpPosition;
        baclgroundLimit();
        if(event.getSource() == resetButton){
            resetGame();
        }
    }

    public void baclgroundLimit() {
        //Damit der Spieler nicht wieder endlos nach Hinten laufen kann
        if(movement.move != -5){
            if(left + movement.move <= 400){
                left += movement.move;
            }
            else{
                xImg += movement.move;
                nx += movement.move;
                nx2 += movement.move;
            }
        }
        else{
            if(left + movement.move > 0){
                left += movement.move;;
            }
        }
    }

    private void fpsCounter() {
        frames++;
        if(System.currentTimeMillis() - lastCheck >= 1000){
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS:" + frames);
            frames = 0;
        }
    }

    //Bleibt fürs erste
    public void setPlayAct(int playAct){
        this.playAct = playAct;
    }

    //Funktion die den Reset ausführen soll
    private void resetGame() {
        // Reset game logic goes here
        Frame.game();
    }
}
