package com.example.sublimadefashionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sublimadefashionapp.Modelos.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class perfil extends AppCompatActivity implements View.OnClickListener {


    EditText telefonocasa;
    EditText telefonocel;
    EditText copo;
    EditText direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        try {
            iniciarlogin();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        telefonocasa=findViewById(R.id.TelCasa);
        telefonocel=findViewById(R.id.TelCel);
        copo=findViewById(R.id.cp);
        direccion=findViewById(R.id.direccion);

    }

    public void iniciarlogin() throws JSONException {

        JSONObject persona = new JSONObject();
        persona.put("e_mail",User.e_mail);

        String url = "http://www.sublimade.mipantano.com/api/android.obtenerusuario";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String telcasa=response.getString("tel_casa");
                    String dire=response.getString("direccion");
                    String cp=response.getString("cp");
                    String telcel=response.getString("tel_celular");

                    direccion.setText(dire);
                    telefonocasa.setText(telcasa);
                    telefonocel.setText(telcel);
                    copo.setText(cp);

                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(perfil.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        VolleyS.getInstance(this).getRq().add(request);
    }

    @Override
    public void onClick(View v) {
                if (telefonocasa.getText().toString().isEmpty()||telefonocel.getText().toString().isEmpty()||copo.getText().toString().isEmpty()||direccion.getText().toString().isEmpty())
                {
                    Toast.makeText(perfil.this, "Todos los campos deben ser llenados", Toast.LENGTH_LONG).show();
                }
                else {
                    JSONObject persona = new JSONObject();
                    try {
                       persona.put("api_token",User.api_token);
                        persona.put("id", User.id_persona);
                        persona.put("telefono_casa", telefonocasa.getText().toString());
                        persona.put("telefono_celular", telefonocel.getText().toString());
                        persona.put("cp", copo.getText().toString());
                        persona.put("direccion", direccion.getText().toString());

                        String url = "http://www.sublimade.mipantano.com/api/android.modificarusuario";

                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(perfil.this, "Datos guardados", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(perfil.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(perfil.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                        VolleyS.getInstance(this).getRq().add(request);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
    }
}
