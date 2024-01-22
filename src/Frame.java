import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class Frame extends JFrame implements ActionListener {

    private JButton startGame;
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
        frame.setSize(400, 540);
        frame.setLocationRelativeTo(null); //Positionierung des Fensters in der Mitte des Bildschierms
        frame.setLayout(null); //Damit kein Vorgefertigtes Layout angewendet werden kann
        frame.setResizable(false);
        frame.setVisible(true);
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
        importBackgroudImg();
        importJButtons();

    }

    public void importBackgroudImg() {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("assets/Start.png");
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

    @Override
    public void paint(Graphics g) {
        super.paint(g); // First, draw the background of the parent class
        Graphics2D startGraphic = (Graphics2D) g;
        startGraphic.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Then, paint the buttons
        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).paint(g);
            }
        }
    }


    private void importJButtons() {
        startGame = new JButton("Starten");
        startGame.setBounds(120, 170, 160, 40);
        startGame.addActionListener(this);
        startGame.setVisible(true);
        add(startGame);

        controles = new JButton("Steuerung");
        controles.setBounds(120, 250, 160, 40);
        controles.addActionListener(this);
        controles.setVisible(true);
        add(controles);

        finish = new JButton("Beenden");
        finish.setBounds(120, 330, 160, 40);
        finish.addActionListener(this);
        finish.setVisible(true);
        add(finish);

        System.out.println("Buttons gesetzt");
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

        if(event.getSource() == controles){
            control();
        }

        if(event.getSource() == finish) {
            System.exit(0);
        }
        repaint();
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
        game.setSize(977, 540);
        game.setLocationRelativeTo(null); //Positionierung des Fenseters in der Mitte
        game.setVisible(true);
        game.setResizable(false);
        game.add(new Game());

    }

    public static void control(){
         String message = "<html>" +
                            "<body style='margin:15px; font-size:12px;'>" +
                                "<p style='color: #3498db;'>Steuerung:</p>" +
                                "<br>" +
                                "<p>Nach vorne Laufen:  D</p>" +
                                "<p>Zurück laufen:  A</p>" +
                                "<p>Springen:  W</p>" +
                                "<p>Spiel verlassen: ESCAPE</p>" +
                                "<br>" +
                                "<p style='color: #3498db;'>So wird gespielt:</p>" +
                                "<br>" +
                                "<p>Springe über die verschiedenen Hindernisse, <br> sei aber vorischtig und lass dich nicht erwischen.</p>" +
                                "<p>Auch vor Gruben solltest du dich stqark in Acht nehme, <br> da fällt man leicht runter</p>" +
                                "<p>Sein Ziel ist es vor dem angedrohten Sturm zu entkommen <br> und dabei so viele Meter zu machen wir möglich</p>" +
                                "<br>" +
                                "<p>Viel Glück, du wirst es brauchen</p>" +
                            "</body>" +
                          "</html>";

        JOptionPane.showMessageDialog(null, message, "Steuerung", JOptionPane.INFORMATION_MESSAGE);
    }
}
