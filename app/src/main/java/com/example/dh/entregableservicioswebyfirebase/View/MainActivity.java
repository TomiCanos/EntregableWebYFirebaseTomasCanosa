package com.example.dh.entregableservicioswebyfirebase.View;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dh.entregableservicioswebyfirebase.Controller.ArtistController;
import com.example.dh.entregableservicioswebyfirebase.Model.Artista;
import com.example.dh.entregableservicioswebyfirebase.Model.Paint;
import com.example.dh.entregableservicioswebyfirebase.utils.ResultListener;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.dh.entregableservicioswebyfirebase.R;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Login.ReaccionadorDelUsuario, Home.ReaccionadorDelLogout, Home.PasadorDeLaPaintADetalle {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mAuth.getCurrentUser() == null) {
            transaction.add(R.id.container_main_activity, new Login());
        } else {
            transaction.add(R.id.container_main_activity, new Home());
        }
        transaction.commit();
    }

    @Override
    public void succesfull() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main_activity, new Home());
        transaction.commit();
    }

    @Override
    public void desloguear() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main_activity, new Login());
        transaction.commit();
    }

    @Override
    public void pasarPaint(List<Paint> listaPaints, Integer positionPaint) {
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetalleActivity.CLAVE_PAINT, (Serializable) listaPaints);
        bundle.putInt(DetalleActivity.CLAVE_POSITION, positionPaint);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
