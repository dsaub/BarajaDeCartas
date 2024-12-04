package me.elordenador.barajadecartas.server;

import java.util.ArrayList;

public class Games {
    private ArrayList<Game> clientes;
    public static final Games instance = new Games();

    public Games() {
        this.clientes = new ArrayList<Game>();
    }

    public void addClient(Game cliente) {
        clientes.add(cliente);
    }
}
