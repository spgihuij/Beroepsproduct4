package com.example.beroepsproduct4;

public class SociaalNetwerk {

    public String sociaalnetwerknaam;
    public String sociaalnetwerkbeschrijving;
    private String Sociaalnetwerkfoto;

    public SociaalNetwerk() {}
    public SociaalNetwerk(String sociaalnetwerknaam, String sociaalnetwerkbeschrijving, String sociaalnetwerkfoto) {
        this.sociaalnetwerknaam = sociaalnetwerknaam;
        this.sociaalnetwerkbeschrijving = sociaalnetwerkbeschrijving;
        this.Sociaalnetwerkfoto = sociaalnetwerkfoto;
    }

    public String getSociaalnetwerknaam() {
        return sociaalnetwerknaam;
    }

    public SociaalNetwerk setSociaalnetwerknaam(String sociaalnetwerknaam) {
        this.sociaalnetwerknaam = sociaalnetwerknaam;
        return this;
    }

    public String getSociaalnetwerkbeschrijving() {
        return sociaalnetwerkbeschrijving;
    }



    public SociaalNetwerk setSociaalnetwerkbeschrijving(String sociaalnetwerkbeschrijving) {
        this.sociaalnetwerkbeschrijving = sociaalnetwerkbeschrijving;
        return this;
    }

    public String getSociaalnetwerkfoto() {
        return Sociaalnetwerkfoto;
    }

    public SociaalNetwerk setSociaalnetwerkfoto(String sociaalnetwerkfoto) {
        this.Sociaalnetwerkfoto = sociaalnetwerkfoto;
        return this;
    }
}
