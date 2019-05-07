package com.example.beroepsproduct4;

public class Persoon {
    public String persoonnaam;
    public String persoonemail;
    public String persoonprofielfoto;

    public Persoon(String persoonnaam, String persoonemail, String persoonprofielfoto) {
        this.persoonnaam = persoonnaam;
        this.persoonemail = persoonemail;
        this.persoonprofielfoto = persoonprofielfoto;
    }

    public String getPersoonprofielfoto() {
        return persoonprofielfoto;
    }

    public void setPersoonprofielfoto(String persoonprofielfoto) {
        this.persoonprofielfoto = persoonprofielfoto;
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
}
