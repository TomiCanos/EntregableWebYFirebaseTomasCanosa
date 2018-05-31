package com.example.dh.entregableservicioswebyfirebase.View;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dh.entregableservicioswebyfirebase.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayHomeAsUpEnabled(true);
        //ab.setDisplayShowTitleEnabled(false);
        ab.hide();

        Home fragmentHome = new Home();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container_main_activity, fragmentHome);
        transaction.commit();
    }
}
