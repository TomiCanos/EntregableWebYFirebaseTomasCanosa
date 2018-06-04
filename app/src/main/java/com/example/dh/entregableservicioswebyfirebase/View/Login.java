package com.example.dh.entregableservicioswebyfirebase.View;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dh.entregableservicioswebyfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    private Button login;
    private Button register;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private LinearLayout linearLayout;
    private ReaccionadorDelUsuario reaccionadorDelUsuario;



    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        login = view.findViewById(R.id.fragment_login_button_login);
        register = view.findViewById(R.id.fragment_login_button_register);
        email = view.findViewById(R.id.fragment_login_email_adress);
        password = view.findViewById(R.id.fragment_login_password);
        linearLayout = view.findViewById(R.id.linlay1);

        password.setError("Password must have at least 6 characters");
        email.setError("Enter a valid email adress");

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            linearLayout.setVisibility(view.VISIBLE);
            }
        };

        handler.postDelayed(runnable, 2000);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                if (emailString.isEmpty() && passwordString.isEmpty()){
                    Toast.makeText(getContext(), "Enter your email and password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!emailString.contains("@") && !emailString.contains(".com")){
                    Toast.makeText(getContext(), "Enter a valid email adress", Toast.LENGTH_SHORT).show();
                } else if (passwordString.length() < 6){
                    Toast.makeText(getContext(), "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    crearUsuario(emailString, passwordString);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();
                if (email.getText().toString().isEmpty() && passwordString.isEmpty()){
                    Toast.makeText(getContext(), "Enter your email and password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!emailString.contains("@") && !emailString.contains(".com")){
                    Toast.makeText(getContext(), "Enter a valid email adress", Toast.LENGTH_SHORT).show();
                } else if (passwordString.length() < 6){
                    Toast.makeText(getContext(), "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    loguearUsuario(emailString, passwordString);
                }
            }
        });

        return view;
    }

    private void crearUsuario (String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("firebase", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    reaccionadorDelUsuario.succesfull();
                }  else {
                    Log.w("firebase", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loguearUsuario (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("firebase", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    reaccionadorDelUsuario.succesfull();
                } else {
                    Log.w("firebase", "signInWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        reaccionadorDelUsuario = (ReaccionadorDelUsuario) context;
    }

    public interface ReaccionadorDelUsuario{
        public void succesfull();
    }
}