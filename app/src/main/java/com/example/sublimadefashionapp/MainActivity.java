package com.example.sublimadefashionapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.example.sublimadefashionapp.Modelos.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import com.example.sublimadefashionapp.Fragments.CarritoFragment;
import com.example.sublimadefashionapp.Fragments.CatalogoFragment;
import com.example.sublimadefashionapp.Fragments.DeseadosFragment;
import com.example.sublimadefashionapp.Fragments.InicioFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener, CatalogoFragment.OnFragmentInteractionListener,
        CarritoFragment.OnFragmentInteractionListener, DeseadosFragment.OnFragmentInteractionListener{
    RecyclerView rvCatalogo;
    String id,nombre,correo,celular,uid, itemselected;
    List<Producto> lp;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    private TextView txtNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.header_navigation_drawer);
        setToolbar();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        //Fragment Inicial del BottomNavigation
        id = "iniciofragment";
        InicioFragment fragment = InicioFragment.newInstance("id", id);
        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,fragment).commit();

        //SideBar menux
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Navigation View
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        txtNombreUsuario = (TextView) header.findViewById(R.id.txtnombreusuario);


       if(User.api_token !=null){
          // Toast.makeText(MainActivity.this, datos.Usuario.api_token.toString(), Toast.LENGTH_LONG).show();
          txtNombreUsuario.setText(User.e_mail.toString());
             Toast.makeText(MainActivity.this, User.pass, Toast.LENGTH_LONG).show();
       }





        if (firebaseUser != null) {
            txtNombreUsuario.setText(firebaseUser.getDisplayName());
                nombre=firebaseUser.getDisplayName();
                celular=firebaseUser.getPhoneNumber();
                correo=firebaseUser.getEmail();
                uid=firebaseUser.getUid();
            try {
                enviarcuenta();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Metodo del Navigation View
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.perfilItem:
                        Intent intent=new Intent(MainActivity.this, perfil.class);
                        startActivity(intent);
                        break;
                    case R.id.comprasItem:

                        break;
                    case R.id.pedidosItem:

                        break;
                    case R.id.cerrarsesionItem:
                        if(firebaseUser != null){
                            firebaseAuth.getInstance().signOut();
                            finish();

                        }
                        Intent inten=new Intent(MainActivity.this, login.class);
                        startActivity(inten);
                        break;
                }

                return true;
            }
        });
        //Barra de navegacion
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottmnavigation_view);
        //Metodo de la barra de navegacion inferior
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                itemselected= String.valueOf(menuItem.getItemId());

                switch (menuItem.getItemId()){
                    case R.id.carritoItem:
                        id = "carritofragment";
                        CarritoFragment carritoFragment = CarritoFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,carritoFragment).commit();
                        break;
                    case R.id.catalogoItem:
                        id = "catalogofragment";
                        CatalogoFragment catalogoFragment = CatalogoFragment.newInstance("id", id);
                       getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,catalogoFragment).commit();
                        break;
                    case R.id.inicioItem:
                        id = "iniciofragment";
                        InicioFragment inicioFragment = InicioFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,inicioFragment).commit();
                        break;
                    case R.id.deseadosItem:
                        id = "deseadosfragment";
                        DeseadosFragment deseadosFragment = DeseadosFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,deseadosFragment).commit();
                        break;
                }

                return true;
            }
        });//Final del metodo de la barra de navegacion inferior
    }

//Método para toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//                getMenuInflater().inflate(R.menu.menu_catalogo_filtros, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    private void enviarcuenta() throws JSONException {



            JSONObject persona = new JSONObject();
            persona.put("nombre",nombre);
            persona.put("celular",celular);
            persona.put("email",correo);
            persona.put("contrasena",uid);


            String url = "http://www.sublimade.mipantano.com/registro.usuario.android";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Mensaje", response.toString());
                    Toast.makeText(MainActivity.this, "Persona" + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VolleyS.getInstance(this).getRq().add(request);
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
            case R.id.filtro:
                Intent intent=new Intent(MainActivity.this, filtrosActivity.class);
                startActivity(intent);
                return true;

            case R.id.search:
                onSearchRequested();
                return true;

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
