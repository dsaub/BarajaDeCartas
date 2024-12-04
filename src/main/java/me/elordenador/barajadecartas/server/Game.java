package me.elordenador.barajadecartas.server;

import me.elordenador.barajadecartas.Carta;
import me.elordenador.barajadecartas.Tipo;
import me.elordenador.barajadecartas.Baraja;
import me.elordenador.valueutils.NumberUtils;

import java.util.ArrayList;
public class Game extends Thread {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int code = NumberUtils.generateRandomNumberOfDigits(6);

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public int getPlayerCount() {
        return players.size();
    }

    public void run() {
        boolean salida = false;

        Baraja baraja = new Baraja(Tipo.ESPANOLA);
        baraja.barajar();

        // Send Start method to all players
        for (Player player : players) {
            player.send("START");
        }

        int contador = 0;
        while (!salida) {
            Player player = getPlayer(contador);
            if (!player.getRajado() && !player.getMuerto()) {
                player.send("TURN");
                Carta carta = baraja.siguiente();

                if (carta.getTipo() == Tipo.ESPANOLA || carta.getTipo() == Tipo.ESPANOLA_EXTENDIDA) {
                    switch (carta.getValor()) {
                        case 10: player.addPuntos(0.5); break;
                        case 11: player.addPuntos(0.5); break;
                        case 12: player.addPuntos(0.5); break;
                        default: player.addPuntos(carta.getValor());
                    }
                } else if (carta.getTipo() == Tipo.POKER) {
                    switch (carta.getValor()) {
                        case 11: player.addPuntos(0.5); break;
                        case 12: player.addPuntos(0.5); break;
                        case 13: player.addPuntos(0.5); break;
                        default: player.addPuntos(carta.getValor());
                    }
                } else {
                    switch (carta.getValor()) {
                        case 11: player.addPuntos(0.5); break;
                        case 12: player.addPuntos(0.5); break;
                        case 13: player.addPuntos(0.5); break;
                        case 14: player.addPuntos(0.5); break;
                        default: player.addPuntos(carta.getValor());
                    }
                }


                player.send(carta.toString() + ":" + player.getPuntos());
                String response = player.receive();
                if (response.equals("N")) {
                    player.rajar();
                }
            }
            player.send("TURN");
            Carta carta = baraja.siguiente();
            player.send(carta.toString());
            String response = player.receive();
            if (response.equals("Y")) {

            }
        }
    }
}
