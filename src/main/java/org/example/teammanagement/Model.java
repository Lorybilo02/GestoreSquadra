package org.example.teammanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final String URL = "jdbc:sqlite:database.db";
    private static Model instance; // singleton
    private Connection connection;

    Model() {
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
               //System.out.println("Connessione al database riuscita!");
            }
        } catch (Exception e) {
           // System.out.println("Errore nella connessione al database: " + e.getMessage());
        }
    }




    // verificare la password durante il login con ritorno di intero per tipologia utente
    public int authenticateUser(String username, String plainPassword) {
        //System.out.println(username + " " + plainPassword);
        String[] queries = {
                "SELECT Password FROM Admins WHERE Username = ?",
                "SELECT Password FROM Allenatori WHERE Username = ?",
                "SELECT Password FROM Giocatori WHERE Username = ?"
        };
        // 0 = Admin , 1 = Allenatore, 2 = Giocatore
        for (int i = 0; i < queries.length; i++) {
            try (PreparedStatement pstmt = connection.prepareStatement(queries[i])) {
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

    public String getNomeCognomeGiocatoreByUsername(String username) {
        String query = "SELECT Nome, Cognome FROM Giocatori WHERE Username = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, username);

            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("Nome");
                    String cognome = rs.getString("Cognome");
                    return nome + " " + cognome;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Username non trovato";
    }

    public String getNomeCognomeAllenatoreByUsername(String username) {
        String query = "SELECT Nome, Cognome FROM Allenatori WHERE Username = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, username);

            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("Nome");
                    String cognome = rs.getString("Cognome");
                    return nome + " " + cognome;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Username non trovato";
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

    public String getInCampoGiocatore(String username) {
        String query = "SELECT InCampo FROM Giocatori WHERE Username = ?";
        String incampo = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                incampo = rs.getString("InCampo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // System.out.println(incampo);
        return incampo;
    }

    // ritorna la squadra associata ad un allenaotore
    public String getSquadraByAllenatore(String username) {
        String query = "SELECT Squadra FROM Allenatori WHERE Username = ?";
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

   public int getNumeroMagliaByUsername(String username){
       String query = "SELECT NumMaglia FROM Giocatori WHERE Username = ?";
       int numMaglia = 0;

       try (PreparedStatement pstmt = connection.prepareStatement(query)) {
           pstmt.setString(1, username);
           ResultSet rs = pstmt.executeQuery();

           if (rs.next()) {
               numMaglia = rs.getInt("NumMaglia");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
      // System.out.println(numMaglia);
       return numMaglia;

   }

    // ritorna squadra ssociata ad un giocatore
    public String getSquadraByGiocatore(String username) {
        String query = "SELECT Squadra FROM Giocatori WHERE Username = ?";
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

    //metodo per tornare i dati di un allenatore in base alla squadra
    public Allenatore getAllenatoreBySquadra(String squadra) {
        String query = "SELECT * FROM Allenatori WHERE Squadra = ?";
        Allenatore allenatore = null;

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
                allenatore.setSquadra(rs.getString("Squadra"));
                allenatore.setAnniContratto(rs.getInt("AnniContratto"));
                allenatore.setModulo(rs.getString("Modulo"));
                allenatore.setNazionalita(rs.getString("Nazionalità"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allenatore;
    }

    // ritorna dati di un giocatore dato l'username
    public Giocatore getGiocatoreByUsername(String username) {
        String query = "SELECT * FROM Giocatori WHERE Username = ?";
        Giocatore giocatore = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                giocatore = new Giocatore();
                giocatore.setId(rs.getInt("ID"));
                giocatore.setUsername(rs.getString("Username"));
                giocatore.setPassword(rs.getString("Password"));
                giocatore.setNome(rs.getString("Nome"));
                giocatore.setCognome(rs.getString("Cognome"));
                giocatore.setRuolo(rs.getString("Ruolo"));
                giocatore.setEta(rs.getInt("Eta"));
                giocatore.setNumMaglia(rs.getInt("NumMaglia"));
                giocatore.setNazionalita(rs.getString("Nazionalità"));
                giocatore.setPiede(rs.getString("Piede"));
                giocatore.setSquadra(rs.getString("Squadra"));
                giocatore.setStipendio(rs.getInt("Stipendio"));
                giocatore.setAnniContratto(rs.getInt("AnniContratto"));
                giocatore.setGoal(rs.getInt("Goal"));
                giocatore.setAssist(rs.getInt("Assist"));
                giocatore.setMinutiGiocati(rs.getInt("MinutiGiocati"));
                giocatore.setIsTitolare(rs.getInt("isTitolare"));
                giocatore.setinCampo(rs.getString("InCampo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return giocatore;
    }


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
                giocatore.setinCampo(rs.getString("InCampo"));

                giocatori.add(giocatore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return giocatori;
    }












    //METODI EDIT GIOCATORE
    // metodo per settare un giocatore titolare prendendo come parametro il suo ID
    public boolean setGiocatoreTitolare(int id) {
        String query = "UPDATE Giocatori SET isTitolare = 1 WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // restituisce true se almeno una riga è stata aggiornata
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setGiocatoreNonTitolare(int id) {
        String query = "UPDATE Giocatori SET isTitolare = 0 WHERE ID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editNomeGiocatore(int id, String nome) {
        String query = "UPDATE Giocatori SET Nome = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, nome);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editCognomeGiocatore(int id, String cognome) {
        String query = "UPDATE Giocatori SET Cognome = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, cognome);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editNazionalitaGiocatore(int id, String nazionalita) {
        String query = "UPDATE Giocatori SET Nazionalità = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, nazionalita);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //metodo per modificare il ruolo In Campo
    public boolean editInCampoGiocatore(int id, String inCampo) {
        String query = "UPDATE Giocatori SET InCampo = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, inCampo);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editRuoloGiocatore(int id, String ruolo) {
        String query = "UPDATE Giocatori SET Ruolo = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, ruolo);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editEtaGiocatore(int id, int eta) {
        String query = "UPDATE Giocatori SET Eta = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, eta);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editPiedeGiocatore(int id, String piede) {
        String query = "UPDATE Giocatori SET Piede = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, piede);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editNumMagliaGiocatore(int id, int numMaglia) {
        String query = "UPDATE Giocatori SET NumMaglia = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, numMaglia);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editStipendioGiocatore(int id, int stipendio) {
        String query = "UPDATE Giocatori SET Stipendio = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, stipendio);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editAnnicontrattoGiocatore(int id, int anniContratto) {
        String query = "UPDATE Giocatori SET AnniContratto = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, anniContratto);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editGoalGiocatore(int id, int goal) {
        String query = "UPDATE Giocatori SET Goal = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, goal);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editAssistGiocatore(int id, int assist) {
        String query = "UPDATE Giocatori SET Assist = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, assist);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean editMinutigiocatiGiocatore(int id, int minutiGiocati) {
        String query = "UPDATE Giocatori SET MinutiGiocati = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setInt(1, minutiGiocati);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }












    //METODI EDIT ALLENATORE
    public boolean editNomeAllenatore(int id, String nome) {
        String query = "UPDATE Allenatori SET Nome = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, nome);
            psmt.setInt(2, id);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editCognomeAllenatore(int id, String cognome) {
        String query = "UPDATE Allenatori SET Cognome = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, cognome);
            psmt.setInt(2, id);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editEtaAllenatore(int id, int eta) {
        String query = "UPDATE Allenatori SET Eta = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setInt(1, eta);
            psmt.setInt(2, id);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editStipendioAllenatore(int id, int stipendio) {
        String query = "UPDATE Allenatori SET Stipendio = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setInt(1, stipendio);
            psmt.setInt(2, id);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editAnniContrattoAllenatore(int id, int anniContratto) {
        String query = "UPDATE Allenatori SET AnniContratto = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setInt(1, anniContratto);
            psmt.setInt(2, id);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editModuloAllenatore(int id, String modulo) {
        String query = "UPDATE Allenatori SET Modulo = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, modulo);
            psmt.setInt(2, id);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editNazionalitaAllenatore(int id, String nazionalita) {
        String query = "UPDATE Allenatori SET Nazionalità = ? WHERE ID = ?";
        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, nazionalita);
            psmt.setInt(2, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }












    //METODI RICHIESTE
    public String getTipoUtente(String username) {
        String checkGiocatori = "SELECT 1 FROM Giocatori WHERE Username = ?";
        String checkAllenatori = "SELECT 1 FROM Allenatori WHERE Username = ?";

        try (PreparedStatement checkGiocatoriStmt = connection.prepareStatement(checkGiocatori);
             PreparedStatement checkAllenatoriStmt = connection.prepareStatement(checkAllenatori)) {

            // controlla  nei giocatori
            checkGiocatoriStmt.setString(1, username);
            try (ResultSet rs = checkGiocatoriStmt.executeQuery()) {
                if (rs.next()) {
                    return "Giocatore";
                }
            }

            // controlla negli allenatori
            checkAllenatoriStmt.setString(1, username);
            try (ResultSet rs = checkAllenatoriStmt.executeQuery()) {
                if (rs.next()) {
                    return "Allenatore";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // username non trovato
    }

    public boolean addRichiestaGiocatore(String username, Integer nuovoStipendio, Integer nuoviAnniContratto, String squadra) {
        String query = "INSERT INTO RichiesteGiocatori (Username, NuovoStipendio, NuoviAnniContratto, Squadra) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, username);

            if (nuovoStipendio != null) {
                psmt.setInt(2, nuovoStipendio);
            } else {
                psmt.setNull(2, java.sql.Types.INTEGER);
            }

            if (nuoviAnniContratto != null) {
                psmt.setInt(3, nuoviAnniContratto);
            } else {
                psmt.setNull(3, java.sql.Types.INTEGER);
            }

            psmt.setString(4, squadra);

            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addRichiestaAllenatore(String username, Integer nuovoStipendio, Integer nuoviAnniContratto, String squadra) {
        String query = "INSERT INTO RichiesteAllenatori (Username, NuovoStipendio, NuoviAnniContratto, Squadra) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, username);

            if (nuovoStipendio != null) {
                psmt.setInt(2, nuovoStipendio);
            } else {
                psmt.setNull(2, java.sql.Types.INTEGER);
            }

            if (nuoviAnniContratto != null) {
                psmt.setInt(3, nuoviAnniContratto);
            } else {
                psmt.setNull(3, java.sql.Types.INTEGER);
            }

            psmt.setString(4, squadra);


            int rowsAffected = psmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public List<RichiesteGiocatori> getRichiesteGiocatoreByUsername(String username) {
        List<RichiesteGiocatori> richieste = new ArrayList<>();
        String query = "SELECT ID, Username, NuovoStipendio, NuoviAnniContratto, Squadra " +
                "FROM RichiesteGiocatori " +
                "WHERE Username = ?";

        try (PreparedStatement psmt = connection.prepareStatement(query)) {
            psmt.setString(1, username);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    RichiesteGiocatori richiesta = new RichiesteGiocatori();
                    richiesta.setId(rs.getInt("ID"));
                    richiesta.setUsername(rs.getString("Username"));
                    richiesta.setNuovoStipendio(rs.getInt("NuovoStipendio"));
                    richiesta.setNuoviAnniContratto(rs.getInt("NuoviAnniContratto"));
                    richiesta.setSquadra(rs.getString("Squadra"));
                    richieste.add(richiesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return richieste;
    }


    //ritorna tutte le richiesteGiocatori di una squadra
    public List<RichiesteGiocatori> getAllRichiesteGiocatori(String squadra) {
        List<RichiesteGiocatori> richieste = new ArrayList<>();
        String query = "SELECT ID, Username, NuovoStipendio, NuoviAnniContratto, Squadra " +
                "FROM RichiesteGiocatori " +
                "WHERE Squadra = ?";

        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, squadra);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    RichiesteGiocatori richiesta = new RichiesteGiocatori();
                    richiesta.setId(rs.getInt("ID"));
                    richiesta.setUsername(rs.getString("Username"));
                    richiesta.setNuovoStipendio(rs.getInt("NuovoStipendio"));
                    richiesta.setNuoviAnniContratto(rs.getInt("NuoviAnniContratto"));
                    richiesta.setSquadra(rs.getString("Squadra"));
                    richieste.add(richiesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return richieste;
    }

    public List<RichiesteAllenatori> getAllRichiesteAllenatori(String squadra) {
        List<RichiesteAllenatori> richieste = new ArrayList<>();
        String query = "SELECT ID, Username, NuovoStipendio, NuoviAnniContratto, Squadra " +
                "FROM RichiesteAllenatori " +
                "WHERE Squadra = ?";

        try (PreparedStatement psmt = connection.prepareStatement(query)) {

            psmt.setString(1, squadra);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    RichiesteAllenatori richiesta = new RichiesteAllenatori();
                    richiesta.setId(rs.getInt("ID"));
                    richiesta.setUsername(rs.getString("Username"));
                    richiesta.setNuovoStipendio(rs.getInt("NuovoStipendio"));
                    richiesta.setNuoviAnniContratto(rs.getInt("NuoviAnniContratto"));
                    richiesta.setSquadra(rs.getString("Squadra"));
                    richieste.add(richiesta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return richieste;
    }

    public boolean accettaRichiesta(int richiestaId, String username) {
        String tipoUtente = getTipoUtente(username);
        if (tipoUtente == null) {
            //System.out.println("Username non trovato in Giocatori o Allenatori.");
            return false;
        }

        String getRequestQuery;
        String updateQuery;
        String deleteRequestQuery;

        if ("Giocatore".equalsIgnoreCase(tipoUtente)) {
            getRequestQuery = "SELECT NuovoStipendio, NuoviAnniContratto FROM RichiesteGiocatori WHERE ID = ?";
            //COALESCE serve a gestire il fatto che un parametro può essere null
            updateQuery = "UPDATE Giocatori SET Stipendio = COALESCE(?, Stipendio), AnniContratto = COALESCE(?, AnniContratto) WHERE Username = ?";
            deleteRequestQuery = "DELETE FROM RichiesteGiocatori WHERE ID = ?";
        } else if ("Allenatore".equalsIgnoreCase(tipoUtente)) {
            getRequestQuery = "SELECT NuovoStipendio, NuoviAnniContratto FROM RichiesteAllenatori WHERE ID = ?";
            updateQuery = "UPDATE Allenatori SET Stipendio = COALESCE(?, Stipendio), AnniContratto = COALESCE(?, AnniContratto) WHERE Username = ?";
            deleteRequestQuery = "DELETE FROM RichiesteAllenatori WHERE ID = ?";
        } else {
            return false;
        }

        try (PreparedStatement getRequestStmt = connection.prepareStatement(getRequestQuery);
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
             PreparedStatement deleteStmt = connection.prepareStatement(deleteRequestQuery)) {

            getRequestStmt.setInt(1, richiestaId);
            try (ResultSet rs = getRequestStmt.executeQuery()) {
                if (rs.next()) {
                    Integer nuovoStipendio = rs.getObject("NuovoStipendio") != null ? rs.getInt("NuovoStipendio") : null;
                    Integer nuoviAnniContratto = rs.getObject("NuoviAnniContratto") != null ? rs.getInt("NuoviAnniContratto") : null;

                    updateStmt.setObject(1, nuovoStipendio, java.sql.Types.INTEGER);
                    updateStmt.setObject(2, nuoviAnniContratto, java.sql.Types.INTEGER);
                    updateStmt.setString(3, username);

                    int rowsUpdated = updateStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        deleteStmt.setInt(1, richiestaId);
                        deleteStmt.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rifiutaRichiesta(int richiestaId, String username) {
        String tipoUtente = getTipoUtente(username);
        if (tipoUtente == null) {
            //System.out.println("Username non trovato in Giocatori o Allenatori.");
            return false;
        }

        String deleteRequestQuery;

        if ("Giocatore".equalsIgnoreCase(tipoUtente)) {
            deleteRequestQuery = "DELETE FROM RichiesteGiocatori WHERE ID = ?";
        } else if ("Allenatore".equalsIgnoreCase(tipoUtente)) {
            deleteRequestQuery = "DELETE FROM RichiesteAllenatori WHERE ID = ?";
        } else {
            return false;
        }

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteRequestQuery)) {
            deleteStmt.setInt(1, richiestaId);
            return deleteStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean accettaTutteLeRichieste(String squadra) {
        String getRequestsQueryGiocatori = "SELECT ID, Username, NuovoStipendio, NuoviAnniContratto FROM RichiesteGiocatori WHERE Squadra = ?";
        String getRequestsQueryAllenatori = "SELECT ID, Username, NuovoStipendio, NuoviAnniContratto FROM RichiesteAllenatori WHERE Squadra = ?";
        String updateGiocatoreQuery = "UPDATE Giocatori SET Stipendio = COALESCE(?, Stipendio), AnniContratto = COALESCE(?, AnniContratto) WHERE Username = ?";
        String updateAllenatoreQuery = "UPDATE Allenatori SET Stipendio = COALESCE(?, Stipendio), AnniContratto = COALESCE(?, AnniContratto) WHERE Username = ?";
        String deleteRequestGiocatoriQuery = "DELETE FROM RichiesteGiocatori WHERE ID = ?";
        String deleteRequestAllenatoriQuery = "DELETE FROM RichiesteAllenatori WHERE ID = ?";

        try {
            // gestione richieste giocatori
            try (PreparedStatement getRequestsGiocatoriStmt = connection.prepareStatement(getRequestsQueryGiocatori);
                 PreparedStatement updateGiocatoreStmt = connection.prepareStatement(updateGiocatoreQuery);
                 PreparedStatement deleteRequestGiocatoriStmt = connection.prepareStatement(deleteRequestGiocatoriQuery)) {

                getRequestsGiocatoriStmt.setString(1, squadra);
                try (ResultSet rsGiocatori = getRequestsGiocatoriStmt.executeQuery()) {
                    while (rsGiocatori.next()) {
                        int id = rsGiocatori.getInt("ID");
                        String username = rsGiocatori.getString("Username");
                        Integer nuovoStipendio = rsGiocatori.getObject("NuovoStipendio") != null ? rsGiocatori.getInt("NuovoStipendio") : null;
                        Integer nuoviAnniContratto = rsGiocatori.getObject("NuoviAnniContratto") != null ? rsGiocatori.getInt("NuoviAnniContratto") : null;

                        updateGiocatoreStmt.setObject(1, nuovoStipendio, java.sql.Types.INTEGER);
                        updateGiocatoreStmt.setObject(2, nuoviAnniContratto, java.sql.Types.INTEGER);
                        updateGiocatoreStmt.setString(3, username);

                        if (updateGiocatoreStmt.executeUpdate() > 0) {
                            deleteRequestGiocatoriStmt.setInt(1, id);
                            deleteRequestGiocatoriStmt.executeUpdate();
                        }
                    }
                }
            }

            // gestione richieste allenatori
            try (PreparedStatement getRequestsAllenatoriStmt = connection.prepareStatement(getRequestsQueryAllenatori);
                 PreparedStatement updateAllenatoreStmt = connection.prepareStatement(updateAllenatoreQuery);
                 PreparedStatement deleteRequestAllenatoriStmt = connection.prepareStatement(deleteRequestAllenatoriQuery)) {

                getRequestsAllenatoriStmt.setString(1, squadra);
                try (ResultSet rsAllenatori = getRequestsAllenatoriStmt.executeQuery()) {
                    while (rsAllenatori.next()) {
                        int id = rsAllenatori.getInt("ID");
                        String username = rsAllenatori.getString("Username");
                        Integer nuovoStipendio = rsAllenatori.getObject("NuovoStipendio") != null ? rsAllenatori.getInt("NuovoStipendio") : null;
                        Integer nuoviAnniContratto = rsAllenatori.getObject("NuoviAnniContratto") != null ? rsAllenatori.getInt("NuoviAnniContratto") : null;

                        updateAllenatoreStmt.setObject(1, nuovoStipendio, java.sql.Types.INTEGER);
                        updateAllenatoreStmt.setObject(2, nuoviAnniContratto, java.sql.Types.INTEGER);
                        updateAllenatoreStmt.setString(3, username);

                        if (updateAllenatoreStmt.executeUpdate() > 0) {
                            deleteRequestAllenatoriStmt.setInt(1, id);
                            deleteRequestAllenatoriStmt.executeUpdate();
                        }
                    }
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean rifiutaTutteLeRichieste(String squadra) {
        String deleteGiocatoriQuery = "DELETE FROM RichiesteGiocatori WHERE Squadra = ?";
        String deleteAllenatoriQuery = "DELETE FROM RichiesteAllenatori WHERE Squadra = ?";

        try (PreparedStatement deleteGiocatoriStmt = connection.prepareStatement(deleteGiocatoriQuery);
             PreparedStatement deleteAllenatoriStmt = connection.prepareStatement(deleteAllenatoriQuery)) {

            // elimina le richieste dei giocatori
            deleteGiocatoriStmt.setString(1, squadra);
            int giocatoriDeleted = deleteGiocatoriStmt.executeUpdate();

            // elimina le richieste degli allenatori
            deleteAllenatoriStmt.setString(1, squadra);
            int allenatoriDeleted = deleteAllenatoriStmt.executeUpdate();

            //System.out.println("Eliminate  richieste da RichiesteGiocatori.");
            //System.out.println("Eliminate  richieste da RichiesteAllenatori.");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




















    // metodo per aggiungere giocatori (per semplicità)
     public boolean addGiocatore (String username, String password, String nome, String cognome,
                String ruolo,int eta, int numMaglia, String nazionalita,
                String piede, String squadra,int stipendio, int anniContratto,
        int goal, int assist, int minutiGiocati, int isTitolare, String inCampo){
            String query = "INSERT INTO Giocatori (Username, Password, Nome, Cognome, Ruolo, Eta, NumMaglia, Nazionalità, Piede, Squadra, Stipendio, AnniContratto, Goal, Assist, MinutiGiocati, isTitolare,inCampo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
                pstmt.setString(17, inCampo);

                int rowsAffected = pstmt.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        //metodo per aggiungere Admin (per semplicità)
     public boolean addAdmin (String username, String password, String squadra){
            String query = "INSERT INTO Admins (Username, Password, Squadra) VALUES (?, ?, ?)";
            try (PreparedStatement psmt = connection.prepareStatement(query)) {
                psmt.setString(1, username);
                psmt.setString(2, PasswordUtils.hashPassword(password));
                psmt.setString(3, squadra);

                int rowsAffected = psmt.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        //metodo per aggiungere Allenatore (per semplicità)
     public boolean addAllenatore (String username, String password, String Nome, String Cognome,int Eta,
        int Stipendio, String Squadra,int AnniContratto, String Modulo){
            String query = "INSERT INTO Allenatori (Username, Password, Nome, Cognome, Eta, Stipendio, Squadra, AnniContratto, Modulo) VALUES (?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement psmt = connection.prepareStatement(query)) {
                psmt.setString(1, username);
                psmt.setString(2, PasswordUtils.hashPassword(password));
                psmt.setString(3, Nome);
                psmt.setString(4, Cognome);
                psmt.setInt(5, Eta);
                psmt.setInt(6, Stipendio);
                psmt.setString(7, Squadra);
                psmt.setInt(8, AnniContratto);
                psmt.setString(9, Modulo);

                int rowsAffected = psmt.executeUpdate();
                System.out.println("Allenatore aggiunto");
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }


        }

    }