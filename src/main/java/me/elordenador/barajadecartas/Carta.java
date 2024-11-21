package me.elordenador.barajadecartas;

import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;

/**
 * Clase para manejar las cartas de los juegos
 * @author Daniel Sánchez Úbeda
 * @version 1.0
 */
public class Carta {
    private int valor;
    private String palo;
    private Tipo tipo;

    /**
     * Constructor para crear la carta
     * @param valor El numero de la carta, puedes usar la clase Espanola/Alemana/Poker para servir el valor no-numerico mas facil.
     * @param palo El palo de la carta. Puedes usar la clase Espanola/Alemana/Poker para servir el palo mas facil.
     * @param tipo El tipo de carta, debe de ser del Enum me.elordenador.barajadecartas.Tipo.
     * @throws NoEnLaBaraja Si no existe una carta de ESA baraja.
     */
    public Carta(int valor, String palo, Tipo tipo) throws NoEnLaBaraja {
        if (valor <= 0) {
            throw new NoEnLaBaraja("La carta " + valor + " no esta en la baraja");
        }
        if (tipo == Tipo.ALEMANA && valor < 6) {
            throw new NoEnLaBaraja("La carta " + valor + " no esta en la baraja");
        }
        if (tipo == Tipo.ESPANOLA && valor >= 8 && valor <= 9) {
            throw new NoEnLaBaraja("La carta " + valor + " no esta en la baraja");
        }
        if(tipo == Tipo.ALEMANA && valor > 9) {
            throw new NoEnLaBaraja("La carta " + valor + " no esta en la baraja");
        }
        if ((tipo == Tipo.ESPANOLA_EXTENDIDA || tipo == Tipo.ESPANOLA) && valor > 12) {
            throw new NoEnLaBaraja("La carta " + valor + " no esta en la baraja");
        }
        if (tipo == Tipo.POKER && valor > 13) {
            throw new NoEnLaBaraja("La carta " + valor + " no esta en la baraja");
        }



        this.valor = valor;
        this.palo = palo;
        this.tipo = tipo;
    }

    /**
     * Establece el valor de la carta.
     * @param valor El numero de la carta
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Devuelve el valor de la carta.
     * @return
     */
    public int getValor() {
        return valor;
    }

    /**
     * Establece el palo de la carta.
     * @param palo El palo a establecer.
     */
    public void setPalo(String palo) {
        this.palo = palo;
    }

    /**
     * Consigue el palo actual, Devuelve un String.
     * @return El String del Palo
     */
    public String getPalo() {
        return palo;
    }

    /**
     * Establece el tipo de la carta.
     * @param tipo El Tipo de la Carta
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;

    }

    /**
     * Consigue el tipo de la carta
     * @return el tipo de la carta
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Devuelve el string de la carta (Valor/Palo)
     * @return El String
     */
    public String toString() {
        String string = "";
        if (tipo == Tipo.ESPANOLA) {
            switch (valor) {
                case 10: string += "Sota "; break;
                case 11: string += "Caballo "; break;
                case 12: string += "Rey "; break;
                default: string += valor + " "; break;
            }

            string += palo;
        } else if (tipo == Tipo.POKER) {
            switch (valor) {
                case 11: string += "J "; break;
                case 12: string += "Q "; break;
                case 13: string += "K "; break;
                default: string += valor + " "; break;
            }

            string += palo;
        } else if (tipo == Tipo.ALEMANA) {
            switch (valor) {
                case 11: string += "Campesino "; break;
                case 12: string += "Dama "; break;
                case 13: string += "Rey "; break;
                case 14: string += "As "; break;
            }
            string += palo;
        }
        return string;
    }

    /**
     * Comprueba si una carta es igual a esta carta.
     * @param carta La carta a comparar
     * @return si es igual o no.
     */
    public boolean equals(Carta carta) {
        return (valor == carta.getValor() && palo.equals(carta.getPalo()) && tipo == carta.getTipo());
    }
}
