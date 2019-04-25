package com.example.sublimadefashionapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sublimadefashionapp.Fragments.PedidosEncursoFragment;
import com.example.sublimadefashionapp.Fragments.PedidosRecibidosFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs= numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return null;
            case 1:
                return null;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
