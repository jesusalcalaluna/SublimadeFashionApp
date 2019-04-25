package com.example.sublimadefashionapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sublimadefashionapp.Categoria;
import com.example.sublimadefashionapp.R;
import com.example.sublimadefashionapp.VolleyS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class filtrosActivity extends AppCompatActivity implements View.OnClickListener {

    String sexo,categoria,producto;
    Spinner categorias, productos;

    ArrayList<Categoria> CategoriaArrayList;
    ArrayList<String> categ=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        categorias=findViewById(R.id.categoria);
        productos=findViewById(R.id.producto);



        String url = "http://www.sublimade.mipantano.com/api/android.obtenercategoria";


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrrrrrr", ">>" + response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("status").equals("true")) {
                                CategoriaArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("categoria");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    Categoria cat = new Categoria();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    cat.setCategoria(dataobj.getString("categoria"));

                                    CategoriaArrayList.add(cat);
                                }

                                for (int i = 0; i < CategoriaArrayList.size(); i++) {
                                    categ.add(CategoriaArrayList.get(i).getCategoria().toString());
                                }
                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(filtrosActivity.this, android.R.layout.simple_spinner_item, categ);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                categorias.setAdapter(spinnerArrayAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(filtrosActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        VolleyS.getInstance(this).getRq().add(stringRequest);

        RadioGroup rGroup = (RadioGroup)findViewById(R.id.sexogroup);
// This will get the radiobutton in the radiogroup that is checked
        RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...

                sexo =checkedRadioButton.getText().toString();

                if (isChecked)
                {
                    Toast.makeText(filtrosActivity.this, checkedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {



    }
}
