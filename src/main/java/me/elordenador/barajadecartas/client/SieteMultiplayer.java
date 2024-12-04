package me.elordenador.barajadecartas.client;

import me.elordenador.barajadecartas.Cypher;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SieteMultiplayer {
    private Scanner sc = new Scanner(System.in);

    private String HOST;
    private int PORT;

    private Socket socket;

    private DataOutputStream salidaServidor;
    private BufferedReader entrada;

    public void connect(String HOST, int PORT) throws IOException {
        socket = new Socket(HOST, PORT);
    }
    public void run() throws IOException {

        try {
            System.out.println("IP: ");
            HOST = sc.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("PORT: ");
            PORT = sc.nextInt(); sc.nextLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        connect(HOST, PORT);
        salidaServidor = new DataOutputStream(socket.getOutputStream());
        send("CONNECTED");
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String message = receive();
        System.out.println(message);
        if (message.equals("REGISTER")) {
            System.out.println("Este servidor requiere de autenticación, por favor, inicie sesión para continuar.");
            System.out.print("Usuario: ");
            String user = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();
            System.out.println(user + ":" + Cypher.toSHA256(pass));
            send(user + ":" + Cypher.toSHA256(pass));


            message = receive();
            System.out.println(message);

        }
        if (message.equals("AUTHENTICATED")) {
            System.out.println("Conectado correctamente. Bienvenido!");
        } else if (message.equals("NOT_AUTHENTICATED")) {
            System.out.println("El usuario o la contraseña es incorrecto. Saliendo...");
            System.exit(1);
        } else {
            System.out.println("Error al autenticar. Saliendo...");
            System.exit(1);
        }


        menu();
        
        
        
        
        
        
        socket.close();
        
    }
        
    private void menu() {
        // TODO Auto-generated method stub
        System.out.println("// Menu // \n" +
                            "1. Crear una sala \n" +
                            "2. Unirse a una sala \n");
        int option = 0;
        try {
            option = sc.nextInt(); sc.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("ERROR: Is not an integer. Exiting");
        }
        

        switch (option) {
            case 1: crear_sala(); break;
            case 2: unirse_sala(); break;
            default: System.exit(0);
        }
    }

    private void crear_sala() {
        send("CREATE_GAME");
        String code = receive();
        System.out.println("Press Enter to start game, your code is "+ code);
        sc.nextLine();
        send("START_GAME");
        game();
    }

    private void unirse_sala() {
        int code = 0;
        try {
            code = sc.nextInt();
        } catch (InputMismatchException e)  {
            System.err.println("ERROR: No es un numero");
            System.exit(51);
        }


        sc.nextLine();

        send("JOIN_GAME:" + code);

        game();
    }
    public String receive() {
        try {
            boolean salida = false;
            String retur = null;
            while (!salida) {
                retur = entrada.readLine();
                retur = retur.trim();
                if (retur != null && !retur.equals("\n") && !retur.equals("")) {
                    salida = true;
                }
            }
            return retur.trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message) {
        try {
            salidaServidor.writeUTF(message);
            salidaServidor.writeUTF("\n");
            salidaServidor.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void game() {
        boolean salida = false;
        String respuesta = null;
        while (!salida) {
            respuesta = receive();
            salida = respuesta.equals("START");
        }


        salida = false;
        while (!salida) {
            respuesta = receive();
            String[] splitedRespuesta = respuesta.split("\\:");
            if (splitedRespuesta[0].equals("TURN")) {
                System.out.println("Has recibido la carta " + splitedRespuesta[1]);
                System.out.println("Ahora tienes " + splitedRespuesta[2] + " puntos");
                System.out.println("¿Te quieres rajar? [y:N]");
                String rajada = sc.nextLine();
                send(rajada);
            }
        }
    }
}
