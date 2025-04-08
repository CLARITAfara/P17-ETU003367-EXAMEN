package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String url = "jdbc:mysql://localhost:3306/db_s2_ETU003367";
            String user = "ETU003367";  
            String password = "AGupCdXf";  
            
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données", e);
        }
    }
}