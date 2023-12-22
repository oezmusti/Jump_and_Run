import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {

    //Variablen
    private Image img;
    private Image playerImg;
    private int left;
    private int player_Y = 400;

    private Timer time;
    private int xImg;
    private int move;
    private int nx, nx2;
    private int anzahl = 0;
    private int anzahl2 = 0;
    private JButton resetButton;
    private Movement movement;
    private Jump jump;

    public Game() {
        move = 0;
        nx = 0;
        nx2 = 1080;

        setFocusable(true);
        //Background Image Pfad
        ImageIcon image = new ImageIcon("src\\assets\\BackgroundImage.png");
        img = image.getImage();

        //Player Image Pfad
        ImageIcon player = new ImageIcon("src\\assets\\Charakter-stehend.png");
        playerImg = player.getImage();

        // Bekantmachung der Klasse Movement
        movement = new Movement();
        jump = new Jump();

        addKeyListener(movement);
        //addKeyListener(new AL());

        // Größe des Panels auf die Größe des Fensters
        setPreferredSize(new Dimension(800, 600));

        // Damit isch der Konstruktor selbst nochmal aufruft
        time = new Timer(5, this);
        time.start();

        //ResetButton
        resetButton = new JButton("Reset");
        resetButton.setBounds(10, 10, 80, 30);
        resetButton.addActionListener(this);
        add(resetButton);
    }

    public void actionPerformed(ActionEvent event) {
        move();
        player_Y = jump.jumpPosition;
        repaint();

        if(event.getSource() == resetButton){
            resetGame();
        }
    }

    //Funktion die den Reset ausführen soll
    private void resetGame() {
        // Reset game logic goes here
        Frame.game();
    }


    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D g2d = (Graphics2D) graphic;

        if(getXImg() == 1080 + (anzahl * 1920)) {
            anzahl += 1;
            nx = 0;
        }
        if(getXImg() == 1920 + (anzahl2 * 1920)) {
            anzahl2 += 1;
            nx2 = 0;
        }

        if(getXImg() >= 1080){
            g2d.drawImage(img, 930-nx, 0, getWidth(), getHeight(), this);
        }
        g2d.drawImage(img, 930-nx2, 0, getWidth(), getHeight(), this);

        g2d.drawImage(playerImg, left, player_Y,64,64, null);
    }

    public int getXImg(){
        return xImg;
    }

    public void move() {

        //Damit der Spieler nicht wieder endlos nach Hinten laufen kann
        if(movement.move != -5){
            if(left + movement.move <= 175){
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

    @Deprecated
    // Private Klasse eventuell extrahieren
    private class AL extends KeyAdapter {


        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_D) {
                move = 5;
            }

            if (key == KeyEvent.VK_A) {
                move = -5;
            }

            if(key == KeyEvent.VK_ESCAPE){
                System.exit(0);
            }
        }


        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
                move = 0;
            }
        }
    }
}
