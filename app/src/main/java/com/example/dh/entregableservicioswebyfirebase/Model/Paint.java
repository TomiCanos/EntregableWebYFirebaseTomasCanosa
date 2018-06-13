package com.example.dh.entregableservicioswebyfirebase.Model;


import java.io.Serializable;

/**
 * Created by DH on 30/5/2018.
 */

public class Paint implements Serializable{
    private String image;
    private String name;
    private Integer artistId;

    public Paint() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
