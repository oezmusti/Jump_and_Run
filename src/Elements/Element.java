package Elements;

import java.awt.*;

abstract class Element {
    protected String name;
    protected Rectangle hitBox;

    protected abstract void importElementImage();

    protected abstract void updateHitboxPosition(int x, int y);


}
