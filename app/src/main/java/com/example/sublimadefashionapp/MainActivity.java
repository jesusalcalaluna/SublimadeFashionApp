package com.example.sublimadefashionapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.sublimadefashionapp.Fragments.CarritoFragment;
import com.example.sublimadefashionapp.Fragments.CatalogoFragment;
import com.example.sublimadefashionapp.Fragments.DeseadosFragment;
import com.example.sublimadefashionapp.Fragments.InicioFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener, CatalogoFragment.OnFragmentInteractionListener,
        CarritoFragment.OnFragmentInteractionListener, DeseadosFragment.OnFragmentInteractionListener{

    String id;
    private DrawerLayout drawerLayout;
    private TextView txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        TextView txtDisplayName = findViewById(R.id.prueba);
        txtEmail = (TextView) findViewById(R.id.correousuario);
        setToolbar();

        if (firebaseUser != null) {
            txtDisplayName.setText(firebaseUser.getDisplayName());
         //   txtEmail.setText(firebaseUser.getDisplayName());

        }





        id = "iniciofragment";
        InicioFragment fragment = InicioFragment.newInstance("id", id);
        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner,fragment).commit();
        //Barra de navegacion
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        //SideBar menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //Metodo de la barra de navegacion inferior
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
        });//Final del metodo de la barra de navegacion inferior
    }

    private void setToolbar(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Metodo para abrir sidebar (menu lateral)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //abrir menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }//Fin del Metodo para abrir sidebar (menu lateral)

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
