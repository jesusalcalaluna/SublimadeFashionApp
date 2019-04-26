package com.example.sublimadefashionapp.Adapters;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sublimadefashionapp.Carrito;
import com.example.sublimadefashionapp.Modelos.User;
import com.example.sublimadefashionapp.R;
import com.example.sublimadefashionapp.VolleyS;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdaptadorCarrito extends RecyclerView.Adapter<AdaptadorCarrito.CarritoViewHolder> {
    List<Carrito> lc;

    public AdaptadorCarrito(List<Carrito> productos){
        this.lc= productos;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carrito_card, null, false);
        return new AdaptadorCarrito.CarritoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarritoViewHolder carritoViewHolder, final int i) {
        final Carrito c = lc.get(i);

        String Nombre = c.getNombre();
        String Precio = String.valueOf(c.getCosto_unitario());
        String Categoria = String.valueOf(c.getCategoria());
        String Diseno =  String.valueOf(c.getDiseno());
        String Talla = String.valueOf(c.getTalla());
        String Cantidad = String.valueOf(c.getCantidad());
        String Total = String.valueOf(c.getTotal());
        final int reg = c.getReg();
        final int id_prod = c.getId_producto();

        carritoViewHolder.nombre.setText(Nombre);
        carritoViewHolder.precio.setText(Precio);
        carritoViewHolder.categoria.setText(Categoria);
        carritoViewHolder.cantidad.setText("Cantidad: "+Cantidad);
        carritoViewHolder.talla.setText("Talla: "+Talla);
        carritoViewHolder.total.setText("Total: "+Total);
        carritoViewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject datos = new JSONObject();
                try {
                    datos.put("id_prod", id_prod);
                    datos.put("id_carr", User.id_persona);
                    datos.put("reg", reg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, "http://sublimade.mipantano.com/android/eliminarcarrito", datos, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.getMessage());
                    }
                });

                VolleyS.getInstance(carritoViewHolder.itemView.getContext()).getRq().add(jor);

                Toast.makeText(carritoViewHolder.itemView.getContext(), "Producto eliminado del carrito", Toast.LENGTH_LONG).show();
                lc.remove(i);
                notifyDataSetChanged();

            }
        });

        Picasso.get().load("http://sublimade.mipantano.com/storage/disenos/"+Diseno).into(carritoViewHolder.diseno, new Callback() {
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
        return lc.size();
    }

    public class CarritoViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio, categoria, talla, cantidad, total;
        ImageView diseno;
        Button eliminar;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdName);
            precio = itemView.findViewById(R.id.ProdPrice);
            categoria = itemView.findViewById(R.id.ProdCat);
            diseno = itemView.findViewById(R.id.ProdImage);
            talla = itemView.findViewById(R.id.talla_cart);
            cantidad = itemView.findViewById(R.id.cant_cart);
            total = itemView.findViewById(R.id.total_cart);
            eliminar = itemView.findViewById(R.id.deleteProd);

        }
    }
}
