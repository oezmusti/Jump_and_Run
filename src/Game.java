import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

    private Image img;
    private Timer time;
    private int xImg;
    private int move;
    private int nx, nx2;
    private int anzahl = 0;
    private int anzahl2 = 0;

    public Game() {
        move = 0;
        nx = 0;
        nx2 = 960;

        setFocusable(true);
        ImageIcon image = new ImageIcon("src\\assets\\Hintergruind_NEU.jpeg");
        img = image.getImage();
        addKeyListener(new AL());

        // Setzen Sie die Größe des Panels auf die Größe des Fensters
        setPreferredSize(new Dimension(800, 600));

        // Damit isch der Konstruktor selbst nochmal aufruft
        time = new Timer(5, this);
        time.start();
    }

    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D g2d = (Graphics2D) graphic;
        g2d.drawImage(img, xImg, 0, getWidth(), getHeight(), this);

        if(getXImg() == 960 + (anzahl * 1920)) {
            anzahl += 1;
            nx = 0;
        }
        if(getXImg() == 1920 + (anzahl2 * 1920)) {
            anzahl2 += 1;
            nx2 = 0;
        }

        if(getXImg() >= 510){
            g2d.drawImage(img, 960-nx, 0, getWidth(), getHeight(), this);
        }
        g2d.drawImage(img, 960-nx2, 0, getWidth(), getHeight(), this);


    }

    public int getXImg(){
        return xImg;
    }

    public void move() {
        xImg += move;
        nx += move;
        nx2 += move;
    }

    // Private Klasse eventuell extrahieren
    private class AL extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_A) {
                move = 3;
            }

            if (key == KeyEvent.VK_D) {
                move = -3;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
                move = 0;
            }
        }
    }
}
