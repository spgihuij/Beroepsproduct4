package com.example.beroepsproduct4;

public class Persoon {


    public String persoonNaam;
    public String persoonEmail;
    public String persoonProfielFoto;
    public String persoonWoonplaats;
    public String persoonGeboortedatum;
    public String persoonSport;
    public String persoonHuisdier;
    public String persoonTvProgramma;
    public String persoonWebsite;
    public String persoonHobby;

    public Persoon(){

    }
    public Persoon(String persoonnaam, String persoonemail, String persoonprofielfoto, String persoonwoonplaats, String persoongeboortedatum, String persoonsport, String persoonhuisdier, String persoontvprogramma, String persoonwebsite, String persoonhobby) {
        this.persoonNaam = persoonnaam;
        this.persoonEmail = persoonemail;
        this.persoonProfielFoto = persoonprofielfoto;
        this.persoonWoonplaats = persoonwoonplaats;
        this.persoonGeboortedatum = persoongeboortedatum;
        this.persoonSport = persoonsport;
        this.persoonHuisdier = persoonhuisdier;
        this.persoonTvProgramma = persoontvprogramma;
        this.persoonWebsite = persoonwebsite;
        this.persoonHobby = persoonhobby;
    }

    public String getPersoonHobby() {
        return persoonHobby;
    }

    public void setPersoonHobby(String persoonHobby) {
        this.persoonHobby = persoonHobby;
    }

    public String getPersoonnaam() {
        return persoonNaam;
    }

    public void setPersoonnaam(String persoonnaam) {
        this.persoonNaam = persoonnaam;
    }

    public String getPersoonemail() {
        return persoonEmail;
    }

    public void setPersoonemail(String persoonemail) {
        this.persoonEmail = persoonemail;
    }

    public String getPersoonprofielfoto() {
        return persoonProfielFoto;
    }

    public void setPersoonprofielfoto(String persoonprofielfoto) {
        this.persoonProfielFoto = persoonprofielfoto;

    }

    public String getPersoonwoonplaats() {
        return persoonWoonplaats;
    }

    public void setPersoonwoonplaats(String persoonwoonplaats) {
        this.persoonWoonplaats = persoonwoonplaats;
    }

    public String getPersoongeboortedatum() {
        return persoonGeboortedatum;
    }

    public void setPersoongeboortedatum(String persoongeboortedatum) {
        this.persoonGeboortedatum = persoongeboortedatum;
    }

    public String getPersoonsport() {
        return persoonSport;
    }

    public void setPersoonsport(String persoonsport) {
        this.persoonSport = persoonsport;
    }

    public String getPersoonhuisdier() {
        return persoonHuisdier;
    }

    public void setPersoonhuisdier(String persoonhuisdier) {
        this.persoonHuisdier = persoonhuisdier;
    }

    public String getPersoontvprogramma() {
        return persoonTvProgramma;
    }

    public void setPersoontvprogramma(String persoontvprogramma) {
        this.persoonTvProgramma = persoontvprogramma;
    }

    public String getPersoonwebsite() {
        return persoonWebsite;
    }

    public void setPersoonwebsite(String persoonwebsite) {
        this.persoonWebsite = persoonwebsite;
    }


}
