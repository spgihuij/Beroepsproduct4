package com.example.beroepsproduct4;

public class Persoon {

    private String persoonemail;
    private String persoonnaam;
    private String persoonfoto;
    private String persoonwoonplaats;
    private String persoongeboortedatum;
    private String persoonsport;
    private String persoonhuisdier;
    private String persoontvprogramma;
    private String persoonwebsite;

    public Persoon(String persoonemail, String persoonnaam, String persoonfoto, String persoonwoonplaats, String persoongeboortedatum, String persoonsport, String persoonhuisdier, String persoontvprogramma, String persoonwebsite) {
        this.persoonemail = persoonemail;
        this.persoonnaam = persoonnaam;
        this.persoonfoto = persoonfoto;
        this.persoonwoonplaats = persoonwoonplaats;
        this.persoongeboortedatum = persoongeboortedatum;
        this.persoonsport = persoonsport;
        this.persoonhuisdier = persoonhuisdier;
        this.persoontvprogramma = persoontvprogramma;
        this.persoonwebsite = persoonwebsite;
    }

    public String getPersoonemail() {
        return persoonemail;
    }

    public void setPersoonemail(String persoonemail) {
        this.persoonemail = persoonemail;
    }

    public String getPersoonnaam() {
        return persoonnaam;
    }

    public void setPersoonnaam(String persoonnaam) {
        this.persoonnaam = persoonnaam;
    }

    public String getPersoonfoto() {
        return persoonfoto;
    }

    public void setPersoonfoto(String persoonfoto) {
        this.persoonfoto = persoonfoto;
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


