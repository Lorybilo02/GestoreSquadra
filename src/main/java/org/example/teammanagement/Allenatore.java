package org.example.teammanagement;

public class Allenatore {
    private int id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private int eta;
    private int stipendio;
    private String squadra;
    private int AnniContratto;


    public Allenatore(int id, String username, String password, String nome,String cognome, int eta, int stipendio, String squadra) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.stipendio = stipendio;
        this.squadra = squadra;
        this.AnniContratto = AnniContratto;
    }
    //construttore vuoto
    public Allenatore () {}

    // Getter e Setter per ogni attributo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getStipendio() {
        return stipendio;
    }

    public void setStipendio(int stipendio) {
        this.stipendio = stipendio;
    }

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }
    public int getAnniContratto() {return AnniContratto;}
    public void setAnniContratto(int AnniContratto) {
        this.AnniContratto = AnniContratto;
    }


}

