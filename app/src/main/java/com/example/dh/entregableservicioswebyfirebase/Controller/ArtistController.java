package com.example.dh.entregableservicioswebyfirebase.Controller;

import android.util.Log;

import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;

import java.util.List;

/**
 * Created by DH on 18/6/2018.
 */

public class ArtistController {

    private Boolean hayInternet() {return true;}

    public void obtenerArtists(final ResultListener<List<Artista>> escuchadorDeLaVista){
        if (hayInternet()){
            DAOArtist daoArtist = new DAOArtist();
            daoArtist.obtenerArtistsAsincronico(new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            });
        } else {
            Log.d("Internet", "no hay internet");
        }
    }

}
