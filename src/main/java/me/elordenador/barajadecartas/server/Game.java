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
                String response = player.receive().toLowerCase();
                if (response.equals("y")) {
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


            // Checkeo de ganar o no ganar para jugadores

            // Un jugador ha llegado a 7.5 puntos
            for (Player player1 : players) {
                if (player1.getPuntos() == 7.5) {
                    sendMSGtoAll("--- GANADOR: " + player1.getName() + " ---");
                    salida = true;
                    for (Player player2 : players) {
                        player2.send("EXIT:0");
                        player2.disconnect();
                    }
                }
            }

            if (!salida) {
                // Continuaremos si la condición anterior ha funcionado

                // Checkeo de si todos los jugadores se han rajado
                int cantidadJugadores = players.size();
                int cantidadRajada = 0;
                for (Player player1 : players) {
                    if (player1.getRajado()) {
                        cantidadRajada += 1;
                    }
                }

                if (cantidadRajada == cantidadJugadores) {
                    // Se han rajado todos los jugadores, procediendo a comprobar cual es el que mas se acerca.

                    double maximoPuntos = 0;
                    Player jugadorLider = null; 

                    for (Player player1 : players) {
                        if (player1.getPuntos() > maximoPuntos && player1.getPuntos() <= 7.5) {
                            maximoPuntos = player1.getPuntos();
                            jugadorLider = player1;
                        }
                    }

                    sendMSGtoAll("--- GANADOR: " + jugadorLider.getName() + " --- ");
                    salida = true;
                    for (Player player1 : players) {
                        player1.send("EXIT:0");
                        player1.disconnect();
                    }
                }
            }

            if (!salida) {
                // Si los anteriores no se han ejecutado...
                int cantidadJugadores = players.size();
                int cantidadMuertos = 0;
                for (Player player1 : players) {
                    if (player1.getMuerto()) {
                        cantidadMuertos += 1;
                    }
                }

                if (cantidadMuertos == cantidadJugadores) {
                    double puntosJ = Double.MAX_VALUE;
                    Player jugadorLider = null;
                    for (Player player1 : players) {
                        if (player1.getPuntos() < puntosJ) {
                            puntosJ = player1.getPuntos();
                            jugadorLider = player1;
                        }
                    }
                    salida = true;

                    sendMSGtoAll("--- GANADOR: " + jugadorLider.getName() + " ---");
                    for (Player player1 : players) {
                        player1.send("EXIT:0");
                        player1.disconnect();
                    }
                }
            }

            if (!salida) {
                // No se han cumplido las anteriores, comprobando si la cantidad de jugadores estan muertos y/o rajados

                int cantidadJugadores = players.size();
                int cantidadMuertos = 0;
                int cantidadRajados = 0;
                for (Player player1 : players) {
                    if (player1.getMuerto()) cantidadMuertos++;
                    if (player1.getRajado()) cantidadRajados++;
                }

                if (cantidadJugadores == cantidadMuertos + cantidadRajados) {
                    sendMSGtoAll("¡Rajada completa!");
                    double maxPuntos = 0;
                    Player winningPlayer = null;
                    for (Player player2 : players) {
                        if (player2.getRajado()) {
                            if (player2.getPuntos() > maxPuntos && player2.getPuntos() <= 7.5) {
                                maxPuntos = player2.getPuntos();
                                winningPlayer = player2;
                            }
                        }

                        sendMSGtoAll("¡Ha ganado " + winningPlayer.getName() + " por estar mas cerca del 7 y medio");
                        salida = true;
                    }
                }

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
