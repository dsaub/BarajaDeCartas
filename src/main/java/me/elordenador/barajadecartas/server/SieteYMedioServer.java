package me.elordenador.barajadecartas.server;

import me.elordenador.barajadecartas.client.SieteYMedio;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class SieteYMedioServer {

    public final String HOST, USERNAME, PASSWORD, DATABASE;
    public final int PORT;
    private Connection connection;

    public static SieteYMedioServer instance;
    public static void main(String[] args) {
        try {
            instance = new SieteYMedioServer();
            instance.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException {
        Conexion conexion = new Conexion(5007);

        conexion.initialize();
    }

    public SieteYMedioServer() throws IOException {
        System.out.println("[+] Getting connection details...");
        InputStream stream = new FileInputStream(new File("connection.yml"));
        Yaml yaml = new Yaml();
        Map map = (Map) yaml.load(stream);
        HOST = (String) map.get("address");
        USERNAME = (String) map.get("user");
        PASSWORD = (String) map.get("password");
        DATABASE = (String) map.get("database");
        PORT = (int) map.get("port");


        String connectionUrl = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false&keepAlive=true";
        try {
            System.out.println("[+] Connecting to database...");
            connection = java.sql.DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("[-] Error connecting to database.");
            throw new RuntimeException(e);
        }
        System.out.println("[+] Connected to database. Starting Server...");

    }

    public Connection getConnection() {
        return connection;
    }
}
