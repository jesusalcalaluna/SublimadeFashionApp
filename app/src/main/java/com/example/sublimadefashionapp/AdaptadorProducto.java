package com.example.sublimadefashionapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ProductoViewHolder> {

    List<Producto> productos;

    public AdaptadorProducto(List<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public AdaptadorProducto.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catalogo_card, null, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProducto.ProductoViewHolder productoViewHolder, int i) {
        Producto p = productos.get(i);

        String Nombre = p.getNombre();
        String Precio = String.valueOf(p.getCosto_unitario());
        String Categoria = String.valueOf(p.getCategoria());

        productoViewHolder.nombre.setText(Nombre);
        productoViewHolder.precio.setText(Precio);
        productoViewHolder.categoria.setText(Categoria);

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio, categoria;
        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdName);
            precio = itemView.findViewById(R.id.ProdPrice);
            categoria = itemView.findViewById(R.id.ProdCat);

        }
    }
}
