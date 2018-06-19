package com.example.dh.entregableservicioswebyfirebase.Controller;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;

import java.util.List;

/**
 * Created by DH on 30/5/2018.
 */

public class PaintController {

    public Boolean hayInternet() {
        return true;
    }

    public void obtenerPaints(final ResultListener<List<Paint>> escuchadorDeLaVista) {
        if (hayInternet()) {
            DAOPaint daoPaint = new DAOPaint();

            daoPaint.obtenerPaintsAsincronico(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            });
        } else {
            Log.d("Internet", "No hay Internet");
        }
    }

}
