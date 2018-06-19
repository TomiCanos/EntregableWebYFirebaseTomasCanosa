package com.example.dh.entregableservicioswebyfirebase.View;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dh.entregableservicioswebyfirebase.Controller.AdapterViewPager;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.R;

import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    public static final String CLAVE_PAINT = "CLAVE_PAINT";
    public static final String CLAVE_POSITION = "CLAVE_POSITION";

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewPager = findViewById(R.id.view_pager_detalle);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Integer positionAdapter = bundle.getInt(CLAVE_POSITION);
        List<Paint> paintsList = (List<Paint>) bundle.getSerializable(CLAVE_PAINT);

        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager(), paintsList);

        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(positionAdapter);
    }
}
