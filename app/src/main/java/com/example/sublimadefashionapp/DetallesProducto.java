package com.example.sublimadefashionapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sublimadefashionapp.Adapters.AdaptadorDetallesProducto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class DetallesProducto extends AppCompatActivity{



    RecyclerView rv;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        rv = findViewById(R.id.detailsProdrv);
        rv.setHasFixedSize(true);
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

}
