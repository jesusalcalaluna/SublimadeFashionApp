package com.example.sublimadefashionapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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
        Producto p = productos.get(i);
        String Nombre = p.getNombre();
        String Precio = String.valueOf(p.getCosto_unitario());
        String Diseno = String.valueOf(p.getDiseno());

        productoViewHolder.nombre.setText(Nombre);
        productoViewHolder.precio.setText(Precio);

        productoViewHolder.np.setMinValue(1);
        productoViewHolder.np.setMaxValue(99);



        Picasso.get().load("http://sublimade.mipantano.com/storage/disenos/"+Diseno).into(productoViewHolder.diseno, new Callback() {
            @Override
            public void onSuccess() {
                Toast.makeText(productoViewHolder.itemView.getContext(), "Simon", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(productoViewHolder.itemView.getContext(), "Nel", Toast.LENGTH_LONG).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio;
        ImageView diseno;
        NumberPicker np;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdName);
            precio = itemView.findViewById(R.id.ProdPrice);
            diseno = itemView.findViewById(R.id.ProdImage);
            np = itemView.findViewById(R.id.Quantity);

        }
    }
}
