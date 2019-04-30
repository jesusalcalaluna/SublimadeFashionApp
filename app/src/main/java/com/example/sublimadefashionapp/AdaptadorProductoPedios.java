package com.example.sublimadefashionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sublimadefashionapp.R;

public class AdaptadorProductoPedios {

    public class ProductoPedidosViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, precio, categoria;
        ImageView diseno, deseado;
        CardView cd;


        public ProductoPedidosViewHolder(@NonNull View itemView) {

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
