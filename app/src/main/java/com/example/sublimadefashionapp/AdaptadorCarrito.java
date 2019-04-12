package com.example.sublimadefashionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull CarritoViewHolder carritoViewHolder, int i) {
        Carrito c = lc.get(i);

        String Nombre = c.getNombre();
        String Precio = String.valueOf(c.getCosto_unitario());
        String Categoria = String.valueOf(c.getCategoria());
        String Diseno =  String.valueOf(c.getDiseno());
        String Talla = String.valueOf(c.getTalla());
        String Cantidad = String.valueOf(c.getCantidad());
        String Total = String.valueOf(c.getTotal());

        carritoViewHolder.nombre.setText(Nombre);
        carritoViewHolder.precio.setText(Precio);
        carritoViewHolder.categoria.setText(Categoria);
        carritoViewHolder.cantidad.setText("Cantidad: "+Cantidad);
        carritoViewHolder.talla.setText("Talla: "+Talla);
        carritoViewHolder.total.setText("Total: "+Total);

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

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdName);
            precio = itemView.findViewById(R.id.ProdPrice);
            categoria = itemView.findViewById(R.id.ProdCat);
            diseno = itemView.findViewById(R.id.ProdImage);
            talla = itemView.findViewById(R.id.talla_cart);
            cantidad = itemView.findViewById(R.id.cant_cart);
            total = itemView.findViewById(R.id.total_cart);

        }
    }
}
