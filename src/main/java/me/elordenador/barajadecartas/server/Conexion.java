package me.elordenador.barajadecartas.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Conexion {
    private final int puerto;
    private ServerSocket ss;
    private Socket cs;
    private DataOutputStream salidaServidor, salidaCliente;
    private ArrayList<ServThread> clientes = new ArrayList<ServThread>();
    public Conexion(int puerto) {
        this.puerto = puerto;

    }

    public void initialize() throws IOException {
        ss = new ServerSocket(puerto);
        cs = new Socket();
        System.out.println("[+] Waiting for connections...");
        boolean salida = false;
        while (!salida) {
            cs = ss.accept();

            ServThread cliente = new ServThread(cs, SieteYMedioServer.instance);
            cliente.start();
            clientes.add(cliente);
        }


    }
}
