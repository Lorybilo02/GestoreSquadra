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
    //ritorna i giocatori che deve gestire un admin
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
                giocatore.setNome(rs.getString("Nome"));
                giocatore.setCognome(rs.getString("Cognome"));
                giocatore.setRuolo(rs.getString("Ruolo"));
                giocatore.setEta(rs.getInt("Eta"));
                giocatore.setNumMaglia(rs.getInt("NumMaglia"));
                giocatore.setNazionalita(rs.getString("Nazionalità"));
                giocatore.setPiede(rs.getString("Piede"));
                giocatore.setStipendio(rs.getInt("Stipendio"));
                giocatore.setAnniContratto(rs.getInt("AnniContratto"));
                giocatore.setGoal(rs.getInt("Goal"));
                giocatore.setAssist(rs.getInt("Assist"));
                giocatore.setMinutiGiocati(rs.getInt("MinutiGiocati"));
                giocatore.setIsTitolare(rs.getInt("IsTitolare"));

                giocatori.add(giocatore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return giocatori;
    }
    //funzione per aggiungere giocatori (per semplicità)
    public static boolean addGiocatore(String username, String password, String nome, String cognome,
                                       String ruolo, int eta, int numMaglia, String nazionalita,
                                       String piede, String squadra, int stipendio, int anniContratto, int goal, int assist, int minutiGiocati, int isTitolare) {
        String query = "INSERT INTO Giocatori (Username, Password, Nome, Cognome, Ruolo, Eta, NumMaglia, Nazionalità, Piede, Squadra, Stipendio, AnniContratto) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, PasswordUtils.hashPassword(password)); // crittografa la password
            pstmt.setString(3, nome);
            pstmt.setString(4, cognome);
            pstmt.setString(5, ruolo);
            pstmt.setInt(6, eta);
            pstmt.setInt(7, numMaglia);
            pstmt.setString(8, nazionalita);
            pstmt.setString(9, piede);
            pstmt.setString(10, squadra);
            pstmt.setInt(11, stipendio);
            pstmt.setInt(12, anniContratto);
            pstmt.setInt(13, goal);
            pstmt.setInt(14, assist);
            pstmt.setInt(15, minutiGiocati);
            pstmt.setInt(16, isTitolare);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

