package me.elordenador.barajadecartas.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServThread extends Thread {
    private Socket socket;
    private BufferedReader entrada;
    private DataOutputStream salida;

    private String mensajeServidor;
    SieteYMedioServer instance;

    public ServThread(Socket socket, SieteYMedioServer instance) {
        this.socket = socket;
        this.instance = instance;
    }

    @Override
    public void run() {
        System.out.println("[+] Client connected... " + socket.getInetAddress().toString());
        System.out.println("[+] Waiting for client response...");
        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mensajeServidor = receive();

        System.out.println("[+] Client response: " + mensajeServidor);
        System.out.println("[+] Asking for registration");
        try {
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        send("REGISTER");

        String credentials = receive();
        System.out.println(credentials);

        System.out.println("[+] Got credentials from user " + credentials.split("\\:")[0] + ", validating...");
        String username = credentials.split(":")[0];
        String password = credentials.split(":")[1];
        Connection connection = instance.getConnection();

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(2, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rs.next()) {
                System.out.println("[+] User " + username + " authenticated successfully.");
                send("AUTHENTICATED");
            } else {
                System.out.println("[+] User " + username + " not found.");
                send("NOT_AUTHENTICATED");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void send(String message) {
        try {
            salida.writeUTF(message);
            salida.writeUTF("\n");
            salida.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String receive() {
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
}
