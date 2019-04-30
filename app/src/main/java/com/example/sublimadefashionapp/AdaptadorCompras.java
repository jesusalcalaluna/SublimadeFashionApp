package com.example.sublimadefashionapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sublimadefashionapp.Modelos.Pedido;

import java.util.List;

public class AdaptadorCompras extends RecyclerView.Adapter<AdaptadorCompras.ComprasViewHolder> {

    List<Pedido> lp;

    public AdaptadorCompras(List<Pedido> lp) {
        this.lp = lp;
    }

    @NonNull
    @Override
    public ComprasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carrito_card, null, false);
        return new AdaptadorCompras.ComprasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComprasViewHolder comprasViewHolder, int i) {
        Pedido p = lp.get(i);

        String F_pedido = "Fecha de pedido: "+p.getFecha_pedido();
        String F_entrega = "Fecha de entrega: "+p.getFecha_entrega();
        String Detalles = "Detalles: "+p.getDetalles();
        String Estado = "Estado: "+p.getEstado();

        comprasViewHolder.f_pedido.setText(F_pedido);
        comprasViewHolder.f_entrega.setText(F_entrega);
        comprasViewHolder.detalles.setText(Detalles);
        comprasViewHolder.estado.setText(Estado);

    }
    @Override
    public int getItemCount() {
        return lp.size();
    }

    public class ComprasViewHolder extends RecyclerView.ViewHolder {

        TextView f_pedido, f_entrega, detalles, estado;
        public ComprasViewHolder(@NonNull View itemView) {
            super(itemView);
            f_pedido = itemView.findViewById(R.id.FechaPedido);
            f_entrega = itemView.findViewById(R.id.FechaEntrega);
            detalles = itemView.findViewById(R.id.Detalles);
            estado = itemView.findViewById(R.id.EstadoPedido);

        }
    }
}
