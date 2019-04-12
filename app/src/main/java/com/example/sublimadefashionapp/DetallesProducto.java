package com.example.sublimadefashionapp;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sublimadefashionapp.Modelos.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class DetallesProducto extends AppCompatActivity{

    NumberPicker cantidad;
    RadioGroup talla;
    RadioButton chico, mediano, grande;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);
        cantidad = findViewById(R.id.Quantity);
        talla = findViewById(R.id.ProdSize);
        chico = findViewById(R.id.SizeSmall);
        mediano = findViewById(R.id.SizeMedium);
        grande = findViewById(R.id.SizeLarge);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        final RecyclerView rv = findViewById(R.id.detailsProdrv);
        rv.setLayoutManager(new LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false));

        int id = getIntent().getExtras().getInt("id");
        JSONArray objeto = new JSONArray();
        try {
            objeto.put(1, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.POST, "http://sublimade.mipantano.com/android/detalles", objeto,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson g = new Gson();
                            Type t = new TypeToken<List<Producto>>(){}.getType();
                            List<Producto> lp = g.fromJson(response.toString(), t);
                            AdaptadorDetallesProducto adapt = new AdaptadorDetallesProducto(lp);
                            rv.setAdapter(adapt);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
            }
        });
        VolleyS.getInstance(this).getRq().add(jar);



    }

    public void AddToCart(View view) throws JSONException {

        int id = getIntent().getExtras().getInt("id");
        JSONObject prod = new JSONObject();
        if(chico.isChecked()){
            prod.put("talla", chico.getText());
        }
        if(mediano.isChecked()){
            prod.put("talla", mediano.getText());
        }
        if(grande.isChecked()){
            prod.put("talla", grande.getText());
        }
        prod.put("cantidad", cantidad.getValue());
        prod.put("id", id);
        prod.put("id_usuario", User.id_persona);


        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, "http://sublimade.mipantano.com/android/addcarrito", prod, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(DetallesProducto.this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleyS.getInstance(this).getRq().add(jor);


       //JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, );

    }
}
