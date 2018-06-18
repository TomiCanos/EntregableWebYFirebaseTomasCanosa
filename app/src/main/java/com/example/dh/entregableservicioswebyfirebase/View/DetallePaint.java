package com.example.dh.entregableservicioswebyfirebase.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dh.entregableservicioswebyfirebase.Controller.DAOArtist;
import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.Model.ArtistaContainer;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePaint extends Fragment {

    public static final String ID_PAINT = "ID_PAINT";
    public static final String ID_ARTIST = "ID_ARTIST";

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

        /*artistId.setText(artista.getArtistId());
        artistName.setText(artista.getName());
        artistNationality.setText(artista.getNationality());
        artistInfluencedBy.setText(artista.getInfluenced_by());
        tituloPaint.setText(paint.getName());*/
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
