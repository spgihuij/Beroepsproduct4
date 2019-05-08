package com.example.beroepsproduct4;

public class Persoon {
    public String persoonnaam;
    public String persoonemail;
    public String persoonprofielfoto;
    public String persoonwoonplaats;
    public String persoongeboortedatum;
    public String persoonsport;
    public String persoonhuisdier;
    public String persoontvprogramma;
    public String persoonwebsite;

    public Persoon(String persoonnaam, String persoonemail, String persoonprofielfoto, String persoonwoonplaats, String persoongeboortedatum, String persoonsport, String persoonhuisdier, String persoontvprogramma, String persoonwebsite) {
        this.persoonnaam = persoonnaam;
        this.persoonemail = persoonemail;
        this.persoonprofielfoto = persoonprofielfoto;
        this.persoonwoonplaats = persoonwoonplaats;
        this.persoongeboortedatum = persoongeboortedatum;
        this.persoonsport = persoonsport;
        this.persoonhuisdier = persoonhuisdier;
        this.persoontvprogramma = persoontvprogramma;
        this.persoonwebsite = persoonwebsite;
    }

    public String getPersoonnaam() {
        return persoonnaam;
    }

    public void setPersoonnaam(String persoonnaam) {
        this.persoonnaam = persoonnaam;
    }

    public String getPersoonemail() {
        return persoonemail;
    }

    public void setPersoonemail(String persoonemail) {
        this.persoonemail = persoonemail;
    }

    public String getPersoonprofielfoto() {
        return persoonprofielfoto;
    }

    public void setPersoonprofielfoto(String persoonprofielfoto) {
        this.persoonprofielfoto = persoonprofielfoto;
    }

    public String getPersoonwoonplaats() {
        return persoonwoonplaats;
    }

    public void setPersoonwoonplaats(String persoonwoonplaats) {
        this.persoonwoonplaats = persoonwoonplaats;
    }

    public String getPersoongeboortedatum() {
        return persoongeboortedatum;
    }

    public void setPersoongeboortedatum(String persoongeboortedatum) {
        this.persoongeboortedatum = persoongeboortedatum;
    }

    public String getPersoonsport() {
        return persoonsport;
    }

    public void setPersoonsport(String persoonsport) {
        this.persoonsport = persoonsport;
    }

    public String getPersoonhuisdier() {
        return persoonhuisdier;
    }

    public void setPersoonhuisdier(String persoonhuisdier) {
        this.persoonhuisdier = persoonhuisdier;
    }

    public String getPersoontvprogramma() {
        return persoontvprogramma;
    }

    public void setPersoontvprogramma(String persoontvprogramma) {
        this.persoontvprogramma = persoontvprogramma;
    }

    public String getPersoonwebsite() {
        return persoonwebsite;
    }

    public void setPersoonwebsite(String persoonwebsite) {
        this.persoonwebsite = persoonwebsite;
    }
}