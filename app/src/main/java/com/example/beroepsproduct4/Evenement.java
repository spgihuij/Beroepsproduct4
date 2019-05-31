package com.example.beroepsproduct4;

public class Evenement {
    public String evenementid;
    public String evenementnaam;
    public String evenementlocatie;
    public String evenementbeschrijving;
    public String evenementdatum;
    public String evenementfoto;
    public Evenement() {

    }

    public String getEvenementid() {
        return evenementid;
    }

    public void setEvenementid(String evenementid) {
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

    public String getEvenementdatum() {
        return evenementdatum;
    }

    public void setEvenementdatum(String evenementdatum) {
        this.evenementdatum = evenementdatum;
    }

    public String getEvenementfoto() {
        return evenementfoto;
    }

    public void setEvenementfoto(String evenementfoto) {
        this.evenementfoto = evenementfoto;
    }

    public Evenement(String evenementid, String evenementnaam, String evenementlocatie, String evenementbeschrijving, String evenementdatum, String evenementfoto) {
        this.evenementid = evenementid;
        this.evenementnaam = evenementnaam;
        this.evenementlocatie = evenementlocatie;
        this.evenementbeschrijving = evenementbeschrijving;
        this.evenementdatum = evenementdatum;
        this.evenementfoto = evenementfoto;
    }
}
