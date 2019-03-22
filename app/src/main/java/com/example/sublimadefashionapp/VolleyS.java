package com.example.sublimadefashionapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {
    private RequestQueue rq;
    private static VolleyS vs = null;

    private VolleyS(Context c){
        rq = Volley.newRequestQueue(c);
    }

    public static VolleyS getInstance(Context c){
        if(vs==null){
            vs =  new VolleyS(c);
        }
        return vs;
    }
    public RequestQueue getRq(){
        return rq;
    }
}
