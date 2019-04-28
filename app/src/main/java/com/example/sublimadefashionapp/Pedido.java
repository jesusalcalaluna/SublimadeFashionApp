package com.example.sublimadefashionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sublimadefashionapp.Modelos.User;

public class Pedido extends AppCompatActivity {

    WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        browser = findViewById(R.id.webview);
        String subtotal = getIntent().getExtras().getString("subtotal");
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        browser.loadUrl("http://sublimade.mipantano.com/pagoAndroid?subtotal="+subtotal+"&id_client="+ User.id_persona);

    }

}
