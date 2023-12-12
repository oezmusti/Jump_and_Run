import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Frame extends JFrame implements ActionListener {

    private JButton startGame;
    private JButton settings;
    private JButton finish;

    public static void main(String[] args) {

        Frame frame = new Frame("Jump and Run");//Framename
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Damit Exit funktioniert
        frame.setSize(400, 400);

        frame.setLayout(null); //Damit kein Vorgefertigtes Layout angewendet werden kann
        frame.setVisible(true);

    }

    public Frame(String title){
        super(title);

        startGame = new JButton("Starten");
        startGame.setBounds(120, 40, 160, 40);
        startGame.addActionListener(this);
        add(startGame);

        settings = new JButton("Einstellungen");
        settings.setBounds(120, 120, 160, 40);
        settings.addActionListener(this);
        add(settings);

        finish = new JButton("Beenden");
        finish.setBounds(120, 200, 160, 40);
        finish.addActionListener(this);
        add(finish);

    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == startGame){
            game();
        }

        if(event.getSource() == settings){
            auswahl();
        }

        if(event.getSource() == finish) {
            System.exit(0);
        }

    }

    public static void game(){
        JFrame game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(960, 540);
        game.setVisible(true);
        game.add(new Game());

    }

    public static void auswahl(){

    }

}
