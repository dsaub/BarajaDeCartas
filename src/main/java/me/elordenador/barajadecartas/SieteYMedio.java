package me.elordenador.barajadecartas;

import java.util.ArrayList;
import java.util.Scanner;

public class SieteYMedio {
    private Player[] players;
    private Scanner sc = new Scanner(System.in);

    public void run() {
        boolean salida = false;

        int n = 0;
        System.out.println("Dime un numero de jugadores: ");
        try {
            n = sc.nextInt(); sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error al introducir el numero de jugadores");
            System.exit(1);
        }

        players = new Player[n];

        for (int i = 0; i < n; i++) {
            salida = false;
            while(!salida) {
                try {
                    System.out.println("Dime el nombre del jugador " + (i + 1) + ": ");
                    String name = sc.nextLine();
                    if (name.equals("")) {
                        System.out.println("El nombre no puede estar vacio");
                    } else {
                        players[i] = new Player(name);
                        salida = true;
                    }
                } catch (Exception e) {
                    System.out.println("Ha ocurrido un error al introducir el nombre del jugador");
                }

            }
        }

        Baraja baraja;
        System.out.println("Elija un tipo de baraja: ");
        System.out.println("1. Española");
        System.out.println("2. Española Extendida");
        System.out.println("3. Alemana");
        System.out.println("4. Poker");

        try {
            int tipo = sc.nextInt(); sc.nextLine();
            switch (tipo) {
                case 1: baraja = new Baraja(Tipo.ESPANOLA); break;
                case 2: baraja = new Baraja(Tipo.ESPANOLA_EXTENDIDA); break;
                case 3: baraja = new Baraja(Tipo.ALEMANA); break;
                case 4: baraja = new Baraja(Tipo.POKER); break;
                default: baraja = new Baraja(Tipo.ESPANOLA); break;
            }
        } catch (Exception e) {
            System.out.println("Error al introducir el tipo de baraja, Seleccionando la española");
            baraja = new Baraja(Tipo.ESPANOLA);
        }


        salida = false;

        // Inicio del bucle del juego
        int posTurno = 0;
        while (!salida) {
            Player turno = players[posTurno];

            if (turno != null) {
                System.out.println("Turno de " + turno.getPlayername());
                System.out.println("Puntos: " + turno.getPoints());


                Carta carta = baraja.siguiente();
                double puntosQueDa;
                if (carta.getTipo() == Tipo.ESPANOLA && carta.getValor() >= 10) {
                    puntosQueDa = 0.5;

                } else if (carta.getTipo() == Tipo.ESPANOLA_EXTENDIDA && carta.getValor() >= 10) {
                    puntosQueDa = 0.5;

                } else if (carta.getTipo() == Tipo.ALEMANA && carta.getValor() >= 11) {
                    puntosQueDa = 0.5;

                } else if (carta.getTipo() == Tipo.POKER && carta.getValor() >= 11) {
                    puntosQueDa = 0.5;

                } else {
                    puntosQueDa = carta.getValor();
                }


                System.out.println("Carta: " + carta.getValor() + " de " + carta.getPalo());
                System.out.println("Puntos que da: " + puntosQueDa);

                turno.setPoints(turno.getPoints() + puntosQueDa);

                System.out.println("Total de puntos: " + turno.getPoints());
                System.out.println("¿Te deseas rajar? [y:N]: ");
                try {
                    String rajar = sc.nextLine().toLowerCase();
                    if (rajar.equals("y")) {
                        System.out.println("El jugador " + turno.getPlayername() + " se ha rajado");
                        players[posTurno] = null;
                    }
                } catch (Exception e) {
                    System.out.println("Error al introducir la respuesta, no te rajas");
                }

                if (turno.getPoints() >= 7.5) {
                    System.out.println("El jugador " + turno.getPlayername() + " ha ganado");
                    salida = true;
                }

            } else {
                System.out.println("El usuario " + turno.getPlayername() + " se ha rajado de antes...");
            }


            posTurno += 1;
            if (posTurno >= players.length) {
                posTurno = 0;
            }



        }


    }
}
