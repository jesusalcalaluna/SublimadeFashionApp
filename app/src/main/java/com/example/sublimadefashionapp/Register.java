package com.example.sublimadefashionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity implements View.OnClickListener {
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);  b=(Button) findViewById(R.id.b2);
        b.setOnClickListener(this);
    }

    public void onClick(View view) {

        Intent as = new Intent(this,Registercorreo.class);
        startActivity(as);
    }
}
