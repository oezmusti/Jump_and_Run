import Elements.*;

import javax.swing.*;
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
    private int player_Y = 349;
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
    //Randomizer
    Random rand = new Random();

    //Assosiations
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

    private void importBackgroundImg() {
        ImageIcon image = new ImageIcon("src\\assets\\Hintergrund.png");
        img = image.getImage();
        setPreferredSize(new Dimension(800, 600));
    }

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

        //Counter Anzeige
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g2d.setColor(Color.RED);
        g2d.drawString("Score: " + curentScore, 10, 25);

        //Zeichne den Spieler
        player.updateAniTick();

        g2d.drawImage(player.goForAni[playAct][player.aniIndex], left, player_Y, 96, 96, null);
        //System.out.println("Player width: " + width);
        playerHitBox();
        g2d.setColor(Color.BLUE);
        g2d.draw(player.getHitbox());

        //System.out.println(player_Y);
        //Objekte
        g2d.drawImage(cactusStart.elementImage, 500 - nx3, 377, 64, 64, null);
        cactusStart.updateHitboxPosition (500- nx3, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactusStart.gethitBox());

        g2d.drawImage(stingStart.elementImage, 700 - nx3, 424, 32, 16, null);
        stingStart.updateHitboxPosition (700- nx3, 424);
        g2d.setColor(Color.RED);
        g2d.draw(stingStart.gethitBox());

        g2d.drawImage(cactus1.elementImage, cactusX2 - nx, 377, 64, 64, null);
        cactus1.updateHitboxPosition (cactusX2- nx, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus1.gethitBox());

        g2d.drawImage(sting.elementImage, stingX - nx, 424, 32, 16, null);
        sting.updateHitboxPosition (stingX- nx, 424);
        g2d.setColor(Color.RED);
        g2d.draw(sting.gethitBox());

        g2d.drawImage(stone1.elementImage, stoneX2 - nx, 377, 64, 64, null);
        stone1.updateHitboxPosition (stoneX2- nx, 377);
        g2d.setColor(Color.RED);
        g2d.draw(stone1.getHitBox());

        g2d.drawImage(stone2.elementImage, stoneX - nx2, 377, 64, 64, null);
        stone2.updateHitboxPosition (stoneX- nx2, 377);
        g2d.setColor(Color.RED);
        g2d.draw(stone2.getHitBox());

        g2d.drawImage(cactus2.elementImage, cactusX - nx2, 377, 64, 64, null);
        cactus2.updateHitboxPosition (cactusX- nx2, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus2.gethitBox());

        g2d.drawImage(blocs1.elementImage, 960-nx2, 440, 960, 64, null);
        blocs1.updateHitboxPosition(960-nx2, 440);
        g2d.setColor(Color.ORANGE);
        g2d.draw(blocs1.getHitBox());

        g2d.drawImage(blocs2.elementImage, 960-nx, 440, 960, 64, null);
        blocs2.updateHitboxPosition(960-nx, 440);
        g2d.setColor(Color.ORANGE);
        g2d.draw(blocs2.getHitBox());

        g2d.drawImage(holeStart.elementImage, 950 - nx3, 440, 80, 64, null);
        holeStart.updateHitboxPosition (950- nx3, 440);
        g2d.setColor(Color.RED);
        g2d.draw(holeStart.gethitBox());

        g2d.drawImage(hole1.elementImage, holeX2 - nx, 440, 80, 64, null);
        hole1.updateHitboxPosition (holeX2- nx, 440);
        g2d.setColor(Color.RED);
        g2d.draw(hole1.gethitBox());

        g2d.drawImage(hole2.elementImage, holeX1 - nx2, 440, 80, 64, null);
        hole2.updateHitboxPosition (holeX1 - nx2, 440);
        g2d.setColor(Color.RED);
        g2d.draw(hole2.gethitBox());
        /*
        g2d.drawImage(cactus1.elementImage, enemyStartCactus-nx3, 377, 64, 64, null);
        this.cactus1.updateHitboxPosition(enemyStartCactus-nx3, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus1.gethitBox());

        g2d.drawImage(cactus2.elementImage, (enemyStartCactus+random)-nx3, 377, 64, 64, null);
        this.cactus2.updateHitboxPosition((enemyStartCactus+random)-nx3, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus2.gethitBox());

        g2d.drawImage(cactus3.elementImage, (enemyStartCactus+random+200)-nx3, 377, 64, 64, null);
        this.cactus3.updateHitboxPosition((enemyStartCactus+random+200)-nx3, 377);
        g2d.setColor(Color.RED);
        g2d.draw(cactus3.gethitBox());

        g2d.drawImage(blocs.elementImage, 0-nx3, 440, 64, 64, null);
        blocs.updateHitboxPosition(0-nx3, 440);
        g2d.setColor(Color.ORANGE);
        g2d.draw(blocs.getHitBox());
         */

        //System.out.println(curentScore);
        curentScore =  nx3/20;

        //fpsCounter();
        repaint();
    }


    private void timerRestart() {
        time = new Timer(15, this);
        time.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        //repaint();
        player.setPlayAct(playAct);
        player_Y = jump.jumpPosition;
        backgroundLimit();

       /*
       if(player.getHitbox().intersects(blocs1.getHitBox()) || player.getHitbox().intersects(blocs2.getHitBox())){
           System.out.println("Kein GameOver");
       }
       else {
           if (player_Y < 349) {
               System.out.println("Kein GameOver");
           } else if (player_Y == 349 && (player.getHitbox().intersects(blocs1.getHitBox()) || player.getHitbox().intersects(blocs2.getHitBox()))) {
               System.out.println("Kein GameOver");
           } else if (!(player.getHitbox().intersects(blocs1.getHitBox()) && player.getHitbox().intersects(blocs2.getHitBox())) && player_Y <= 349) {
               System.out.println("Das ist das andere GameOver");
           } else if (player_Y > 349 && player.getHitbox().intersects(blocs1.getHitBox()) && player.getHitbox().intersects(blocs2.getHitBox())) {
               System.out.println("Kein GameOver");
           } else {
               System.out.println("Das ist nicht nur GameOver sondern mehr GameOver");
               movement.move = 0;
               if (!gameOverAngezeigt) {
                   gameOver();
                   gameOverAngezeigt = true;
                }
            }
         }

        */

        if (player.getHitbox().intersects(hole1.gethitBox()) || player.getHitbox().intersects(hole2.gethitBox()) || player.getHitbox().intersects(holeStart.gethitBox())) {
            //System.out.println("deom CurentScore beträgt:" + curentScore);
            movement.move = 0;
            killObject = hole1.getName();
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        // Überprüfung der Kollision von Hitboxen
        if (player.getHitbox().intersects(cactus1.gethitBox()) || player.getHitbox().intersects(cactus2.gethitBox()) || player.getHitbox().intersects(cactusStart.gethitBox())) {
            // Game-Fenster schließt sich
            movement.move = 0;
            killObject = cactus1.getName();
            //System.out.println("deom CurentScore beträgt:" + curentScore);
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        // Überprüfung der Kollision von Hitboxen
        if (player.getHitbox().intersects(sting.gethitBox()) || player.getHitbox().intersects(stingStart.gethitBox())) {
            // Game-Fenster schließt sich
            movement.move = 0;
            killObject = sting.getName();
            //System.out.println("deom CurentScore beträgt:" + curentScore);
            if (!gameOverAngezeigt) {
                gameOver();
                gameOverAngezeigt = true;
            }
        }

        if (player.getHitbox().intersects(stone1.getHitBox()) || player.getHitbox().intersects(stone2.getHitBox())) {
            movement.move = 0;
            killObject = stone1.getName();
            //System.out.println("deom CurentScore beträgt:" + curentScore);
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

    public int getCurentScore(){
        return curentScore;
    }

    //Funktion die den Reset ausführen soll
    private void resetGame() {
        // Reset game logic goes here
        //Frame.game();
        gameOver();
    }

    //Game Over
    @Deprecated
    private void zeigeGameOverBildschirm() {
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(540, 440);
        gameOverFrame.setLocationRelativeTo(null); // Positionierung des Fenseters in der Mitte
        gameOverFrame.setVisible(true);
        gameOverFrame.setResizable(false);
    }

    public void gameOver() {
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(540, 440);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setResizable(false);
        gameOverFrame.setUndecorated(true);

        GameOver gameOverPanel = new GameOver();
        //gameOverPanel.importElementImage();
        gameOverPanel.setScore(curentScore);
        gameOverPanel.setKillObject(killObject);
        gameOverFrame.add(gameOverPanel);
        gameOverFrame.setVisible(true);
    }
}
