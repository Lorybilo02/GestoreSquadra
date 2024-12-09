package org.example.teammanagement;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:database.db";

    // metodo per ottenere la connessione
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

    // Verificare la password durante il login   , ancora da fare
    public boolean login(String username, String plainPassword) {
        String query = "SELECT Password FROM Users WHERE Username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("Password");
                // verifica la corrispondenza della password hashata
                return PasswordUtils.verifyPassword(plainPassword, hashedPassword);
            }
        } catch (SQLException e) {
            System.out.println("Errore durante il login: " + e.getMessage());
        }

        return false; // login fallito
    }
}
