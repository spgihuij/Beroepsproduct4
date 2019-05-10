package com.example.beroepsproduct4;

public class UserInformation {

    public String naam;
    public String woonplaats;
    public String emailadrespersoon;
    public String userID;

    public UserInformation() {

    }

    public String getNaam() {
        return naam;
    }

    public UserInformation setNaam(String naam) {
        this.naam = naam;
        return this;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public UserInformation setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
        return this;
    }

    public String getEmailadrespersoon() {
        return emailadrespersoon;
    }

    public UserInformation setEmailadrespersoon(String emailadrespersoon) {
        this.emailadrespersoon = emailadrespersoon;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public UserInformation setUserID(String userID) {
        this.userID = userID;
        return this;
    }
}

