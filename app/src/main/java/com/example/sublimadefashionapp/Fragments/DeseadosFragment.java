package com.example.sublimadefashionapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sublimadefashionapp.Modelos.User;
import com.example.sublimadefashionapp.Producto;
import com.example.sublimadefashionapp.R;
import com.example.sublimadefashionapp.VolleyS;
import com.example.sublimadefashionapp.adaptadordeseado;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeseadosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeseadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeseadosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_CATEGORIA="categoria";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String categoria;
    private OnFragmentInteractionListener mListener;

    public DeseadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeseadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeseadosFragment newInstance(String param1, String param2,String categoria) {
        DeseadosFragment fragment = new DeseadosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_CATEGORIA,categoria);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            categoria=getArguments().getString(ARG_CATEGORIA);
        }
    }
RecyclerView rvDeseados;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deseados, container, false);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.refresh);
         rvDeseados = view.findViewById(R.id.rvdeseado);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    descargarcatalogo();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                rvDeseados.setLayoutManager(new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL,false));
                rvDeseados.setHasFixedSize(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },4000);
            }
        });

        try {
            rvDeseados.setLayoutManager(new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL,false));
            rvDeseados.setHasFixedSize(true);
            descargarcatalogo();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void descargarcatalogo() throws JSONException {
        JSONArray prod=new JSONArray();

            prod.put(1, User.id_persona);

        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.POST, "http://sublimade.mipantano.com/api/android.obtenerproductodeseado", prod,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        Gson g = new Gson();
                        Type t = new TypeToken<List<Producto>>() {
                        }.getType();
                        List<Producto> lp = g.fromJson(response.toString(), t);
                        adaptadordeseado adapt = new adaptadordeseado(lp, getContext());
                        rvDeseados.setAdapter(adapt);
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
            }
        });
        VolleyS.getInstance(getContext()).getRq().add(jar);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_catalogo_filtros,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
