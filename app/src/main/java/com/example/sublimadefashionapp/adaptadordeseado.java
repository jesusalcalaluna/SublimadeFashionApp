package com.example.sublimadefashionapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sublimadefashionapp.Modelos.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class adaptadordeseado  extends RecyclerView.Adapter<adaptadordeseado.ProductoViewHolder>{


    List<Producto> productos;
    Context c;

    public adaptadordeseado(List<Producto> productos, Context c) {
        this.productos = productos;
        this.c = c;
    }
    @NonNull
    @Override
    public adaptadordeseado.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.deseados_card, null, false);
        return new adaptadordeseado.ProductoViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final adaptadordeseado.ProductoViewHolder productoViewHolder, int i) {
        Producto  p = productos.get(i);


        final  int Id = p.getId_producto();
        String Nombre = p.getNombre();
        String Precio = String.valueOf(p.getCosto_unitario());
        String Categoria = String.valueOf(p.getCategoria());
        String Diseno =  String.valueOf(p.getDiseno());

        datos.id_prod=Id;
        productoViewHolder.nombre.setText(Nombre);
        productoViewHolder.precio.setText(Precio);
        productoViewHolder.categoria.setText(Categoria);
        productoViewHolder.deseado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productoViewHolder.deseado.getDrawable().getConstantState() == productoViewHolder.itemView.getResources().getDrawable(R.drawable.heart_gris_96, null).getConstantState()){
                    productoViewHolder.deseado.setImageResource(R.drawable.heart_activo_96);
                    try {
                        Regitardeseados();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else {
                    productoViewHolder.deseado.setImageResource(R.drawable.heart_gris_96);
                }
            }

            private void Regitardeseados() throws JSONException {
                JSONObject deseado = new JSONObject();
                deseado.put("productos_id_producto",datos.id_prod);
                deseado.put("usuarios_id_persona", User.id_persona);



                String url = "http://www.sublimade.mipantano.com/api/android.obtenerproducto";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, deseado, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null){

                            try {
                                datos.id_deseado=response.getInt("id_deseados");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(productoViewHolder.itemView.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                            //  Intent intent = new Intent(login.this,MainActivity.class);
                            //startActivity(intent);
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  Toast.makeText(login.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                VolleyS.getInstance(productoViewHolder.itemView.getContext()).getRq().add(request);
            }

        });


        productoViewHolder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(productoViewHolder.itemView.getContext(), "Clic", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(productoViewHolder.itemView.getContext(), DetallesProducto.class);
                i.putExtra("id", Id);
                c.startActivity(i);

            }
        });
        Picasso.get().load("http://sublimade.mipantano.com/storage/disenos/"+Diseno).into(productoViewHolder.diseno, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError(Exception e) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, categoria;
        ImageView diseno, deseado;
        CardView cd;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdName);
            precio = itemView.findViewById(R.id.ProdPrice);
            categoria = itemView.findViewById(R.id.ProdCat);
            diseno = itemView.findViewById(R.id.ProdImage);
            cd = itemView.findViewById(R.id.cvCatalogo);
            deseado = itemView.findViewById(R.id.deseado);
        }
    }
}