package com.example.cafeteria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    private static final String URL =
            "jdbc:postgresql://bdyu262pdbqzfv5m5pst-postgresql.services.clever-cloud.com:5432/bdyu262pdbqzfv5m5pst?sslmode=require";

    private static final String USER = "umhv4lmzaovwvp1qsvat";
    private static final String PASSWORD = "Vcmlntpv2LIZvfJwUgsjpzLTCEdHMY";

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            System.out.println("BD no disponible: " + e.getMessage());
            return null;
        }
    }
 }


