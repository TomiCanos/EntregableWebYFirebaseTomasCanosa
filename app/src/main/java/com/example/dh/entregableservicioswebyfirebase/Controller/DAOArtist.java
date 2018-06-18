package com.example.dh.entregableservicioswebyfirebase.Controller;

import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.Model.ArtistaContainer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOArtist {
    public DAOArtist() {
    }

    /*public Artista getArtistaById(Integer id){
        Artista artistaFinal = new Artista();
        for (Artista artista : artistas) {
            if (id.toString().equals(artista.getArtistId())){
                 artistaFinal = artista;
            }
        }
        return artistaFinal;
    }*/

    public List<Artista> getArtist() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        final List <Artista> artistaList = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               /* ArtistaContainer artistas = dataSnapshot.child("artists").getValue(ArtistaContainer.class);
                for (Artista artista : artistas) {
                    artistaList.add(artista);
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return artistaList;
    }
}
