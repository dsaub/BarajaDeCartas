package me.elordenador.barajadecartas;

import me.elordenador.barajadecartas.exceptions.NoEnLaBaraja;

public class Carta {
    private int valor;
    private String palo;
    private Tipo tipo;

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

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }
    public String getPalo() {
        return palo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;

    }
    public Tipo getTipo() {
        return tipo;
    }
}
