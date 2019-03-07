package com.example.sublimadefashionapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    //Barra de navegacion
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        info = (TextView) findViewById(R.id.Info);

        //Metodo de la barra de navegacion
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.carritoItem:
                        //Toast.makeText(MainActivity.this ,"Carrito",Toast.LENGTH_SHORT).show();
                        info.setText(R.string.carrito);
                        break;
                    case R.id.catalogoItem:
                        //Toast.makeText(MainActivity.this ,"Catalogo",Toast.LENGTH_SHORT).show();
                        info.setText(R.string.catalogo);
                        break;
                    case R.id.inicioItem:
                        //Toast.makeText(MainActivity.this ,"Inicio",Toast.LENGTH_SHORT).show();
                        info.setText(R.string.inicio);
                        break;
                    case R.id.deseadosItem:
                        //Toast.makeText(MainActivity.this ,"Deseados",Toast.LENGTH_SHORT).show();
                        info.setText(R.string.deseados);
                        break;
                }

                return true;
            }
        });//Final del metodo de la barra de navegacion
    }
}
