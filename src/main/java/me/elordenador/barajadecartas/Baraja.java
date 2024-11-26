package me.elordenador.barajadecartas;

import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;
import me.elordenador.barajadecartas.tipos.Alemana;
import me.elordenador.barajadecartas.tipos.Espanola;

public class Baraja {

    public static void main(String[] args) throws NoEnLaBaraja {
        Baraja baraja = new Baraja(Tipo.ESPANOLA);
        Baraja baraja2 = new Baraja(Tipo.ALEMANA);
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
                String palo = Espanola.getPalo(i);
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
                    System.out.println(mazo[i][o].toString());
                }
            }
        }
        if (tipo == Tipo.ALEMANA) {
            for (int i = 0; i < 4; i++) {
                String palo = Alemana.getPalo(i);
                for (int o = 0; o < 9; o++) {
                    int n = o + 6;
                    mazo[i][o] = new Carta(n, palo, tipo);
                    System.out.println(mazo[i][o].toString());
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
}
