/*
 * Ein Jump and Run Game
 * @authos Jana und Musti
 * @verson 
*/

import Elements.Rock;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame("Menü");//Framename
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Damit Exit funktioniert
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null); //Positionierung des Fensters in der Mitte des Bildschierms
        frame.setLayout(null); //Damit kein Vorgefertigtes Layout angewendet werden kann
        frame.setResizable(false);
        frame.setVisible(true);
    }
}