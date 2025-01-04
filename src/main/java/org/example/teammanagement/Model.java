package org.example.teammanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final String URL = "jdbc:sqlite:database.db";
    private static Model instance; // singleton
    private Connection connection;

    private Model() {
        try {
            this.connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Model getInstance() {
        if (instance == null) {
            synchronized (Model.class) {
                if (instance == null) {
                    instance = new Model();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // metodo per testare la connessione
    public void testConnection() {
        try {
            if (connection != null) {
                System.out.println("Connessione al database riuscita!");
            }
        } catch (Exception e) {
            System.out.println("Errore nella connessione al database: " + e.getMessage());
        }
    }

    // verificare la password durante il login con ritorno di intero per tipologia utente
    public int authenticateUser(String username, String plainPassword) {
        String[] queries = {
                "SELECT Password FROM Admins WHERE Username = ?",
                "SELECT Password FROM Allenatori WHERE Username = ?",
                "SELECT Password FROM Giocatori WHERE Username = ?"
        };

        for (int i = 0; i < queries.length; i++) {
            try (PreparedStatement pstmt = connection.prepareStatement(queries[i])) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("Password");
                    // Verifica la password
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

    // ritorna la squadra associata ad un admin
    public String getSquadraByAdmin(String username) {
        String query = "SELECT Squadra FROM Admins WHERE Username = ?";
        String squadra = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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

    // ritorna i giocatori che deve gestire un admin
    public List<Giocatore> getGiocatoriBySquadra(String squadra) {
        String query = "SELECT * FROM Giocatori WHERE Squadra = ?";
        List<Giocatore> giocatori = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
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
                giocatore.setIsTitolare(rs.getInt("isTitolare"));

                giocatori.add(giocatore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return giocatori;
    }

    //metodo per tornare i dati di un allenatore in base alla squadra
    public Allenatore getAllenatoreBySquadra(String squadra) {
        String query = "SELECT * FROM Allenatori WHERE Squadra = ?";
        Allenatore allenatore = null; // Inizializza fuori dal blocco try

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, squadra);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                allenatore = new Allenatore();
                allenatore.setId(rs.getInt("ID"));
                allenatore.setUsername(rs.getString("Username"));
                allenatore.setPassword(rs.getString("Password"));
                allenatore.setNome(rs.getString("Nome"));
                allenatore.setCognome(rs.getString("Cognome"));
                allenatore.setEta(rs.getInt("Eta"));
                allenatore.setStipendio(rs.getInt("Stipendio"));
                allenatore.setAnniContratto(rs.getInt("AnniContratto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allenatore;
    }














    // metodo per aggiungere giocatori (per semplicità)
    public boolean addGiocatore(String username, String password, String nome, String cognome,
                                String ruolo, int eta, int numMaglia, String nazionalita,
                                String piede, String squadra, int stipendio, int anniContratto,
                                int goal, int assist, int minutiGiocati, int isTitolare) {
        String query = "INSERT INTO Giocatori (Username, Password, Nome, Cognome, Ruolo, Eta, NumMaglia, Nazionalità, Piede, Squadra, Stipendio, AnniContratto, Goal, Assist, MinutiGiocati, isTitolare) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, PasswordUtils.hashPassword(password)); // Crittografa la password
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
            System.out.println("Giocatore aggiunto");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //metodo per aggiungere Admin (per semplicità)
    public boolean addAdmin(String username, String password,String squadra) {
        String query = "INSERT INTO Admin (Username, Password, Squadra) VALUES (?, ?, ?)";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, username);
            psmt.setString(2, PasswordUtils.hashPassword(password));
            psmt.setString(3,squadra);

            int rowsAffected = psmt.executeUpdate();
            System.out.println("Admin aggiunto");
            return rowsAffected > 0;
        }  catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //metodo per aggiungere Allenatore (per semplicità)
    public boolean addAllenatore(String username, String password,String Nome,String Cognome, String Eta, String Stipendio, String Squadra, String AnniContratto) {
        String query = "INSERT INTO ALLENATORI (Username, Password, Nome, Cognome, Eta, Stipendio, Squadra, AnniContratto)";
        try(PreparedStatement psmt = connection.prepareStatement(query)){
            psmt.setString(1, username);
            psmt.setString(2, PasswordUtils.hashPassword(password));
            psmt.setString(3, Nome);
            psmt.setString(3, Cognome);
            psmt.setString(3, Eta);
            psmt.setString(3, Stipendio);
            psmt.setString(3, Squadra);
            psmt.setString(3, AnniContratto);

            int rowsAffected = psmt.executeUpdate();
            System.out.println("Allenatore aggiunto");
            return rowsAffected >0 ;
        }  catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

}