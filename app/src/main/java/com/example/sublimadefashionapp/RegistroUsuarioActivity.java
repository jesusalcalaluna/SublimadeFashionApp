package com.example.sublimadefashionapp;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class RegistroUsuarioActivity extends AppCompatActivity {

    EditText ETxtFechaNacimiento;
    Calendar calendar;
    DatePickerDialog datePicker;
    EditText nombre, apellido, fechanacimiento, telcasa, telcel, direccion, codigopostal, email,contraseña;
    RadioButton masculino, femenino;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        nombre = (EditText) findViewById(R.id.ETxtNombre);
        apellido = (EditText) findViewById(R.id.ETxtApellido);
        fechanacimiento = (EditText) findViewById(R.id.ETxtFechaNacimiento);
        telcasa = (EditText) findViewById(R.id.ETxtTelCasa);
        telcel = (EditText) findViewById(R.id.EtxtTelCel);
        direccion = (EditText) findViewById(R.id.ETxtDireccion);
        codigopostal = (EditText) findViewById(R.id.ETxtCP);
        email = (EditText) findViewById(R.id.ETxtNombre);
        contraseña = (EditText) findViewById(R.id.ETxtNombre);
        masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        femenino = (RadioButton) findViewById(R.id.radioButtonMasculino);

        ETxtFechaNacimiento = (EditText) findViewById(R.id.ETxtFechaNacimiento);
        ETxtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int anio = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(RegistroUsuarioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ETxtFechaNacimiento.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },dia,mes,anio);
                datePicker.show();
            }
        });


    }

    public void enviar(View view) throws JSONException {
        JSONObject persona = new JSONObject();
        persona.put("nombre",nombre.getText().toString());
        persona.put("apellido",apellido.getText().toString());
        persona.put("nacimiento",fechanacimiento.getText().toString());
        persona.put("telefono",telcasa.getText().toString());
        persona.put("celular",telcel.getText().toString());
        persona.put("direccion",direccion.getText().toString());
        persona.put("cp",codigopostal.getText().toString());
        persona.put("email",email.getText().toString());
        persona.put("contrasena",contraseña.getText().toString());

        if(femenino.isChecked()){
            Toast.makeText(this,femenino.getText().toString(),Toast.LENGTH_LONG).show();
        }
        if (masculino.isChecked()){
            Toast.makeText(this,masculino.getText().toString(),Toast.LENGTH_LONG).show();
        }



        /*JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, "http://nuevo.rnrsiilge-org.mx/baraja/enviar", persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(RegistroUsuarioActivity.this,"Objeto" + response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleyS.getInstance(this).getRq().add(jor);*/
    }
}
