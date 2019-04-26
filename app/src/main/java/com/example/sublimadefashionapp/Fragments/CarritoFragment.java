package com.example.sublimadefashionapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sublimadefashionapp.AdaptadorCarrito;
import com.example.sublimadefashionapp.AdaptadorCarritoSub;
import com.example.sublimadefashionapp.AdaptadorProducto;
import com.example.sublimadefashionapp.Carrito;
import com.example.sublimadefashionapp.MainActivity;
import com.example.sublimadefashionapp.Modelos.User;
import com.example.sublimadefashionapp.Producto;
import com.example.sublimadefashionapp.R;
import com.example.sublimadefashionapp.VolleyS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarritoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarritoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CarritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarritoFragment newInstance(String param1, String param2) {
        CarritoFragment fragment = new CarritoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrito, container, false);

       final RecyclerView rvCarrito = view.findViewById(R.id.rvCarrito);
       final TextView subtotal = view.findViewById(R.id.subtotal);

        String id = User.id_persona;
        JSONArray objeto = new JSONArray();
        try {
            objeto.put(1, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        rvCarrito.setLayoutManager(new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL,false));
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.POST, "http://sublimade.mipantano.com/android/carrito", objeto,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if(response.length() == 0) {
                                Intent intent=new Intent(getContext(), MainActivity.class);
                                intent.putExtra("abrirfragmentcatalogo1","texto");
                                startActivity(intent);
                                Toast.makeText(getContext(), "No hay nada", Toast.LENGTH_SHORT).show();
                            }
                            Gson g = new Gson();
                            Type t = new TypeToken<List<Carrito>>(){}.getType();
                            List<Carrito> lc = g.fromJson(response.toString(), t);
                            AdaptadorCarrito adapt= new AdaptadorCarrito(lc);
                            rvCarrito.setAdapter(adapt);
                            Toast.makeText(getContext(), String.valueOf(lc.get(0).sub_total), Toast.LENGTH_LONG).show();
                            String valor = "Subtotal: MXN$"+lc.get(0).sub_total;
                            subtotal.setText(valor);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Aun no haz agregado productos", Toast.LENGTH_SHORT).show();
                Log.d("error", error.getMessage());

            }
        });
        VolleyS.getInstance(getContext()).getRq().add(jar);

        return view;
    }

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
