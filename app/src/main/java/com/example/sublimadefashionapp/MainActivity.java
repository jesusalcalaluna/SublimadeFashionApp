package com.example.sublimadefashionapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener,CatalogoFragment.OnFragmentInteractionListener,
        CarritoFragment.OnFragmentInteractionListener, DeseadosFragment.OnFragmentInteractionListener{

    String id;
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
