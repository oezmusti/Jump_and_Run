package util;

/**
 * Implementierung der PlaayerConst Klasse
 * Eine Hilfsklasse um di
 * */
public class PlaayerConst {
    public static class PlayerMovings{
        public static final int STAY = 0;
        public static final int RUNNING_FORWARD = 1;
        public static final int STAY_BACK = 2;
        public static final int RUNNING_BACKWARD = 3;

        /**
         * Abruf der angezeigten Stelle des Spielerbildes im 2D Array
         *
         * @param action Übergabe, welche Position angezeigt werden soll im Spiel
         * */
        public static int GetSpriteAmount(int action){

            switch (action){
                case RUNNING_FORWARD:
                case RUNNING_BACKWARD:
                    return 2;
                case STAY:
                case STAY_BACK:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}
