package com.example.sublimadefashionapp;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorCarrito extends RecyclerView.Adapter<AdaptadorCarrito.CarritoViewHolder> {
    List<Producto> productoList;

    public AdaptadorCarrito(List<Producto> productos){
        this.productoList= productos;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carrito_card, null, false);
        return new AdaptadorCarrito.CarritoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder carritoViewHolder, int i) {
        Producto p = productoList.get(i);

        String Nombre = p.getNombre();
        String Precio = String.valueOf(p.getCosto_unitario());
        String Categoria = String.valueOf(p.getCategoria());
        String Diseno =  String.valueOf(p.getDiseno());

        carritoViewHolder.nombre.setText(Nombre);
        carritoViewHolder.precio.setText(Precio);
        carritoViewHolder.categoria.setText(Categoria);
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class CarritoViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio, categoria;
        ImageView diseno;

        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ProdNameCarrito);
            precio = itemView.findViewById(R.id.ProdPriceCarrito);
            categoria = itemView.findViewById(R.id.ProdCarrito);
            diseno = itemView.findViewById(R.id.ProdImageCarrito);
        }
    }
}
