package com.example.dh.entregableservicioswebyfirebase.Controller;

import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.Model.ArtistaContainer;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;
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

    public void obtenerArtistsAsincronico(final ResultListener<List<Artista>> escuchadorDelControlador) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        final List <Artista> artistaList = new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot : dataSnapshot.child("artists").getChildren()) {
                        Artista artista = datasnapshot.getValue(Artista.class);

                        artistaList.add(artista);
                }
                escuchadorDelControlador.finish(artistaList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
