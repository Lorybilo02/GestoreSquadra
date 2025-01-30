package org.example.teammanagement;

public class Giocatore {
    private int id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String ruolo;
    private int eta;
    private int numMaglia;
    private String nazionalita;
    private String piede;
    private String squadra;
    private int stipendio;
    private int anniContratto;
    private int Goal;
    private int Assist;
    private int MinutiGiocati;
    private int isTitolare;
    private String inCampo;
    // Costruttore
    public Giocatore() {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.eta = eta;
        this.numMaglia = numMaglia;
        this.nazionalita = nazionalita;
        this.piede = piede;
        this.squadra = squadra;
        this.stipendio = stipendio;
        this.anniContratto = anniContratto;
        this.Goal = Goal;
        this.Assist = Assist;
        this.MinutiGiocati = MinutiGiocati;
        this.isTitolare = isTitolare;
        this.inCampo = inCampo;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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


    public String getSquadra() {
        return squadra;
    }
    public void setSquadra(String squadra) {
        this.squadra = squadra;
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
    public int getGoal() {return Goal;}
    public void setGoal(int Goal) {this.Goal = Goal;}
    public int getAssist() {return Assist;}
    public void setAssist(int Assist) {this.Assist = Assist;}
    public int getMinutiGiocati() { return MinutiGiocati;}
    public void setMinutiGiocati(int minutiGiocati) {this.MinutiGiocati = minutiGiocati;}
    public int getIsTitolare() { return isTitolare;}
    public void setIsTitolare( int isTitolare) {this.isTitolare = isTitolare;}
    public String getinCampo(){return inCampo;};
    public void setinCampo(String inCampo){this.inCampo = inCampo;}


}

