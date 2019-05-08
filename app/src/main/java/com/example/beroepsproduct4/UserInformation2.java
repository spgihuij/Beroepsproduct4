package com.example.beroepsproduct4;

public class UserInformation2 {
    public String naam;

    public String getNaam() {
        return naam;
    }

    public UserInformation2 setNaam(String naam) {
        this.naam = naam;
        return this;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public UserInformation2 setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
        return this;
    }

    public String getEmailadrespersoon() {
        return emailadrespersoon;
    }

    public UserInformation2 setEmailadrespersoon(String emailadrespersoon) {
        this.emailadrespersoon = emailadrespersoon;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public UserInformation2 setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public UserInformation2(String naam, String woonplaats, String emailadrespersoon, String userID) {
        this.naam = naam;
        this.woonplaats = woonplaats;
        this.emailadrespersoon = emailadrespersoon;
        this.userID = userID;
    }

    public String woonplaats;
    public String emailadrespersoon;
    public String userID;
}
