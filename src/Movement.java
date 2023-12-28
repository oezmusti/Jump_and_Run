import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movement extends KeyAdapter {

    public int move;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            System.out.println("D wurde gedrückt");
            move = 5;
        }

        if (key == KeyEvent.VK_A) {
            System.out.println("A wurde gedrückt");
            move = -5;
        }

        if (key == KeyEvent.VK_SPACE){
            System.out.println("SPACE wurde gedrückt");
            performJump();
        }

        if(key == KeyEvent.VK_ESCAPE){
            System.out.println("Programm wurde beendet");
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_D) {
            //System.out.println("Keine Bewegung nach links oder rechts");
            move = 0;
        }
    }

    public void performJump(){
        Jump jumpanimation = new Jump();
        jumpanimation.start();
    }
}
