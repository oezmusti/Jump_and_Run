
import Elements.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import static util.PlaayerConst.PlayerMovings.*;

/**
 * Gamefenster
 * Erstellen der Game Klasse, welches von JPanel erbt und das Interface ActionListener besitzt
 * */
public class Game extends JPanel implements ActionListener {

    private BufferedImage img;
    private int left;
    private int playerY = 349;
    private int frames = 0;
    private long lastCheck = 0;
    private int width = 40;
    public int curentScore;

    private Timer time;
    private int xImg;
    private int move;
    private int nx, nx2;
    public int nx3 = 0;
    private int imageNumber = 0;
    private int imageNumber2 = 0;
    private JButton resetButton;
    private int cactusX;
    private int stoneX;
    private int holeX1;
    private int stingX = 1380;
    private int cactusX2 = 1160;
    private int stoneX2 = 1560;
    private int holeX2 = 1810;
    private String killObject;
    Random rand = new Random();
    private Movement movement;;
    private Jump jump;
    private Player player;
    private GameOver gameOver;
    private Blocs blocs1;
    private Blocs blocs2;
    private Cactus cactusStart;
    private Cactus cactus1;
    private Cactus cactus2;
    private Stone stone1;
    private Stone stone2;
    private Sting stingStart;
    private Sting sting;
    private Hole holeStart;
    private Hole hole1;
    private Hole hole2;
    public int playAct = STAY;
    private boolean gameOverAngezeigt = false;
    public Game() {
        move = 0;
        nx = 0;
        nx2 = 960;

        initClass();
        setFocusable(true);
        importBackgroundImg();
        player.importPlayerImg();
        addKeyListener(movement);
        timerRestart();

        //ResetButton
        resetButton = new JButton("Reset");
        resetButton.setBounds(600, 0, 80, 30);
        resetButton.addActionListener(this);
        add(resetButton);
    }

    /**
     * Initialisierung der Klassenatrebute
     * */
    private void initClass() {
        movement = new Movement(this);
        jump = new Jump();
        player = new Player();
        gameOver = new GameOver();
        blocs1 = new Blocs();
        blocs2 = new Blocs();
        cactusStart = new Cactus();
        cactus1 = new Cactus();
        cactus2 = new Cactus();
        stone1 = new Stone();
        stone2 = new Stone();
        stingStart = new Sting();
        sting = new Sting();
        holeStart = new Hole();
        hole1 = new Hole();
        hole2 = new Hole();
    }


    /**
     * Import des Hintergrundbildes
     */
    private void importBackgroundImg() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Hintergrund.png");

