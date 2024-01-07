import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Frame extends JFrame implements ActionListener {

    private JButton startGame;
    private JButton settings;
    private JButton controles;
    private JButton finish;
    private BufferedImage backgroundImage;

    public static void main(String[] args) {

        /*
         *Frame wird mit den definierten Parametern erstellt
         * Name: Jum and Run
         * Fensterweite: 400
         * Fensterhöhe 400
         *
         */
        Frame frame = new Frame("Menü");//Framename
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Damit Exit funktioniert
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null); //Positionierung des Fensters in der Mitte des Bildschierms
        frame.setLayout(null); //Damit kein Vorgefertigtes Layout angewendet werden kann
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void importPlayerImg() {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/BackgroundImage.png");

        try {
            if (stream != null) {
                backgroundImage = ImageIO.read(stream);
            } else {
                System.err.println("Bild nicht gefunden: assets/Charakter-stehend.png");
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

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D startGraphic = (Graphics2D) g;
        startGraphic.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    /*
     *Frameinhalte
     *
     * Hier wrden die Frameinhalte für das Startfenster definiert.
     * Zurzeiit bestehe diese aus 3 Schasltflächen.
     * Unter andeem werden hier die Positionen und die Propositionen definiert.
     *
     */
    public Frame(String title){
        super(title);

        importPlayerImg();

        importJButtons();

    }

    private void importJButtons() {
        startGame = new JButton("Starten");
        startGame.setBounds(120, 170, 160, 40);
        startGame.addActionListener(this);
        startGame.setVisible(true);
        add(startGame);

        settings = new JButton("Einstellungen");
        settings.setBounds(120, 250, 160, 40);
        settings.addActionListener(this);
        settings.setVisible(true);
        add(settings);

        controles = new JButton("Steuerung");
        controles.setBounds(120, 330, 160, 40);
        controles.addActionListener(this);
        controles.setVisible(true);
        add(controles);

        finish = new JButton("Beenden");
        finish.setBounds(120, 410, 160, 40);
        finish.addActionListener(this);
        finish.setVisible(true);
        add(finish);
    }

    /*
    *Buttonfunktionen
    *
    * Hier wird durch den ActionListener funktionen ausfgelöst,
    *  die beim Drücken der jeweiligen Schaltföche ausgeführt werden
    */
    //Steuerung der Buttons
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == startGame){
            setVisible(false);
            game();
        }

        if(event.getSource() == settings){
            set();
        }

        if(event.getSource() == controles){
            control();
        }

        if(event.getSource() == finish) {
            System.exit(0);
        }

    }

    /*
     * Initialisierung des Spielfensters
     *
     * Hier werden die Daten f+r das Spielfenster festgelegt.
     * Ansschließend wird durch die game.add(new Game()); das Spiel geladen
     */
    public static void game(){
        JFrame game = new JFrame("Jump and Run");
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(960, 540);
        game.setLocationRelativeTo(null); //Positionierung des Fenseters in der Mitte
        game.setVisible(true);
        game.setResizable(false);
        game.add(new Game());

    }

    public static void set(){

    }

    public static void control(){

    }
}
