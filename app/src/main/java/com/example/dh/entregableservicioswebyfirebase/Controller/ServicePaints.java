package com.example.dh.entregableservicioswebyfirebase.Controller;

import com.example.dh.entregableservicioswebyfirebase.Model.PaintsContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DH on 30/5/2018.
 */

public interface ServicePaints {

    @GET("/bins/x858r")
    Call<PaintsContainer> getPaints();


}
