import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movement extends KeyAdapter {

    public int move;
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            move = 5;
        }

        if (key == KeyEvent.VK_A) {
            move = -5;
        }

        if (key == KeyEvent.VK_SPACE){
            performJump();
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

    public void performJump(){
        Jump jumpanimation = new Jump();
        jumpanimation.start();
    }
}
