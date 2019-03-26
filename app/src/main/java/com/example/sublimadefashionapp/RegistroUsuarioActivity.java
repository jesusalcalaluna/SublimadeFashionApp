package com.example.sublimadefashionapp;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistroUsuarioActivity extends AppCompatActivity {

    EditText ETxtFechaNacimiento;
    Calendar calendar;
    DatePickerDialog datePicker;
    EditText nombre, apellido, fechanacimiento, telcasa, telcel, direccion, codigopostal, email,contraseña;
    RadioButton masculino, femenino;
    Button registrar;
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
        email = (EditText) findViewById(R.id.ETxtEmail);
        contraseña = (EditText) findViewById(R.id.ETxtContrasena);
        masculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        femenino = (RadioButton) findViewById(R.id.radioButtonFemenino);
        registrar = (Button) findViewById(R.id.btnregistrar);

        ETxtFechaNacimiento = (EditText) findViewById(R.id.ETxtFechaNacimiento);
        ETxtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int anio = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(RegistroUsuarioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ETxtFechaNacimiento.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },dia,mes,anio);
                datePicker.show();
            }
        });


    }

    public void enviar(View view) throws JSONException {

        JSONObject persona = new JSONObject();
        /*persona.put("nombre",nombre.getText().toString());
        persona.put("apellido",apellido.getText().toString());
        persona.put("nacimiento",fechanacimiento.getText().toString());
        persona.put("telefono",telcasa.getText().toString());
        persona.put("celular",telcel.getText().toString());
        persona.put("direccion",direccion.getText().toString());
        persona.put("cp",codigopostal.getText().toString());
        persona.put("email",email.getText().toString());
        persona.put("contrasena",contraseña.getText().toString());*/
        persona.put("nombre","jesus");
        persona.put("apellido","Alcala");
        persona.put("nacimiento","1997-01-14");
        persona.put("telefono","9711179568");
        persona.put("celular","8711179568");
        persona.put("direccion","calle 1");
        persona.put("cp","27084");
      //  persona.put("email","jesusalcalaluna@yahoo.com");
       // persona.put("contrasena","123");

        persona.put("sexo","Masculino");
        /*if(femenino.isChecked()){
            persona.put("sexo",femenino.getText().toString());
        }
        if (masculino.isChecked()){
            persona.put("sexo",masculino.getText().toString());
        }*/

        Log.e("persona", String.valueOf(persona));



        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, "http://www.sublimade.mipantano.com/registro.usuario.android", persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(RegistroUsuarioActivity.this,"Objeto" + response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroUsuarioActivity.this,error.toString(),Toast.LENGTH_LONG).show();

            }
        });
        VolleyS.getInstance(this).getRq().add(jor);
    }
}
