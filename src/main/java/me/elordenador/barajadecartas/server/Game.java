package me.elordenador.barajadecartas.server;

import me.elordenador.barajadecartas.Carta;
import me.elordenador.barajadecartas.Tipo;
import me.elordenador.barajadecartas.Baraja;

import java.util.ArrayList;
public class Game extends Thread {
    private ArrayList<Player> players = new ArrayList<Player>();
    private int code = Games.instance.generateCode();
    public Game() {
        System.out.println("[+] Created game " + code);
    }
    public void addPlayer(Player player) {
        players.add(player);
        System.out.println("[+] Player " + player.getName() + " joined game " + code);
        System.out.print("  Now the players are: ");
        for (Player player1 : players) {
            System.out.print(player1.getName() + ", ");
        }
        System.out.println();
        
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
        sendMSGtoAll("=== INICIO DEL JUEGO ===");
        int contador = 0;
        while (!salida) {
            Player player = getPlayer(contador);
            sendMSGtoAll("=== Turno del jugador " + player.getName() + " ===");
            if (!player.getRajado() && !player.getMuerto()) {
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

                sendMSGtoAll("! El jugador recibe la carta " + carta.toString() + " !");
                player.send("TURN:" + carta.toString() + ":" + player.getPuntos());
                String response = player.receive();
                if (response.equals("Y")) {
                    sendMSGtoAll("! El jugador " + player.getName() + " se ha rajado !");
                    player.rajar();
                }

                if (player.getPuntos() > 7.5) {
                    sendMSGtoAll("! El jugador " + player.getName() + " ha muerto !, se ha pasado por " + (player.getPuntos() - 7.5) + " puntos !");
                    player.matar();
                }

            }
            contador += 1;
            if (contador >= getPlayerCount()) {
                contador = 0;
            }




        }

        
    }

    public int getCode() {
        return code;
    }

    public void sendMSGtoAll(String msg) {
        for (Player player : players) {
            player.sendMSG(msg);
        }
    }
}
