package com.example.sublimadefashionapp.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sublimadefashionapp.Modelos.User;
import com.example.sublimadefashionapp.R;
import com.example.sublimadefashionapp.VolleyS;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistroUsuarioActivity extends AppCompatActivity  {

    String id,nom,correo,celular,uid;
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
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            nom=firebaseUser.getDisplayName();
            celular=firebaseUser.getPhoneNumber();
            correo=firebaseUser.getEmail();
            uid=firebaseUser.getUid();

            nombre.setText(nom);
            telcel.setText(celular);
            email.setText(correo);
            contraseña.setText(uid);

        }
        ETxtFechaNacimiento = (EditText) findViewById(R.id.ETxtFechaNacimiento);
        ETxtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int anio = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(RegistroUsuarioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ETxtFechaNacimiento.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, dia, mes, anio);
                datePicker.show();
            }
        });
    }

    public void enviar(View view) throws JSONException{

         JSONObject persona = new JSONObject();
        persona.put("nombre",nombre.getText().toString());
        persona.put("apellido",apellido.getText().toString());
        persona.put("nacimiento",fechanacimiento.getText().toString());
        persona.put("celular",telcel.getText().toString());
        persona.put("telefono",telcasa.getText().toString());
        persona.put("direccion",direccion.getText().toString());
        persona.put("cp",codigopostal.getText().toString());
        persona.put("email",email.getText().toString());
        persona.put("contrasena",contraseña.getText().toString());
        if(masculino.isChecked()){
            persona.put("sexo",masculino.getText().toString());
        }
        if(femenino.isChecked()){
            persona.put("sexo",femenino.getText().toString());
        }

        String url = "http://www.sublimade.mipantano.com/api/registro.android";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String mail = response.getString("e_mail");
                    String id = response.getString("id_persona");
                    String t_u = response.getString("tipo_usuario");
                    String pass = response.getString("pass");
                    String tkn = response.getString("api_token");

                    User.id_persona=id;
                    User.e_mail=mail;
                    User.pass=pass;
                    User.tipo_usuario=t_u;
                    User.api_token=tkn;

                    Intent intent = new Intent(RegistroUsuarioActivity.this, MainActivity.class);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleyS.getInstance(this).getRq().add(request);
    }
}

