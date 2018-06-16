package com.example.dh.entregableservicioswebyfirebase.Model;

/**
 * Created by DH on 30/5/2018.
 */

public class Artista {
    private String Influenced_by;
    private String nationality;
    private String name;
    private String artistId;

    public Artista() {
    }

    public Artista(String Influenced_by, String nationality, String name, String artistId) {
        this.Influenced_by = Influenced_by;
        this.nationality = nationality;
        this.name = name;
        this.artistId = artistId;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public void setInfluenced_by(String Influenced_by) {
        this.Influenced_by = Influenced_by;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}

