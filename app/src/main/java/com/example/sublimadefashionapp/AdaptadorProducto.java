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

    public static class ProductoViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView  Nombre;
        TextView Precio;
        TextView Tipo;
        ImageView Imagen;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cvCatalogo);
            Nombre = (TextView) itemView.findViewById(R.id.ProdName);
            Precio = (TextView) itemView.findViewById(R.id.ProdPrice);
            Tipo = (TextView) itemView.findViewById(R.id.ProdType);
            Imagen = (ImageView) itemView.findViewById(R.id.ProdImage);

        }
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_catalogo, viewGroup, false);
        ProductoViewHolder pvh = new ProductoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder productoViewHolder, int i) {
        productoViewHolder.Nombre.setText(productos.get(i).getNombre());
        productoViewHolder.Precio.setText(String.valueOf(productos.get(i).getCosto_unitario()));
        productoViewHolder.Tipo.setText(productos.get(i).getId_tipo_producto());

    }

    @Override
    public long getItemId(int position) {
        return productos.get(position).getId_producto();
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }


}
