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

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adaptadorinicio extends RecyclerView.Adapter<adaptadorinicio.ProductoViewHolder> {


    List<Producto> productos;
    Context c;

    public adaptadorinicio(List<Producto> productos, Context c) {
        this.productos = productos;
        this.c = c;
    }


    @NonNull
    @Override
    public adaptadorinicio.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inicio_card, null, false);
        return new ProductoViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final adaptadorinicio.ProductoViewHolder productoViewHolder, int i) {
        Producto p = productos.get(i);

        final int Id = p.getId_producto();
        String Nombre = p.getNombre();
        String Precio = String.valueOf(p.getCosto_unitario());
        String Categoria = String.valueOf(p.getCategoria());
        String Diseno =  String.valueOf(p.getDiseno());


        productoViewHolder.nombre.setText(Nombre);
        productoViewHolder.precio.setText(Precio);
        productoViewHolder.categoria.setText(Categoria);
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
