package com.example.dh.entregableservicioswebyfirebase.Controller;

import android.util.Log;

import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;

import java.util.List;

/**
 * Created by DH on 18/6/2018.
 */

public class ArtistController {

    private Boolean hayInternet() {
        return true;
    }

    public void obtenerArtists(Integer id, final ResultListener<Artista> escuchadorDeLaVista) {
        DAOArtist daoArtist = new DAOArtist();
        daoArtist.obtenerArtistporIdAsincronico(id, new ResultListener<Artista>() {
            @Override
            public void finish(Artista resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

}
