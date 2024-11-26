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
     * Constructor for the Carta class.
     *
     * @param valor The value of the card.
     * @param palo The suit of the card.
     * @param tipo The type of the card.
     * @throws NoEnLaBaraja if the card is not in the deck based on its value and type.
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
     * Sets the value of the card
     * @param valor value to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Gets the value of the card
     * @return card's value
     */
    public int getValor() {
        return valor;
    }

    /**
     * Sets the suit of the card
     * @param palo the suit to set
     */
    public void setPalo(String palo) {
        this.palo = palo;
    }

    /**
     * Gets the suit of the card
     * @return Suit's name
     */
    public String getPalo() {
        return palo;
    }

    /**
     * Sets the type of the card
     * @param tipo The type to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;

    }

    /**
     * Gets the type of the card
     * @return the type
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Returns the string of the card
     * @return the String
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
     * Checks if a card is equal to this card
     * @param carta The card to compare
     * @return if the cards are equal
     */
    public boolean equals(Carta carta) {
        return (valor == carta.getValor() && palo.equals(carta.getPalo()) && tipo == carta.getTipo());
    }

    /**
     * Clones the current Carta object.
     *
     * @return A new Carta object with the same properties as the current one.
     * @throws RuntimeException if the Carta cannot be created due to a NoEnLaBaraja exception.
     */
    public Carta clone() {
        try {
            return new Carta(valor, palo, tipo);
        } catch (NoEnLaBaraja e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Checks if the value of this card is equal to the value of the given card.
     *
     * @param carta The card to compare with.
     * @return true if the values are equal, false otherwise.
     */
    public boolean equalsNumber(Carta carta) {
        if (carta == null) {
            return false;
        }
        return (valor == carta.getValor());
    }

    /**
     * Checks if the suit of this card is equal to the suit of the given card.
     *
     * @param carta The card to compare with.
     * @return true if the suits are equal, false otherwise.
     */
    public boolean equalsPalo(Carta carta) {
        if (carta == null) {
            return false;
        }
        return (palo.equals(carta.getPalo()));
    }
}
