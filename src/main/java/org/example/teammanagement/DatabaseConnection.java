package org.example.teammanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // verificare la password durante il login con ritorno di intero per tipologia utente
    public static int authenticateUser(String username, String plainPassword) {
        String[] queries = {
                "SELECT Password FROM Admins WHERE Username = ?",
                "SELECT Password FROM Allenatori WHERE Username = ?",
                "SELECT Password FROM Giocatori WHERE Username = ?"
        };

        for (int i = 0; i < queries.length; i++) {
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(queries[i])) {

                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    // verifica la password
                    if (PasswordUtils.verifyPassword(plainPassword, hashedPassword)) {
                        return i;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
    //ritorna la squadra associata ad un admin
    public static String getSquadraByAdmin(String username) {
        String query = "SELECT Squadra FROM Admins WHERE Username = ?";
        String squadra = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                squadra = rs.getString("Squadra");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return squadra;
    }
    //ritorna i giocaotori che deve gestire un admin
    public static List<Giocatore> getGiocatoriBySquadra(String squadra) {
        String query = "SELECT * FROM Giocatori WHERE Squadra = ?";
        List<Giocatore> giocatori = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, squadra);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Giocatore giocatore = new Giocatore();
                giocatore.setId(rs.getInt("ID"));
                giocatore.setNomeCognome(rs.getString("NomeCognome"));
                giocatore.setRuolo(rs.getString("Ruolo"));
                giocatore.setEta(rs.getInt("Eta"));
                giocatore.setNumMaglia(rs.getInt("NumMaglia"));
                giocatore.setNazionalita(rs.getString("Nazionalità"));
                giocatore.setPiede(rs.getString("Piede"));
                giocatore.setStipendio(rs.getInt("Stipendio"));
                giocatore.setAnniContratto(rs.getInt("AnniContratto"));

                giocatori.add(giocatore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return giocatori;
    }
}

