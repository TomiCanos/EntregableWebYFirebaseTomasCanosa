package com.example.dh.entregableservicioswebyfirebase.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorFileLoader;
import com.example.dh.entregableservicioswebyfirebase.Controller.ArtistController;
import com.example.dh.entregableservicioswebyfirebase.Controller.DAOArtist;
import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.Model.ArtistaContainer;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private ImageView imagenPaint;
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
        imagenPaint = view.findViewById(R.id.imgview_fragment_detalle);

        Bundle bundle = getArguments();
        paint = (Paint) bundle.getSerializable(ID_PAINT);
        ArtistController artistController = new ArtistController();
        artistController.obtenerArtists(paint.getArtistId(), new ResultListener<Artista>() {
            @Override
            public void finish(Artista resultado) {
                artistId.setText("Artist's ID: " + resultado.getArtistId());
                artistName.setText("Artist: " + resultado.getName());
                artistNationality.setText("Nacionality: " + resultado.getNationality());
                artistInfluencedBy.setText("Influenced by: " + resultado.getInfluenced_by());
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference();
        reference = reference.child(paint.getImage());

        Glide.with(this).using(new FirebaseImageLoader())
                .load(reference)
                .into(imagenPaint);

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
