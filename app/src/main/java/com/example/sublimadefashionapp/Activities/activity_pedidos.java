package com.example.sublimadefashionapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.example.sublimadefashionapp.R;

public class activity_pedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        Toolbar mayToobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mayToobar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabpedidos);
        tabLayout.addTab(tabLayout.newTab().setText("En curso"));
        tabLayout.addTab(tabLayout.newTab().setText("Recibidos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerPedidos);

    }

    private void setSupportActionBar(Toolbar mayToobar) {
    }
}
