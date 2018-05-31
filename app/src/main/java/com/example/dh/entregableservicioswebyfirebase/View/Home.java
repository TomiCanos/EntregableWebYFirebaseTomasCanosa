package com.example.dh.entregableservicioswebyfirebase.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh.entregableservicioswebyfirebase.Controller.AdapterPaint;
import com.example.dh.entregableservicioswebyfirebase.Controller.PaintController;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private AdapterPaint adapterPaint;
    private List<Paint> listaPaints;
    private List<Paint> listaPaintsResultado;



    public Home() {
       listaPaints = new ArrayList<>();
       listaPaintsResultado = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerPaints  = view.findViewById(R.id.recycler_view_paints);
        recyclerPaints.setHasFixedSize(true);
        recyclerPaints.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterPaint = new AdapterPaint(crearListaPaints());

        recyclerPaints.setAdapter(adapterPaint);
        return view;
    }

    private List<Paint> crearListaPaints() {
        if (listaPaints.size() == 0){
            PaintController paintController = new PaintController();

            paintController.obtenerPaints(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    listaPaintsResultado = resultado;

                    for (Paint paint : listaPaintsResultado) {
                        listaPaints.add(paint);
                    }
                    adapterPaint.notifyDataSetChanged();
                }
            });
        }

        return listaPaints;
    }

}
