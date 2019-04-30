package com.example.sublimadefashionapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class AdaptadorDetallesProducto extends RecyclerView.Adapter<AdaptadorDetallesProducto.ProductoViewHolder> {

    List<Producto> productos;
    Context c;
    public AdaptadorDetallesProducto(List<Producto> lp) {
        this.productos = lp;

    }

    @NonNull
    @Override
    public AdaptadorDetallesProducto.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detalles_card, null, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorDetallesProducto.ProductoViewHolder productoViewHolder, int i) {
        final Producto p = productos.get(i);

        final int Id = p.getId_producto();
        String Nombre = p.getNombre();
        String Precio = String.valueOf(p.getCosto_unitario());
        String Diseno = String.valueOf(p.getDiseno());

        productoViewHolder.nombre.setText(Nombre);
        productoViewHolder.precio.setText("MXN$"+Precio);
        productoViewHolder.diseno.setMaxHeight(250);
        productoViewHolder.diseno.setMaxHeight(450);
        productoViewHolder.chico.toggle();

        productoViewHolder.np.setMinValue(1);
        productoViewHolder.np.setMaxValue(99);

        productoViewHolder.deseado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productoViewHolder.deseado.getDrawable().getConstantState() == productoViewHolder.itemView.getResources().getDrawable(R.drawable.heart_gris_96, null).getConstantState()){
                    productoViewHolder.deseado.setImageResource(R.drawable.heart_activo_96);
                }
                else {
                    productoViewHolder.deseado.setImageResource(R.drawable.heart_gris_96);
                }
            }
        });

        productoViewHolder.anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(productoViewHolder.itemView.getContext(), "Producto añadido al carrito", Toast.LENGTH_LONG).show();
                JSONObject prod = new JSONObject();
                try {
                    prod.put("id", Id);
                    prod.put("id_usuario", User.id_persona);
                    prod.put("cantidad", productoViewHolder.np.getValue());
                    if(productoViewHolder.chico.isChecked()){
                        prod.put("talla", productoViewHolder.chico.getText());
                    }
                    if(productoViewHolder.mediano.isChecked()){
                        prod.put("talla", productoViewHolder.mediano.getText());
                    }
                    if(productoViewHolder.grande.isChecked()){
                        prod.put("talla", productoViewHolder.grande.getText());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, "http://sublimade.mipantano.com/android/addcarrito", prod, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.getMessage());
                    }
                });

                VolleyS.getInstance(productoViewHolder.itemView.getContext()).getRq().add(jor);

            }
        });



        Picasso.get().load("http://sublimade.mipantano.com/storage/disenos/"+Diseno).resize(350, 320).into(productoViewHolder.diseno, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError(Exception e) {
            }
        });
        productoViewHolder.deseado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productoViewHolder.deseado.getDrawable().getConstantState() == productoViewHolder.itemView.getResources().getDrawable(R.drawable.heart_gris_96, null).getConstantState()){
                    productoViewHolder.deseado.setImageResource(R.drawable.heart_activo_96);
                    datos.StatusCorazon=1;
                    datos.id_boorrardeseado= p.getId_producto();
                    datos.id_prod=p.getId_producto();

                    try {
                        Regitardeseados();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else {
                    productoViewHolder.deseado.setImageResource(R.drawable.heart_gris_96);
                    datos.StatusCorazon=2;
                    try {
                        Borrardeseados();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void Borrardeseados() throws JSONException {
                JSONObject deseado = new JSONObject();
                deseado.put("id", datos.id_boorrardeseado);

                String url = "http://www.sublimade.mipantano.com/api/android.borrardeseado";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, deseado, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null){

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleyS.getInstance(productoViewHolder.itemView.getContext()).getRq().add(request);

            }

            private void Regitardeseados() throws JSONException {
                JSONObject deseado = new JSONObject();
                deseado.put("productos_id_producto",datos.id_prod);
                deseado.put("usuarios_id_persona",User.id_persona);



                String url = "http://www.sublimade.mipantano.com/api/android.obtenerproducto";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, deseado, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response!=null){

                            try {
                                datos.id_deseado=response.getString("usuario_id_persona");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //  Toast.makeText(productoViewHolder.itemView.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                            productoViewHolder.deseado.setImageResource(R.drawable.heart_activo_96);


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



    }


    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio;
        ImageView diseno, deseado;
        NumberPicker np;
        ImageButton anadir;
        RadioGroup talla;
        RadioButton chico, mediano, grande;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdName);
            precio = itemView.findViewById(R.id.ProdPrice);
            diseno = itemView.findViewById(R.id.ProdImage);
            np = itemView.findViewById(R.id.Quantity);
            deseado = itemView.findViewById(R.id.deseado);
            anadir = itemView.findViewById(R.id.btnAdd);
            talla = itemView.findViewById(R.id.ProdSize);
            chico = itemView.findViewById(R.id.SizeSmall);
            mediano = itemView.findViewById(R.id.SizeMedium);
            grande = itemView.findViewById(R.id.SizeLarge);


        }
    }
}
