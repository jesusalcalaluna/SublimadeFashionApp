package com.example.sublimadefashionapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sublimadefashionapp.Activities.MainActivity;
import com.example.sublimadefashionapp.Activities.RegistroUsuarioActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    public static final int SIGN_IN_CODE=777;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button btnregistrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnregistrate = (Button) findViewById(R.id.btnRegistrateaqui);
        btnregistrate.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .requestIdToken(getString(R.string.token)).build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);


        firebaseAuth= FirebaseAuth.getInstance();
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){

                    //metodo que lleva a activity home una vez se haya logeado
                    try {
                        goMainScreen();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(Register.this, MainActivity.class));
                }
                else
                {


                }
            }
        };
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRegistrateaqui:
                Intent intent = new Intent(this, RegistroUsuarioActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.signInButton:
                Intent signInIntent =Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent,SIGN_IN_CODE);
                break;
        }

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Checa respuesta de logeo de Google
        if (requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            HandleSignResult(result);
        }
    }


    private void HandleSignResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            firebaseAuthWithGoogle(result.getSignInAccount());
        } else{
            Toast.makeText(this,"nose puede iniciar sesion",Toast.LENGTH_SHORT).show();
        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( !task.isSuccessful() )
                {
                    Toast.makeText(Register.this, "No se pudo autenticar ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goMainScreen() throws JSONException {

        chequeoderegisterenbd();

    }

    private void chequeoderegisterenbd() throws JSONException {
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        JSONObject persona = new JSONObject();
        persona.put("e_mail",firebaseUser.getEmail());


        String url = "http://www.sublimade.mipantano.com/api/android.iniciarsession.google";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response!=null){

                    Intent intent = new Intent(Register.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Intent intent = new Intent(Register.this,RegistroUsuarioActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        VolleyS.getInstance(this).getRq().add(request);
    }



    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void iniciar(View view) {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
        finish();
    }
}
