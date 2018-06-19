package com.example.dh.entregableservicioswebyfirebase.Controller;

import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOArtist {

    public DAOArtist() {
    }

    public void obtenerArtistporIdAsincronico(final Integer id, final ResultListener<Artista> escuchadorDelControlador) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot : dataSnapshot.child("artists").getChildren()) {
                    Artista artista = datasnapshot.getValue(Artista.class);

                    if (artista.getArtistId().equals(id.toString())) {
                        escuchadorDelControlador.finish(artista);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