        try {
            if (stream != null) {
                img = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/Cactus-stehend.png");
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
     * Erstellung des Backgroundloops
     */
    private void backgroundLoop() {
        if(xImg >= 1920 + (imageNumber * 1900)) {
            imageNumber += 1;
            nx = 0;
            cactusX2 = rand.nextInt(191);
            cactusX2 += 1110;
            stoneX2 = rand.nextInt(191);
            stoneX2 += 1515;
            holeX2 = rand.nextInt(191);
            holeX2 += 1765;
            stingX = rand.nextInt(191);
            stingX += 1370;
            System.out.println("bGL 1");
        }

        if(xImg >= 960 + (imageNumber2 * 1900)) {
            imageNumber2 += 1;
            nx2 = 0;
            cactusX = rand.nextInt(191);
            cactusX += 1110;
            stoneX = rand.nextInt(191);
            stoneX += 1515;
            holeX1 = rand.nextInt(191);
            holeX1 += 1765;
            System.out.println("bGL 2");
        }
    }


    /**
     * Playerhitbox vergrößerung oder verkleinerung je nach Position
     */
    private void playerHitBox() {
        if(playAct == RUNNING_BACKWARD){
            width = 50;
        }
        if(playAct == RUNNING_FORWARD){
            width = 40;
        }
        player.updateHitboxPosition(left, playerY, width);
    }

    /**
     * Zeichnen aller Elemente für das Spielfenster
     * Zeichnen des Backgrounds
     * Anzeige des Scores
     * import Player
     * import der Eleemente
     * Zählen der erlangten Reichweite
     * Neuzeichnen
     *
     * @param graphic the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D g2d = (Graphics2D) graphic;
        backgroundLoop();

        // Zeichnen des Hintergrunds
        g2d.drawImage(img, 960 - nx, 0, getWidth(), getHeight(), this);
        g2d.drawImage(img, 960 - nx2, 0, getWidth(), getHeight(), this);

        //Score Anzeige
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2d.setColor(Color.RED);
        g2d.drawString("Score: " + curentScore, 10, 25);

        //Player importieren
        player.updateAniTick();
        g2d.drawImage(player.goForAni[playAct][player.aniIndex], left, playerY, 96, 96, null);
        playerHitBox();
        //g2d.setColor(Color.BLUE);
        //g2d.draw(player.getHitbox());

        //Objekte
        g2d.drawImage(cactusStart.getElementImage(), 500 - nx3, 377, 64, 64, null);
        cactusStart.updateHitboxPosition (500- nx3, 377);
        //g2d.setColor(Color.RED);
        //g2d.draw(cactusStart.getHitBox());

        g2d.drawImage(stingStart.getElementImage(), 700 - nx3, 424, 32, 16, null);
        stingStart.updateHitboxPosition (700- nx3, 424);
        //g2d.setColor(Color.RED);
        //g2d.draw(stingStart.getHitBox());

        g2d.drawImage(cactus1.getElementImage(), cactusX2 - nx, 377, 64, 64, null);
        cactus1.updateHitboxPosition (cactusX2- nx, 377);
        //g2d.setColor(Color.RED);
        //g2d.draw(cactus1.getHitBox());

        g2d.drawImage(sting.getElementImage(), stingX - nx, 424, 32, 16, null);
        sting.updateHitboxPosition (stingX- nx, 424);
        //g2d.setColor(Color.RED);
        //g2d.draw(sting.getHitBox());

        g2d.drawImage(stone1.getElementImage(), stoneX2 - nx, 377, 64, 64, null);
        stone1.updateHitboxPosition (stoneX2- nx, 377);
        //g2d.setColor(Color.RED);
        //g2d.draw(stone1.getHitBox());

        g2d.drawImage(stone2.getElementImage(), stoneX - nx2, 377, 64, 64, null);
        stone2.updateHitboxPosition (stoneX- nx2, 377);
        //g2d.setColor(Color.RED);
        //g2d.draw(stone2.getHitBox());

        g2d.drawImage(cactus2.getElementImage(), cactusX - nx2, 377, 64, 64, null);
        cactus2.updateHitboxPosition (cactusX- nx2, 377);
        //g2d.setColor(Color.RED);
        //g2d.draw(cactus2.getHitBox());

        g2d.drawImage(blocs1.getElementImage(), 960-nx2, 440, 960, 64, null);
        blocs1.updateHitboxPosition(960-nx2, 440);
        //g2d.setColor(Color.ORANGE);
        //g2d.draw(blocs1.getHitBox());

        g2d.drawImage(blocs2.getElementImage(), 960-nx, 440, 960, 64, null);
        blocs2.updateHitboxPosition(960-nx, 440);
        //g2d.setColor(Color.ORANGE);
        //g2d.draw(blocs2.getHitBox());

        g2d.drawImage(holeStart.getElementImage(), 950 - nx3, 440, 80, 64, null);
        holeStart.updateHitboxPosition (950- nx3, 440);
        //g2d.setColor(Color.RED);
        //g2d.draw(holeStart.getHitBox());

        g2d.drawImage(hole1.getElementImage(), holeX2 - nx, 440, 80, 64, null);
        hole1.updateHitboxPosition (holeX2- nx, 440);
        //g2d.setColor(Color.RED);
        //g2d.draw(hole1.getHitBox());

        g2d.drawImage(hole2.getElementImage(), holeX1 - nx2, 440, 80, 64, null);
        hole2.updateHitboxPosition (holeX1 - nx2, 440);
        //g2d.setColor(Color.RED);
        //g2d.draw(hole2.getHitBox());

        curentScore =  nx3/20;

        repaint();
    }


    /**
     * Setzen eines Timersfür für die actionPerformed Methode
     */
    private void timerRestart() {
        time = new Timer(15, this);
        time.start();
    }

    /**
     *  Implementierung des actionPerformed
     *  Übermittlung des Playerzstandes
     *  Gleichsetzen von playerY mit jump.jumpPosition
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        player.setPlayAct(playAct);
        playerY = jump.jumpPosition;
        backgroundLimit();

        if (player.getHitbox().intersects(hole1.getHitBox()) || player.getHitbox().intersects(hole2.getHitBox()) || player.getHitbox().intersects(holeStart.getHitBox())) {
            movement.move = 0;
            killObject = hole1.getName();
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        if (player.getHitbox().intersects(cactus1.getHitBox()) || player.getHitbox().intersects(cactus2.getHitBox()) || player.getHitbox().intersects(cactusStart.getHitBox())) {
            movement.move = 0;
            killObject = cactus1.getName();
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        if (player.getHitbox().intersects(sting.getHitBox()) || player.getHitbox().intersects(stingStart.getHitBox())) {
            movement.move = 0;
            killObject = sting.getName();
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        if (player.getHitbox().intersects(stone1.getHitBox()) || player.getHitbox().intersects(stone2.getHitBox())) {
            movement.move = 0;
            killObject = stone1.getName();
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        if(event.getSource() == resetButton){
            Window[] windows = Window.getWindows();
            for (Window window : windows) {
                if (window instanceof JFrame) {
                    window.dispose();
                }
            }

            JFrame game = new JFrame("Jump and Run");
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setSize(977, 540);
            game.setLocationRelativeTo(null); //Positionierung des Fensters in der Mitte
            game.setVisible(true);
            game.setResizable(false);
            game.add(new Game());

        }
    }

    /**
     * Der Hintergrund wird erst ab 400px nach hinten bewegt
     * Verhindern, dass der Spieler endlos nach hinten läuft kann
     */
    public void backgroundLimit() {
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

    public void setPlayAct(int playAct){
        this.playAct = playAct;
    }

    public int getLeft(){
        return left;
    }

    public int getPlayerY(){
        return playerY;
    }

    public int getCurentScore(){
        return curentScore;
    }


    /**
     * Neustart des Games
     */
    private void resetGame() {
        // Reset game logic goes here
        //Frame.game();
        gameOver();
    }

    /**
     * Erstellen eines neuen GameOver Fensters beim Aufruf der Funktion gameOver();
     */
    public void gameOver() {
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(540, 440);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setResizable(false);
        gameOverFrame.setUndecorated(true);

        GameOver gameOverPanel = new GameOver();
        gameOverPanel.setScore(curentScore);
        gameOverPanel.setKillObject(killObject);
        gameOverFrame.add(gameOverPanel);
        gameOverFrame.setVisible(true);
    }
}
