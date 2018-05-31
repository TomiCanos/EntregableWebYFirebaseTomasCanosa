package com.example.dh.entregableservicioswebyfirebase.Controller;

import android.util.Log;

import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.Model.PaintsContainer;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;

import java.io.Console;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DH on 30/5/2018.
 */

public class DAOPaint {

    private Retrofit retrofit;

    public DAOPaint(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();

    }

    public void obtenerPaintsAsincronico(final ResultListener<List<Paint>> escuchadorDelControlador) {
        ServicePaints servicePaints = retrofit.create(ServicePaints.class);
        Call<PaintsContainer> call = servicePaints.getPaints();
        call.enqueue(new Callback<PaintsContainer>() {
            @Override
            public void onResponse(Call<PaintsContainer> call, Response<PaintsContainer> response) {
                escuchadorDelControlador.finish(response.body().getPaints());
            }

            @Override
            public void onFailure(Call<PaintsContainer> call, Throwable t) {
                Log.d("Retrofit", "Retrofit Fallo");
            }
        });
    }
}
