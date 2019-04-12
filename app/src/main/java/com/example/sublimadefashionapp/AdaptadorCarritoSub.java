package com.example.sublimadefashionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class AdaptadorCarritoSub extends RecyclerView.Adapter<AdaptadorCarritoSub.CarritoViewHolder> {

    List<Carrito> lc;

    public AdaptadorCarritoSub(List<Carrito> lc) {
        this.lc = lc;
    }

    @NonNull
    @Override
    public CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carrito_card2, null, false);
        return new AdaptadorCarritoSub.CarritoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder carritoViewHolder, int i) {
        Carrito c = lc.get(i);

        String Subtotal = String.valueOf(c.sub_total);

        carritoViewHolder.subtotal.setText("Subtotal: "+Subtotal);
    }

    @Override
    public int getItemCount() {
        return lc.size();
    }

    public class CarritoViewHolder extends RecyclerView.ViewHolder {

        TextView subtotal;
        public CarritoViewHolder(@NonNull View itemView) {
            super(itemView);
            subtotal = itemView.findViewById(R.id.subtotal);
        }
    }
}
