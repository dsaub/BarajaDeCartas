package me.elordenador.barajadecartas.server;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class Admin {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream input = new FileInputStream(new File("connection.yml"));
        Yaml yaml = new Yaml();
        Map map = (Map) yaml.load(input);
        String ADDRESS = (String) map.get("address");
        String USER = (String) map.get("user");
        String PASSWORD = (String) map.get("password");
        int PORT = (int) map.get("port");
        String DATABASE = (String) map.get("database");

        String connectionUrl = "jdbc:mysql://" + ADDRESS + ":" + PORT + "/" + DATABASE;
        try (Connection connection = DriverManager.getConnection(connectionUrl, USER, PASSWORD)) {
            System.out.println("Connected to database.");
            System.out.println("Options :");
            System.out.println("1. Print Users");
            System.out.println("2. Create User");
            System.out.println("3. Delete User");

            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt(); sc.nextLine();

            switch (option) {
                case 1:
                    printUsers(connection);
                    break;
                case 2:
                    createUser(connection);
                    break;
                case 3:
                    deleteUser(connection);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            sc.close();





        } catch (SQLException e) {
            e.printStackTrace();
        
    }
}

    private static void deleteUser(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the id of the user: ");
        int id = sc.nextInt(); sc.nextLine();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM User WHERE id = ?");
            statement.setInt(1, id);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.commit();
        sc.close();
    }

    private static void createUser(Connection connection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the username: ");
        String name = sc.nextLine();
        System.out.print("Enter the password: ");
        String password = sc.nextLine();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            password = hexString.toString();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)");
            statement.setString(1, name);
            statement.setString(2, password);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.commit();
        sc.close();
    }

    private static void printUsers(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM User");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id") + ". " + rs.getString("username"));
        }
    }
    }
