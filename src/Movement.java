import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static util.PlaayerConst.PlayerMovings.*;

/**
 * Erstellung der Movement Klasse, welche von der KeyAdapter Klasse erbt
 */
public class Movement extends KeyAdapter {
    public int move;
    private Game game;
    private Player player = new Player();

    /**
     * Prüfen auf tasteneingabe
     *
     * @param e Eventeingabe
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            //System.out.println("D wurde gedrückt");
            game.setPlayAct(RUNNING_FORWARD);
            player.setPlayAct(RUNNING_FORWARD);
            move = 5;
        }

        if (key == KeyEvent.VK_A) {
            //System.out.println("A wurde gedrückt");
            game.setPlayAct(RUNNING_BACKWARD);
            player.setPlayAct(RUNNING_BACKWARD);
            move = -5;
        }

        if (key == KeyEvent.VK_W){
            //System.out.println("SPACE wurde gedrückt");
            performJump();
        }

        if(key == KeyEvent.VK_ESCAPE){
            //System.out.println("Programm wurde beendet");
            System.exit(0);
        }
    }

    /**
     * Prüfen ob Taste losgelassen wurde
     *
     * @param e Eventeingabe
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            game.setPlayAct(STAY_BACK);
            player.setPlayAct(STAY_BACK);
            move = 0;
        }
        if (key == KeyEvent.VK_D){
            game.setPlayAct(STAY);
            player.setPlayAct(STAY);
            move = 0;
        }
    }

    /**
     * Implementierung von einem Sprung
     */
    public void performJump(){
        Jump jumpanimation = new Jump();
        jumpanimation.start();
    }

    public Movement(Game game) {
        this.game = game;
    }
}
