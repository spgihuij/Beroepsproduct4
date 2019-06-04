package com.example.beroepsproduct4;

public class EvenementGroep {

    String persoonsnaam1;
    String persoonsnaam2;
    String evenementnaam;
    String id;



    public EvenementGroep(String persoonsnaam1, String persoonsnaam2, String evenementnaam, String id) {
        this.persoonsnaam1 = persoonsnaam1;
        this.persoonsnaam2 = persoonsnaam2;
        this.evenementnaam = evenementnaam;
        this.id = id;

    }

    public EvenementGroep()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersoonsnaam1() {
        return persoonsnaam1;
    }

    public void setPersoonsnaam1(String persoonsnaam1) {
        this.persoonsnaam1 = persoonsnaam1;
    }

    public String getPersoonsnaam2() {
        return persoonsnaam2;
    }

    public void setPersoonsnaam2(String persoonsnaam2) {
        this.persoonsnaam2 = persoonsnaam2;
    }

    public String getEvenementnaam() {
        return evenementnaam;
    }

    public void setEvenementnaam(String evenementnaam) {
        this.evenementnaam = evenementnaam;
    }
}
