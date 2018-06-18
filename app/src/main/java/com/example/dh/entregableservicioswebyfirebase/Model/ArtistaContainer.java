package com.example.dh.entregableservicioswebyfirebase.Model;

import java.util.List;

public class ArtistaContainer {
    private List<Artista> artistas;

    public ArtistaContainer() {
    }

    public ArtistaContainer(List<Artista> artistas){
        this.artistas = artistas;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
}
