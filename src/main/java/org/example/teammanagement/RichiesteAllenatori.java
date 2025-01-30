package org.example.teammanagement;

public class RichiesteAllenatori {
    private int id;
    private String username;
    private int nuovoStipendio;
    private int nuoviAnniContratto;
    private String squadra;

    public RichiesteAllenatori() {
        this.id = id;
        this.username = username;
        this.nuovoStipendio = nuovoStipendio;
        this.nuoviAnniContratto = nuoviAnniContratto;
        this.squadra = squadra;
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public int getNuovoStipendio(){return nuovoStipendio;}
    public void setNuovoStipendio(int nuovoStipendio){this.nuovoStipendio = nuovoStipendio;}
    public int getNuoviAnniContratto() {return nuoviAnniContratto;}
    public void setNuoviAnniContratto(int nuoviAnniContratto){this.nuoviAnniContratto = nuoviAnniContratto;}
    public String getSquadra() {return squadra;}
    public void setSquadra(String squadra) {this.squadra = squadra;}
}
