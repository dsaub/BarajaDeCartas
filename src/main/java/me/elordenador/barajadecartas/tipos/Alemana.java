package me.elordenador.barajadecartas.tipos;

/**
 * Represents the German deck of cards with constants for suits and face cards.
 */
public class Alemana {
    /**
     * Constant for the suit "Campanas".
     */
    public static final String CAMPANAS = "Campanas";

    /**
     * Constant for the suit "Pastos".
     */
    public static final String PASTOS = "Pastos";

    /**
     * Constant for the suit "Corazones".
     */
    public static final String CORAZONES = "Corazones";

    /**
     * Constant for the suit "Bellotas".
     */
    public static final String BELLOTAS = "Bellotas";

    /**
     * Constant for the face card "Campesino" with a value of 11.
     */
    public static final int CAMPESINO = 11;

    /**
     * Constant for the face card "Dama" with a value of 12.
     */
    public static final int DAMA = 12;

    /**
     * Constant for the face card "Rey" with a value of 13.
     */
    public static final int REY = 13;

    /**
     * Constant for the face card "As" with a value of 14.
     */
    public static final int AS = 14;

    /**
     * Returns a numerical value corresponding to the given suit.
     *
     * @param n The suit of the card as a string.
     * @return An integer representing the suit: 4 for Campanas, 3 for Corazones, 2 for Pastos, 1 for Bellotas, and 0 for any other value or null.
     */
    public static int getN(String n) {

        if (n == null) {
            return 0;
        }

        switch (n) {
            case CAMPANAS: return 4;
            case CORAZONES: return 3;
            case PASTOS: return 2;
            case BELLOTAS: return 1;
            default: return 0;
        }
    }
}