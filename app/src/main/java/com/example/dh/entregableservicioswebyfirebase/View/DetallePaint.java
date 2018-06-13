package com.example.dh.entregableservicioswebyfirebase.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;

import java.security.PublicKey;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePaint extends Fragment {

    public static final String ID_PAINT = "ID_PAINT";

    private TextView tituloPaint;
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

        Bundle bundle = getArguments();
        paint = (Paint) bundle.getSerializable(ID_PAINT);

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
