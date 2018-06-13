package com.example.dh.entregableservicioswebyfirebase.View;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh.entregableservicioswebyfirebase.R;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
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
    private TextView forgotpassword;
    private LoginButton loginFacebook;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authStateListener;
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
        forgotpassword = view.findViewById(R.id.fragment_login_forgot_password);
        linearLayout = view.findViewById(R.id.linlay1);
        loginFacebook = (LoginButton) view.findViewById(R.id.login_button);

        password.setError("Password must have at least 6 characters");
        email.setError("Enter a valid email adress");

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null){
                    reaccionadorDelUsuario.succesfull();
                }
            }
        };

        //efecto de carga
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            linearLayout.setVisibility(view.VISIBLE);
            }
        };

        handler.postDelayed(runnable, 2000);


        loginFacebook.setReadPermissions("email");
        loginFacebook.setFragment(this);

        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getContext(), "Logged In", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken());
                reaccionadorDelUsuario.succesfull();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getContext(), "FATAL ERROR", Toast.LENGTH_SHORT).show();
            }
        });


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


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "u stupid!", Toast.LENGTH_SHORT).show();
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

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getContext(), "ERROR LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        reaccionadorDelUsuario = (ReaccionadorDelUsuario) context;
    }

    public interface ReaccionadorDelUsuario{
        public void succesfull();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}