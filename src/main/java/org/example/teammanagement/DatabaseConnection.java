package org.example.teammanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:database.db"; // Percorso del tuo file SQLite

    // per ottenere la connessione
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }


    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Connessione al database riuscita!");
            }
        } catch (SQLException e) {
            System.out.println("Errore nella connessione al database: " + e.getMessage());
        }
    }
}
