package com.example.sublimadefashionapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sublimadefashionapp.Modelos.Pedido;

import java.util.List;

public class AdaptadorPedidos extends RecyclerView.Adapter<AdaptadorPedidos.PedidosViewHolder>{
    List<Pedido> pedido;
    Context context;

    public AdaptadorPedidos(List<Pedido> pedido, Context context){
        this.pedido=pedido;
        this.context=context;
    }

    @NonNull
    @Override
    public PedidosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosViewHolder pedidosViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class PedidosViewHolder extends RecyclerView.ViewHolder{

        TextView TxtFechaPedido, TxtEstadoPedido, TxtTotalPedido;


        public PedidosViewHolder(@NonNull View itemView) {

            super(itemView);


        }
    }
}
