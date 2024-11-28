package me.elordenador.barajadecartas.client;

import me.elordenador.barajadecartas.Cypher;

import java.io.*;
import java.net.Socket;
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
            send(user + ":" + Cypher.toSHA256(pass));
            message = receive();

        }
        if (message.equals("AUTHENTICATED")) {
            System.out.println("Conectado correctamente. Bienvenido!");
        } else {
            System.out.println("Error al autenticar. Saliendo...");
            System.exit(1);
        }






        socket.close();
    }

    public String receive() {
        try {
            boolean salida = false;
            String retur = null;
            while (!salida) {
                retur = entrada.readLine();
                if (retur != null && !retur.equals("\n")) {
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
}
