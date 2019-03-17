package com.example.sublimadefashionapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener,CatalogoFragment.OnFragmentInteractionListener,
        CarritoFragment.OnFragmentInteractionListener, DeseadosFragment.OnFragmentInteractionListener{
    RecyclerView rvCatalogo;
    String id;
    List<Producto> lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = "iniciofragment";
        InicioFragment fragment = InicioFragment.newInstance("id", id);
        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,fragment).commit();
        //Barra de navegacion
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);


        //Metodo de la barra de navegacion
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.carritoItem:
                        id = "carritofragment";
                        CarritoFragment carritoFragment = CarritoFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,carritoFragment).commit();
                        break;
                    case R.id.catalogoItem:
                        id = "catalogofragment";
                        CatalogoFragment catalogoFragment = CatalogoFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,catalogoFragment).commit();

                        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET, "http://sublimade.com/android/catalogo", null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        try {
                                            Gson g = new Gson();
                                            Type t = new TypeToken<List<Producto>>(){}.getType();
                                            List<Producto> lp = g.fromJson(response.toString(), t);
                                            AdaptadorProducto adapt= new AdaptadorProducto(lp);
                                            rvCatalogo.setAdapter(adapt);
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
                        break;
                    case R.id.inicioItem:
                        id = "iniciofragment";
                        InicioFragment inicioFragment = InicioFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,inicioFragment).commit();
                        break;
                    case R.id.deseadosItem:
                        id = "deseadosfragment";
                        DeseadosFragment deseadosFragment = DeseadosFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,deseadosFragment).commit();
                        break;
                }

                return true;
            }
        });//Final del metodo de la barra de navegacion
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
