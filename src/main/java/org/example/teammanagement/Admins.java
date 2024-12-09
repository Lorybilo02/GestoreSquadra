package org.example.teammanagement;

public class Admins {
    private int id;
    private String username;
    private String password;
    private String squadra;


    public Admins(int id, String username, String password, String squadra) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.squadra = squadra;
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

    public String getSquadra() {
        return squadra;
    }

    public void setSquadra(String squadra) {
        this.squadra = squadra;
    }



}

