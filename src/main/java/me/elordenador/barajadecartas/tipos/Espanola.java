package me.elordenador.barajadecartas.tipos;
/**
 * Represents the Spanish deck of cards with constants for suits and face cards.
 */
public class Espanola {
    /**
     * Constant for the suit "Oros".
     */
    public static final String OROS = "Oros";
    /**
     * Constant for the suit "Bastos".
     */
    public static final String BASTOS = "Bastos";
    /**
     * Constant for the suit "Espadas".
     */
    public static final String ESPADAS = "Espadas";
    /**
     * Constant for the suit "Copas".
     */
    public static final String COPAS = "Copas";

    /**
     * Constant for the face card "Sota" with a value of 10.
     */
    public static final int SOTA = 10;
    /**
     * Constant for the face card "Caballo" with a value of 11.
     */
    public static final int CABALLO = 11;
    /**
     * Constant for the face card "Rey" with a value of 12.
     */
    public static final int REY = 12;

    /**
     * Returns a numerical value corresponding to the given suit.
     *
     * @param n The suit of the card as a string.
     * @return An integer representing the suit: 4 for Oros, 3 for Bastos, 2 for Espadas, 1 for Copas, and 0 for any other value.
     */
    public static int getN(String n) {

        if (n == null) {
            return 0;
        }

        switch (n) {
            case OROS: return 4;
            case BASTOS: return 3;
            case ESPADAS: return 2;
            case COPAS: return 1;
            default: return 0;
        }
    }

    /**
     * Returns the suit of the card as a string corresponding to the given numerical value.
     *
     * @param i The numerical value of the suit.
     * @return A string representing the suit: "Oros" for 4, "Bastos" for 3, "Espadas" for 2, "Copas" for 1, and null for any other value.
     */
    public static String getPalo(int i) {
        switch (i) {
            case 4: return OROS;
            case 3: return BASTOS;
            case 2: return ESPADAS;
            case 1: return COPAS;
            default: return null;
        }
    }
}
