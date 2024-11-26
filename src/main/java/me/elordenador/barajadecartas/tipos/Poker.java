package me.elordenador.barajadecartas.tipos;

/**
 * Represents the Poker deck of cards with constants for suits and face cards.
 */
public class Poker {
    /**
     * Constant for the suit "Corazones".
     */
    public static final String CORAZONES = "Corazones";

    /**
     * Constant for the suit "Diamantes".
     */
    public static final String DIAMANTES = "Diamantes";

    /**
     * Constant for the suit "Picas".
     */
    public static final String PICAS = "Picas";

    /**
     * Constant for the suit "Treboles".
     */
    public static final String TREBOLES = "Treboles";

    /**
     * Constant for the face card "Jack" with a value of 11.
     */
    public static final int JACK = 11;

    /**
     * Constant for the face card "Queen" with a value of 12.
     */
    public static final int QUEEN = 12;

    /**
     * Constant for the face card "King" with a value of 13.
     */
    public static final int KING = 13;

    /**
     * Returns a numerical value corresponding to the given suit.
     *
     * @param n The suit of the card as a string.
     * @return An integer representing the suit: 4 for Picas, 3 for Corazones, 2 for Treboles, 1 for Diamantes, and 0 for any other value or null.
     */
    public static int getN(String n) {

        if (n == null) {
            return 0;
        }

        switch (n) {
            case PICAS: return 4;
            case CORAZONES: return 3;
            case TREBOLES: return 2;
            case DIAMANTES: return 1;
            default: return 0;
        }
    }
}
