package com.example.sublimadefashionapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipSession;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sublimadefashionapp.Fragments.CatalogoFragment;
import com.example.sublimadefashionapp.Modelos.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class filtrosActivity extends AppCompatActivity implements View.OnClickListener,CatalogoFragment.OnFragmentInteractionListener {


    String sexo, idcategoria, idtipoproducto;
    Spinner categorias, productos;
    RadioButton todo, hombre, mujer;
    List<Categoria> lc;
    List<tipos_producto> lp;
    ArrayList<String> categ, product;
    Categoria categoria;
    tipos_producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        todo=findViewById(R.id.all);
        hombre=findViewById(R.id.hombre);
        mujer=findViewById(R.id.mujer);
        categorias=findViewById(R.id.categoria);
        productos=findViewById(R.id.producto);
        categ=new ArrayList<>();
        product=new ArrayList<>();
        String url = "http://www.sublimade.mipantano.com/api/android.obtenercategoria";
        String url1="http://www.sublimade.mipantano.com/api/android.obtenertipoproducto";

        final JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type tp = new TypeToken<List<Categoria>>() {
                }.getType();
                lc = new Gson().fromJson(response.toString(), tp);

                for (int i = 0; i < lc.toArray().length; i++) {
                    categoria = lc.get(i);
                    categ.add(categoria.getCategoria());
                }
                categ.add("Todo");
                categorias.setAdapter(new ArrayAdapter<String>(filtrosActivity.this, android.R.layout.simple_spinner_item, categ));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(filtrosActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleyS.getInstance(getApplicationContext()).getRq().add(jsonArrayRequest);

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category=categorias.getItemAtPosition(categorias.getSelectedItemPosition()).toString();
                for (int i=0; i<lc.toArray().length; i++){
                    categoria=(lc.get(i));
                    if (category==categoria.categoria){
                        idcategoria=categoria.categoria;
                    }
                    else if (category=="Todo"){
                        idcategoria= "all";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final JsonArrayRequest jsonArrayRequest1=new JsonArrayRequest(Request.Method.GET, url1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Type tp = new TypeToken<List<tipos_producto>>() {
                }.getType();
                lp = new Gson().fromJson(response.toString(), tp);

                for (int i = 0; i < lp.toArray().length; i++) {
                    producto =lp.get(i);
                    product.add(producto.getNombre());
                }
                product.add("Todo");
                productos.setAdapter(new ArrayAdapter<String>(filtrosActivity.this, android.R.layout.simple_spinner_item, product));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(filtrosActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        VolleyS.getInstance(getApplicationContext()).getRq().add(jsonArrayRequest1);

        productos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String productoso=productos.getItemAtPosition(productos.getSelectedItemPosition()).toString();
                for (int i=0; i<lp.toArray().length; i++){
                    producto=(lp.get(i));
                    if (productoso==producto.nombre){
                        idtipoproducto= String.valueOf(producto.id_tipo_producto);
                    }
                    else if (productoso=="Todo"){
                        idtipoproducto= "all";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        RadioGroup rGroup = (RadioGroup)findViewById(R.id.sexogroup);
// This will get the radiobutton in the radiogroup that is checked
        RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());



//        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//        {
//            public void onCheckedChanged(RadioGroup group, int checkedId)
//            {
//                // This will get the radiobutton that has changed in its check state
//                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
//                // This puts the value (true/false) into the variable
//                boolean isChecked = checkedRadioButton.isChecked();
//                // If the radiobutton that has changed in check state is now checked...
//
//                if (isChecked)
//                {
//                    Toast.makeText(filtrosActivity.this, sexo, Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {

        if(todo.isChecked()){
            sexo="all";
        }
        else if (mujer.isChecked()){
            sexo="Mujer";
        }
        else if (hombre.isChecked()){
            sexo="Hombre";
        }
        Intent intent=new Intent(filtrosActivity.this, MainActivity.class);
        intent.putExtra("sexo",sexo);
        intent.putExtra("categoria",idcategoria);
        intent.putExtra("tipoproducto",idtipoproducto);
        intent.putExtra("abrirfragmentcatalogo","texto");
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
