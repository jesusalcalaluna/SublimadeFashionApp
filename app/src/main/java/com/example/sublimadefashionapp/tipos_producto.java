package com.example.sublimadefashionapp;

public class tipos_producto {

    public int id_tipo_producto;
    public String nombre;

    public tipos_producto(int id_tipo_producto, String nombre){

        this.id_tipo_producto=id_tipo_producto;
        this.nombre=nombre;

    }

    public int getId_tipo_producto() {
        return id_tipo_producto;
    }

    public String getNombre(){
        return nombre;
    }

    public void setId_tipo_producto(int id_tipo_producto) {
        this.id_tipo_producto = id_tipo_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
