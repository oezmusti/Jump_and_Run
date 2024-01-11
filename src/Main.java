/*
 * Ein Jump and Run Game
 * @authos Jana und Musti
 * @verson 
*/

import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        /*
         *Frame wird mit den definierten Parametern erstellt
         * Name: Jum and Run
         * Fensterweite: 400
         * Fensterhöhe 400
         *
         */
        Frame frame = new Frame("Start");//Framename
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Damit Exit funktioniert
        frame.setSize(400, 540);
        frame.setLocationRelativeTo(null); //Positionierung des Fensters in der Mitte des Bildschierms
        frame.setLayout(null); //Damit kein Vorgefertigtes Layout angewendet werden kann
        frame.setResizable(false);
        frame.setVisible(true);
    }

}