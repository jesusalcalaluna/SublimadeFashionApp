package com.example.sublimadefashionapp.Modelos;

import java.util.Date;

public class Pedido {
    int reg_pedido;
    int id_cliente;
    Date fecha_pedido;
    Date fecha_entrega;
    String detalles;
    String estado;
    Date fecha_real_entrega;

    public Pedido(int reg_pedido, int id_cliente, Date fecha_pedido, Date fecha_entrega, String detalles, String estado, Date fecha_real_entrega){
        this.reg_pedido=reg_pedido;
        this.id_cliente=id_cliente;
        this.fecha_pedido=fecha_pedido;
        this.fecha_entrega=fecha_entrega;
        this.detalles=detalles;
        this.estado=estado;
        this.fecha_real_entrega=fecha_real_entrega;
    }
    public int getReg_pedido (){
        return reg_pedido;
    }
    public int getId_cliente (){
        return id_cliente;
    }
    public Date getFecha_pedido (){
        return fecha_pedido;
    }
    public Date getFecha_entrega (){
        return fecha_entrega;
    }
    public String getDetalles (){
        return detalles;
    }
    public String getEstado (){
        return estado;
    }
    public Date getFecha_real_entrega (){
        return fecha_real_entrega;
    }
    public void setReg_pedido (int reg_pedido){
        this.reg_pedido= reg_pedido;
    }
    public void setId_cliente (int id_cliente){
        this.id_cliente= id_cliente;
    }
    public void setFecha_pedido (Date fecha_pedido){
        this.fecha_pedido= fecha_pedido;
    }
    public void setFecha_entrega (Date fecha_entrega){
        this.fecha_entrega= fecha_entrega;
    }
    public void setDetalles (String detalles){
        this.detalles= detalles;
    }
    public void setEstado (String estado){
        this.estado= estado;
    }
    public void setFecha_real_entrega (Date fecha_real_entrega){
        this.fecha_real_entrega= fecha_real_entrega;
    }
}
