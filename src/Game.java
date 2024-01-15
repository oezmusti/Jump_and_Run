import Elements.Blocs;
import Elements.Cactus;
import Elements.Rock;
import Elements.Stone;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.swing.*;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static util.PlaayerConst.PlayerMovings.*;

public class Game extends JPanel implements ActionListener {

    //Variablen
    private Image img;
    //private Image playerImg;
    private int left;
    private int player_Y = 348;
    private int frames = 0;
    private long lastCheck = 0;
    private int width;
    private int curentScore = 0;

    private Timer time;
    private int xImg;
    private int move;
    private int nx, nx2;
    public int nx3 = 0;
    private int anzahl = 0;
    private int anzahl2 = 0;
    private JButton resetButton;

    //Randomizer
    Random rand = new Random();
    private int maxRand = 301;
    private int minRand = 140;
    private int random = rand.nextInt(maxRand) + minRand;
    private int enemyStartCactus = 400;

    //Assosiations
    private Movement movement;;
    private Jump jump;
    private Player player;
    private Rock rock;
    private Cactus cactus;
    private Blocs blocs;
    private Stone stone;
    public int playAct = STAY;
    private boolean gameOverAngezeigt = false;
    public Game() {
        move = 0;
        nx = 0;
        nx2 = 960;

        initClass();
        setFocusable(true);
        importBackgroundImg();
        blocs.importElementImage();
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
        blocs = new Blocs();
        stone = new Stone();

    }

    private void importBackgroundImg() {
        ImageIcon image = new ImageIcon("src\\assets\\Hintergrund.png");
        img = image.getImage();
        setPreferredSize(new Dimension(800, 600));
    }

    private void backgroundLoop() {
        if(xImg >= 1920 + (anzahl * 1900)) {
            anzahl += 1;
            nx = 0;
            System.out.println("bGL 1");
        }
        if(xImg >= 960 + (anzahl2 * 1900)) {
            anzahl2 += 1;
            nx2 = 0;
            System.out.println("bGL 2");
        }
    }

    private void playerHitBox() {
        if(playAct == RUNNING_BACKWARD){
            width = 50;
        }
        if(playAct == RUNNING_FORWARD){
            width = 40;
        }
        player.updateHitboxPosition(left, player_Y, width);
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
        playerHitBox();
        g2d.setColor(Color.BLUE);
        g2d.draw(player.getHitbox());

        //Objekte
        g2d.drawImage(cactus.elementImage, enemyStartCactus-nx3, 377, 64, 64, null);
        cactus.updateHitboxPosition(enemyStartCactus-nx3, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus.gethitBox());

        g2d.drawImage(cactus.elementImage, (enemyStartCactus+random)-nx3, 377, 64, 64, null);
        cactus.updateHitboxPosition((enemyStartCactus+random)-nx3, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus.gethitBox());

        g2d.drawImage(blocs.elementImage, 0-nx3, 440, 64, 64, null);
        blocs.updateHitboxPosition(0-nx3, 440);
        g2d.setColor(Color.ORANGE);
        g2d.draw(blocs.getHitBox());

        System.out.println(nx3/20);
        curentScore = nx3/20;
        //fpsCounter();
        repaint();
    }


    private void timerRestart() {
        time = new Timer(15, this);
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        repaint();
        player.setPlayAct(playAct);
        player_Y = jump.jumpPosition;
        backgroundLimit();

        // Überprüfung der Kollision von Hitboxen

        if (player.getHitbox().intersects(cactus.gethitBox())) {
            // Game-Fenster schließt sich
            movement.move = 0;
            System.out.println("deom CurentScore beträgt:" + curentScore);
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        if (player.getHitbox().intersects(stone.getHitBox())) {
            movement.move = 0;
            System.out.println("deom CurentScore beträgt:" + curentScore);
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }



        if(event.getSource() == resetButton){
            resetGame();
        }
    }


    public void backgroundLimit() {
        //Damit der Spieler nicht wieder endlos nach Hinten laufen kann
        if(movement.move != -5){
            if(left + movement.move <= 400){
                left += movement.move;
            }
            else{
                xImg += movement.move;
                nx += movement.move;
                nx2 += movement.move;
                nx3 += movement.move;
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

    public int getLeft(){
        return left;
    }

    public int getPlayer_Y(){
        return player_Y;
    }

    //Funktion die den Reset ausführen soll
    private void resetGame() {
        // Reset game logic goes here
        //Frame.game();
        gameOver();
    }

    //Game Over
    private void zeigeGameOverBildschirm() {
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(540, 440);
        gameOverFrame.setLocationRelativeTo(null); // Positionierung des Fenseters in der Mitte
        gameOverFrame.setVisible(true);
        gameOverFrame.setResizable(false);
    }

    public void gameOver(){
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(540, 440);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setResizable(false);

        GameOver gameOverPanel = new GameOver();  // Erstellen Sie ein neues GameOver-Panel
        gameOverPanel.importElementImage(); // Importieren Sie das Hintergrundbild

        // Fügen Sie das GameOver-Panel zum gameOverFrame hinzu
        gameOverFrame.add(gameOverPanel);

        gameOverFrame.setVisible(true);
        System.out.println("Randoim:" + random);
    }
}
