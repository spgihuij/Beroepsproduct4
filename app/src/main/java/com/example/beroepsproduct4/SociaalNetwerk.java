package com.example.beroepsproduct4;

public class SociaalNetwerk {
        public String sociaalnetwerkid;
        public String sociaalnetwerktnaam;
        public String sociaalnetwerkbeschrijving;
        public String sociaalnetwerkfoto;


    public SociaalNetwerk(String sociaalnetwerkid, String sociaalnetwerktnaam, String sociaalnetwerkbeschrijving, String sociaalnetwerkfoto) {
        this.sociaalnetwerkid = sociaalnetwerkid;
        this.sociaalnetwerktnaam = sociaalnetwerktnaam;
        this.sociaalnetwerkbeschrijving = sociaalnetwerkbeschrijving;
        this.sociaalnetwerkfoto = sociaalnetwerkfoto;
    }

    public String getSociaalnetwerkid() {
        return sociaalnetwerkid;
    }

    public void setSociaalnetwerkid(String sociaalnetwerkid) {
        this.sociaalnetwerkid = sociaalnetwerkid;
    }

    public String getSociaalnetwerktnaam() {
        return sociaalnetwerktnaam;
    }

    public void setSociaalnetwerktnaam(String sociaalnetwerktnaam) {
        this.sociaalnetwerktnaam = sociaalnetwerktnaam;
    }

    public String getSociaalnetwerkbeschrijving() {
        return sociaalnetwerkbeschrijving;
    }

    public void setSociaalnetwerkbeschrijving(String sociaalnetwerkbeschrijving) {
        this.sociaalnetwerkbeschrijving = sociaalnetwerkbeschrijving;
    }

    public String getSociaalnetwerkfoto() {
        return sociaalnetwerkfoto;
    }

    public void setSociaalnetwerkfoto(String sociaalnetwerkfoto) {
        this.sociaalnetwerkfoto = sociaalnetwerkfoto;
    }
}
