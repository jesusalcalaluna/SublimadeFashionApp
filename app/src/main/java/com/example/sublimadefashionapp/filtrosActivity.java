package com.example.sublimadefashionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class filtrosActivity extends AppCompatActivity implements View.OnClickListener {

    String sexo,categoria,producto;
    NumberPicker categorias, productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);

        categorias=findViewById(R.id.categoriapicker);
        productos=findViewById(R.id.productopicker);



//        String url = "http://www.sublimade.mipantano.com/api/android.obtenercategoria";
//
//        JsonArrayRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//
//                  categorias.setDisplayedValues(response.);
//
//                } catch (JSONException e){
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(filtrosActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//        VolleyS.getInstance(this).getRq().add(request);



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
