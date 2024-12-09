package org.example.teammanagement;

public class Giocatore {
    private int id;
    private String username;
    private String password;
    private String nomeCognome;
    private String ruolo;
    private int eta;
    private int numMaglia;
    private String nazionalita;
    private String piede;
    private int stipendio;
    private int anniContratto;

    // Costruttore
    public Giocatore(int id, String username, String password, String nomeCognome, String ruolo, int eta,
                     int numMaglia, String nazionalita, String piede, int stipendio, int anniContratto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nomeCognome = nomeCognome;
        this.ruolo = ruolo;
        this.eta = eta;
        this.numMaglia = numMaglia;
        this.nazionalita = nazionalita;
        this.piede = piede;
        this.stipendio = stipendio;
        this.anniContratto = anniContratto;
    }

    // getter e setter

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

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getNumMaglia() {
        return numMaglia;
    }

    public void setNumMaglia(int numMaglia) {
        this.numMaglia = numMaglia;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getPiede() {
        return piede;
    }

    public void setPiede(String piede) {
        this.piede = piede;
    }

    public int getStipendio() {
        return stipendio;
    }

    public void setStipendio(int stipendio) {
        this.stipendio = stipendio;
    }

    public int getAnniContratto() {
        return anniContratto;
    }

    public void setAnniContratto(int anniContratto) {
        this.anniContratto = anniContratto;
    }


}

