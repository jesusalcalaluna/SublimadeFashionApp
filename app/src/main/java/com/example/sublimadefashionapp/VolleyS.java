package com.example.sublimadefashionapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {
    private RequestQueue rq;
    private static VolleyS vs = null;

    private VolleyS(Context context){
        rq = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context){
        if(vs==null){
            vs =  new VolleyS(context);
        }
        return vs;
    }
    public RequestQueue getRq(){
        return rq;
    }
}
