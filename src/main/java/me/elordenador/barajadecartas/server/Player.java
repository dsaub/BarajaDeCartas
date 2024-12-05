package me.elordenador.barajadecartas.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Player {
    private String player;
    private Socket socket;

    private boolean rajado, muerto;

    private double puntos = 0;

    private BufferedReader entrada;
    private DataOutputStream salida;

    public Player(String player, Socket socket) {
        rajado = false;
        muerto = false;


        this.player = player;
        this.socket = socket;

        try {
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void send(String message) {
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

    public void rajar() {
        rajado = true;
    }

    public boolean getRajado() {
        return rajado;
    }

    public void matar() {
        muerto = true;
    }

    public boolean getMuerto() {
        return muerto;
    }

    public void addPuntos(double puntos) {
        this.puntos += puntos;
        if (puntos > 7.5) {
            matar();
        }
    }

    public double getPuntos() {
        return puntos;
    }


    public String getName() {
        // TODO Auto-generated method stub
        return player;
    }

    public void sendMSG(String msg) {
        send("MSG:"+msg);
    }
}
