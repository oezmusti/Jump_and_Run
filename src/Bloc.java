// Jana
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Bloc extends JPanel implements ActionListener {
    private int blocY;
    private int blocX;

    public static void main (String[] args) {
        JFrame meinFrame = new JFrame("Beispiel JFrame"); // Erzeugung eines neues Frames
        meinFrame.setSize(600, 400); // Breite und Höhe in Pixel vom Fenster
        meinFrame.setVisible(true); // Fenster sichtbar machen


    }

    public Bloc() {

        // Block Image bekannt machen
        ImageIcon bloc = new ImageIcon("src\\assets\\Block.png");
        Image blocImg = bloc.getImage();




        protected void paintComponent(Graphics graphic) {
            super.paintComponent(graphic);
            Graphics2D g2d = (Graphics2D) graphic;
            g2d.drawImage(blocImg, blocX, blocY,64,64, null);


        }
    }

    public void actionPerformed(ActionEvent event) {

    }
}*/

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bloc {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Blöcke");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fügen Sie das BildPanel zum JFrame hinzu
        frame.getContentPane().add(new BildPanel());

        frame.setSize(960, 540);

        frame.setVisible(true);
        frame.setResizable(false);

    }
}

class BildPanel extends JPanel {

    private Image blocImg;
    private Image blocBrownImg;
    private Image stoneImg;
    private Image cactusImg;

    Random random = new Random();
    private int randomZahl = random.nextInt(5); // Zahl 0-5
    private int x = 0;
    private int y;


    public BildPanel() {
        // Laden Sie das Bild
        blocImg = new ImageIcon("src\\assets\\Block.png").getImage();
        blocBrownImg = new ImageIcon("src\\assets\\blocBrown.png").getImage();
        stoneImg = new ImageIcon("src\\assets\\grauerStein.png").getImage();
        cactusImg = new ImageIcon("src\\assets\\Cactus.png").getImage();

    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);

        switch (randomZahl) {
            case 0:
                while (x < 960) {
                    if (x == 256  || x == 320 || x == 384) {

                    } else {
                        g.drawImage(blocImg, x, 440, 64, 64, this);
                    }
                    x = x + 64;
                }

                g.drawImage(blocImg, 320, 330, 64, 64, this);

                g.drawImage(stoneImg, 50, 410, 64, 32, this);
                g.drawImage(stoneImg, 636, 410, 64, 32, this); //600
                g.drawImage(stoneImg, 700, 410, 64, 32, this); //800

                break;

            case 1:
                while (x < 960) {
                    if (x == 448 || x == 512 || x == 576) {
                        g.drawImage(blocBrownImg, x, 440, 64, 64, this);
                    } else {
                        g.drawImage(blocImg, x, 440, 64, 64, this);
                    }
                    x = x + 64;
                }

                g.drawImage(blocImg, 448, 376, 64, 64, this);
                g.drawImage(blocBrownImg, 512, 376, 64, 64, this);
                g.drawImage(blocImg, 576, 376, 64, 64, this);

                g.drawImage(blocImg, 512, 312, 64, 64, this);

                g.drawImage(cactusImg, 150, 376, 64, 64, this);
                g.drawImage(stoneImg, 800, 410, 64, 32, this);

                break;

            case 2:
                while (x < 960) {

                    if (x <= 128 || x >=768) {
                        g.drawImage(blocImg, x, 440, 64, 64, this);
                    } else if (x >= 256 && x <= 640){ //|| x <=704
                        g.drawImage(blocImg, x, 350, 64, 64, this);
                    }
                    x = x + 64;
                }

                g.drawImage(stoneImg, 448, 318, 64, 32, this);
                g.drawImage(cactusImg, 448, 262, 64, 64, this);

                break;

            case 3:
                while (x < 960) {
                    g.drawImage(blocImg, x, 440, 64, 64, this);
                    x = x + 64;
                }

                g.drawImage(stoneImg, 236, 410, 64, 32, this);
                g.drawImage(stoneImg, 300, 410, 64, 32, this);
                g.drawImage(stoneImg, 268, 385, 64, 32, this);

                g.drawImage(cactusImg, 700, 376, 64, 64, this);

                break;

            case 4:
                while (x < 960) {
                    if (x<= 64 || x>= 832) {
                        g.drawImage(blocImg, x, 440, 64, 64, this);
                    }else {
                        g.drawImage(blocBrownImg, x, 440, 64, 64, this);
                        g.drawImage(blocImg, x, 376, 64, 64, this);
                    }
                    x = x + 64;
                }

                g.drawImage(stoneImg, 260, 344, 64, 32, this);
                g.drawImage(stoneImg, 600, 344, 64, 32, this);

                break;

            case 5:
                while (x < 960) {
                    g.drawImage(blocImg, x, 440, 64, 64, this);
                    g.drawImage(blocImg, x + 64, 440, 64, 64, this);

                    x = x + 192;
                }

                break;

        }

        /*
        +++++++++++++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++Layout3++++++++++++++++++++++

        while (x < 960) {

            if (x <= 128 || x >=768) {
                g.drawImage(blocImg, x, 440, 64, 64, this);
            } else if (x >= 256 && x <= 640){ //|| x <=704
                g.drawImage(blocImg, x, 350, 64, 64, this);
            }
            x = x + 64;
        }

        g.drawImage(stoneImg, 448, 318, 64, 32, this);
        g.drawImage(cactusImg, 448, 262, 64, 64, this);

        ++++++++++++++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++
         */

        /*
        ++++++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++Layout 2++++++++++++++++++


        while (x < 960) {
            if (x == 448 || x == 512 || x == 576) {
                g.drawImage(blocBrownImg, x, 440, 64, 64, this);
            } else {
                g.drawImage(blocImg, x, 440, 64, 64, this);
            }
            x = x + 64;
        }

        g.drawImage(blocImg, 448, 376, 64, 64, this);
        g.drawImage(blocBrownImg, 512, 376, 64, 64, this);
        g.drawImage(blocImg, 576, 376, 64, 64, this);

        g.drawImage(blocImg, 512, 312, 64, 64, this);

        g.drawImage(cactusImg, 150, 376, 64, 64, this);
        g.drawImage(stoneImg, 800, 410, 64, 32, this);

        ++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++
         */



        /*
        ++++++++++++++++++++++++++++++++++++
        ++++++++++Layout 1++++++++++++++++++

        while (x < 960) {
            if (x == 256  || x == 320 || x == 384) {

            } else {
                g.drawImage(blocImg, x, 440, 64, 64, this);
            }
            x = x + 64;
        }

        g.drawImage(blocImg, 320, 330, 64, 64, this);

        g.drawImage(stoneImg, 50, 410, 64, 32, this);
        g.drawImage(stoneImg, 636, 410, 64, 32, this); //600
        g.drawImage(stoneImg, 700, 410, 64, 32, this); //800

        +++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++
        */



        //g.drawImage(blocImg, 0, 440, 64, 64, this);
        //g.drawImage(blocImg, 64, 440, 64, 64, this);


    }
}




