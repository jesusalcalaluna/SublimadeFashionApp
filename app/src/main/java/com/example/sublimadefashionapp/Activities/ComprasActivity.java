package com.example.sublimadefashionapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sublimadefashionapp.AdaptadorCarrito;
import com.example.sublimadefashionapp.AdaptadorCompras;
import com.example.sublimadefashionapp.Carrito;
import com.example.sublimadefashionapp.MainActivity;
import com.example.sublimadefashionapp.Modelos.Pedido;
import com.example.sublimadefashionapp.Modelos.User;
import com.example.sublimadefashionapp.R;
import com.example.sublimadefashionapp.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;

public class ComprasActivity extends AppCompatActivity {

    RecyclerView rvPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvPedidos = findViewById(R.id.rvCompras);

        String id = User.id_persona;
        JSONArray objeto = new JSONArray();
        try {
            objeto.put(1, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rvPedidos.setLayoutManager(new LinearLayoutManager(this ,LinearLayoutManager.VERTICAL,false));
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.POST, "http://sublimade.mipantano.com/getPedidosAndroid", objeto,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson g = new Gson();
                            Type t = new TypeToken<List<Pedido>>(){}.getType();
                            List<Pedido> lc = g.fromJson(response.toString(), t);
                            AdaptadorCompras adapt= new AdaptadorCompras(lc);
                            rvPedidos.setAdapter(adapt);
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
