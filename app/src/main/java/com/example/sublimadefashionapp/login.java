package com.example.sublimadefashionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity implements View.OnClickListener {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);

    }

    public void onClick(View view) {

        Intent sig = new Intent(this, Register.class);
        startActivity(sig);
    }
}