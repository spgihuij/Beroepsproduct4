package com.example.beroepsproduct4;

public class Evenement {
    public String evenementid;
    public String evenementnaam;
    public String evenementlocatie;
    public String evenementbeschrijving;
    public String evenementdatum;

    public Evenement() {
    }

    public Evenement(String evenementid, String evenementnaam, String evenementlocatie, String evenementbeschrijving, String evenementdatum) {

        this.evenementnaam = evenementnaam;
        this.evenementlocatie = evenementlocatie;
         this.evenementbeschrijving = evenementbeschrijving;
        this.evenementid = evenementid;
    }

    public String getEvenementnaam() {
        return evenementnaam;
    }

    public void setEvenementnaam(String evenementnaam) {
        this.evenementnaam = evenementnaam;
    }

    public String getEvenementlocatie() {
        return evenementlocatie;
    }

    public void setEvenementlocatie(String evenementlocatie) {
        this.evenementlocatie = evenementlocatie;
    }

    public String getEvenementbeschrijving() {
        return evenementbeschrijving;
    }

    public void setEvenementbeschrijving(String evenementbeschrijving) {
        this.evenementbeschrijving = evenementbeschrijving;
    }

    public String getEvenementid() {
        return evenementid;
    }

    public void setEvenementid(String evenementid) {
        this.evenementid = evenementid;
    }
}
