package com.example.dh.entregableservicioswebyfirebase.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePaint extends Fragment {

    public static final String ID_PAINT = "ID_PAINT";

    private TextView tituloPaint;
    private TextView artistId;
    private TextView artistName;
    private TextView artistNationality;
    private TextView artistInfluencedBy;
    private Paint paint;

    public DetallePaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_paint, container, false);

        tituloPaint = view.findViewById(R.id.txtview_fragment_detalle_paint_name);
        artistId = view.findViewById(R.id.txtview_fragment_detalle_artist_id);
        artistName = view.findViewById(R.id.txtview_fragment_detalle_artist_name);
        artistNationality = view.findViewById(R.id.txtview_fragment_detalle_artist_nationality);
        artistInfluencedBy = view.findViewById(R.id.txtview_fragment_detalle_artist_influenced_by);

        Bundle bundle = getArguments();
        paint = (Paint) bundle.getSerializable(ID_PAINT);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Artista artista =  dataSnapshot.child("artists").child(paint.getArtistId().toString()).getValue(Artista.class);
               artistName.setText(artista.getName());
               artistId.setText(artista.getArtistId());
               artistNationality.setText(artista.getNationality());
               artistInfluencedBy.setText(artista.getInfluenced_by());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tituloPaint.setText(paint.getName());
        return view;
    }

    public static DetallePaint fabricaDeFragments(Paint paint){
        DetallePaint detallePaint = new DetallePaint();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ID_PAINT, paint);
        detallePaint.setArguments(bundle);
        return detallePaint;
    }
}
