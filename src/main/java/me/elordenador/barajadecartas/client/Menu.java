package me.elordenador.barajadecartas.client;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a Baraja de Cartas");
        System.out.println("1. Jugar a Siete y Medio");
        System.out.println("2. Jugar a Siete y Medio Multiplayer");
        System.out.println("Cualquier otra opcion. Salir");


        try {
            int opcion = sc.nextInt(); sc.nextLine();
            switch (opcion) {
                case 1: new SieteYMedio().run(); break;
                case 2: new SieteMultiplayer().run(); break;
                default: System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
