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

    private Player player;

    private String mensajeServidor;
    SieteYMedioServer instance;

    private Integer connectionId = null;

    /**
     * Creates the thread for the player
     * @param socket The socket of the player
     */
    public ServThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sets the instance
     */
    public void setInstance(SieteYMedioServer instance) {
        this.instance = instance;
    }

    /**
     * Runs the thread
     */
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

        String credentials = null;
        credentials = receive();

        credentials = credentials.substring(1, credentials.length());

        System.out.println("[+] Got credentials from user " + credentials.split("\\:")[0] + ", validating...");
        String username = credentials.split("\\:")[0];
        String password = credentials.split("\\:")[1];
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
                player = new Player(username, socket);

                gameloop();
            } else {
                System.out.println("[+] User " + username + " not found.");
                send("NOT_AUTHENTICATED");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    private void gameloop() {
        System.out.print("[+] " + player.getName() + "Is now in the loop, Waiting for commands...");
        // Waiting for command...
        String command = receive();
        if (command.startsWith("CREATE_GAME")) {
            Game game = new Game();
            player.send(Integer.toString(game.getCode()));

            game.addPlayer(player);
            Games.instance.addClient(game);
            command = receive();
            if (command.startsWith("START_GAME")) {
                game.run();
                try {
                    game.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                
            }
        }
        
        if (command.startsWith("JOIN_GAME")) {
            String[] commandSplit = command.split("\\:");
            Game game = Games.instance.find(Integer.parseInt(commandSplit[1]));
            if (game != null) {
                game.addPlayer(player);
                while (!game.isAlive()) {
                } // Esperar a que el thread ejecute
                try {
                    game.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            


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
            System.out.println(retur.trim());
            return retur.trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
