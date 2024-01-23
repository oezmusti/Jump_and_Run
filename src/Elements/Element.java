package Elements;

import java.awt.*;

/**
 * Abstraktw Klasse Element
 */
abstract class Element {
    protected String name;
    protected Rectangle hitBox;

    /**
     * Methode zum setzen eines Bildes
     * Muss überschrieben werden
     */
    protected abstract void importElementImage();

    /**
     * Methode zum updaten der hitboxn
     * Muss überschrieben werden
     */
    protected abstract void updateHitboxPosition(int x, int y);


}
