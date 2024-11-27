package me.elordenador.barajadecartas;

import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;
import me.elordenador.barajadecartas.tipos.Alemana;
import me.elordenador.barajadecartas.tipos.Espanola;
import me.elordenador.barajadecartas.tipos.Poker;

import java.util.Arrays;

public class Baraja {

    public static void main(String[] args) throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA);
        Baraja baraja2 = new Baraja(Tipo.ALEMANA);
        Baraja baraja3 = new Baraja(Tipo.POKER);
        int n_cartas = baraja3.numCartas();
        System.out.println(n_cartas);
    }

    /**
     * Counts the total number of cards in the deck (mazo).
     *
     * @return The total number of cards in the deck.
     */
    public int numCartas() {
        int count = 0;
        for (int i = 0; i < mazo.length; i++) {
            for (int j = 0; j < mazo[i].length; j++) {
                if (mazo[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * Represents the type of deck of cards.
     */
    private Carta[][] mazo;

    /**
     * Represents the type of deck of cards.
     */
    private Tipo tipo;

    /**
     * Constructor for the Baraja class.
     *
     * @param tipo The type of deck of cards.
     * @throws NoEnLaBaraja if the deck type is not recognized.
     */
    public Baraja(Tipo tipo) throws NoEnLaBaraja {
        this.tipo = tipo;
        initializeArray();
        reiniciar();
    }

    /**
     * Initializes the deck of cards (mazo) based on the type of deck.
     * @throws NoEnLaBaraja
     */
    public void reiniciar() throws NoEnLaBaraja {
        if (tipo == Tipo.ESPANOLA) {
            for (int i = 0; i < 4; i++) {
                String palo = Espanola.getPalo(i+1);
                for (int o = 0; o < 10; o++) {
                    int n = o + 1;
                    if (o >= 7) {
                        n = o - 7 + 10;

                    }
                    mazo[i][o] = new Carta(n, palo, tipo);
                }
            }
        }
        if (tipo == Tipo.ESPANOLA_EXTENDIDA) {
            for (int i = 0; i < 4; i++) {
                String palo = Espanola.getPalo(i);
                for (int o = 0; o < 12; o++) {
                    int n = o + 1;
                    mazo[i][o] = new Carta(n, palo, tipo);
                }
            }
        }
        if (tipo == Tipo.ALEMANA) {
            for (int i = 0; i < 4; i++) {
                String palo = Alemana.getPalo(i+1);
                for (int o = 0; o < 9; o++) {
                    int n = o + 6;
                    mazo[i][o] = new Carta(n, palo, tipo);
                }
            }
        }

        if (tipo == Tipo.POKER) {
            for (int i = 0; i < 4; i++) {
                String palo = Poker.getPalo(i+1);
                for (int o = 0; o < 13; o++) {
                    mazo[i][o] = new Carta(o+1, palo, tipo); // 1-13
                }
            }
        }
    }

    /**
     * Initializes the deck of cards (mazo) based on the type of deck.
     */
    private void initializeArray() {
        if (tipo == Tipo.ESPANOLA) {
            mazo = new Carta[4][10];
        }
        if (tipo == Tipo.ALEMANA) {
            mazo = new Carta[4][9];
        }
        if (tipo == Tipo.POKER) {
            mazo = new Carta[4][13];
        }
        if (tipo == Tipo.ESPANOLA_EXTENDIDA) {
            mazo = new Carta[4][12];
        }
    }

    /**
     * Returns the deck of cards (mazo) as a 2D array.
     *
     * @return A 2D array of Carta objects representing the deck of cards.
     */
    public Carta[][] getMazo() {
        return mazo;
    }

    /**
     * Shuffles the deck of cards (mazo) by randomly rearranging the cards.
     * This method creates a new shuffled deck and replaces the current deck with it.
     */
    public void barajar() {
        Carta[][] nuevomazo = new Carta[mazo.length][mazo[0].length];
        for (int i = 0; i < mazo.length; i++) {
            for (int j = 0; j < mazo[i].length; j++) {
                boolean salida = false;
                while (!salida) {
                    int x = (int) (Math.random() * mazo.length);
                    int y = (int) (Math.random() * mazo[x].length);

                    if (nuevomazo[x][y] == null) {
                        nuevomazo[x][y] = mazo[i][j];
                        salida = true;
                    }
                }
            }
        }

        for (int i = 0; i < mazo.length; i++) {
            for (int j = 0; j < mazo[i].length; j++) {
                mazo[i][j] = nuevomazo[i][j];
            }
        }
    }

    /**
     * Retrieves the next card from the deck (mazo) and removes it from the deck.
     *
     * @return The next Carta object from the deck, or null if the deck is empty.
     */
    public Carta siguiente() {
        Carta carta = null;
        int contadorx = 0;
        int contadory = 0;
        boolean encontrado = false;
        while (!encontrado) {
            if (mazo[contadorx][contadory] != null) {
                carta = mazo[contadorx][contadory];
                mazo[contadorx][contadory] = null;
                encontrado = true;
            } else {
                contadory++;
                if (contadory >= mazo[contadorx].length) {
                    contadory = 0;
                    contadorx++;
                }
                if (contadorx >= mazo.length) {
                    break;
                }
            }
        }

        return carta;

    }

    /**
     * Returns the deck of cards (mazo) as a 1D array.
     *
     * @return An array of Carta objects representing the deck of cards.
     */
    public Carta[] getBaraja() {
        Carta[] baraja = new Carta[numCartas()];
        int contador = 0;
        for (int i = 0; i < mazo.length; i++) {
            for (int j = 0; j < mazo[i].length; j++) {
                if (mazo[i][j] != null) {
                    baraja[contador] = mazo[i][j];
                    contador++;
                }
            }
        }

        return baraja;
    }

    /**
     * Returns a string representation of the deck of cards (mazo).
     *
     * @return A string representation of the deck of cards.
     */
    public String toString() {
        Carta[] newMazo = getBaraja();
        return Arrays.toString(newMazo);
    }

}
