package me.elordenador.barajadecartas;

import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;
import me.elordenador.barajadecartas.tipos.Alemana;
import me.elordenador.barajadecartas.tipos.Espanola;
import me.elordenador.barajadecartas.tipos.Poker;

public class Baraja {

    public static void main(String[] args) throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA);
        Baraja baraja2 = new Baraja(Tipo.ALEMANA);
        Baraja baraja3 = new Baraja(Tipo.POKER);
        int n_cartas = baraja3.count();
        System.out.println(n_cartas);
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < mazo.length; i++) {
            for (int j = 0; j < mazo[i].length; j++) {
                count++;
            }
        }
        return count;
    }

    private Carta[][] mazo;
    private Tipo tipo;
    public Baraja(Tipo tipo) throws NoEnLaBaraja {
        this.tipo = tipo;
        initializeArray();
        initializeCards();
    }

    private void initializeCards() throws NoEnLaBaraja {
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
                for (int o = 0; o < 12; o++) {
                    mazo[i][o] = new Carta(o+1, palo, tipo); // 1-13
                }
            }
        }
    }


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
}
