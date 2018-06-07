package com.example.dh.entregableservicioswebyfirebase.View;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dh.entregableservicioswebyfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements Login.ReaccionadorDelUsuario, Home.ReaccionadorDelLogout {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mAuth.getCurrentUser() == null){
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
}
