package me.elordenador.barajadecartas.server;

import java.util.ArrayList;

import me.elordenador.valueutils.NumberUtils;

public class Games {
    private ArrayList<Game> clientes;
    public static final Games instance = new Games();

    public Games() {
        this.clientes = new ArrayList<Game>();
    }

    public void addClient(Game cliente) {
        clientes.add(cliente);
    }

    public int generateCode() {
        int DIGITS = 6;

        boolean salida = false;
        int code = 0;
        while (!salida) {
            code = NumberUtils.generateRandomNumberOfDigits(DIGITS);
            boolean encontrado = false;
            for (Game game : clientes) {
                if (game.getCode() == code) {
                    encontrado = true;
                }
            }

            salida = !encontrado;
        }

        return code;
    }

    public Game find(int int1) {
        Game game = null;
        System.out.println("[+] Searching game " + int1);
        for (Game game1 : clientes) {
            if (game1.getCode() == int1) {
                game = game1;
            }
        }
        if (game != null) {
            System.out.println("[+] Game found");
        } else {
            System.out.println("[+] We cannot find a game");
        }
        
        return game;
    }
}
