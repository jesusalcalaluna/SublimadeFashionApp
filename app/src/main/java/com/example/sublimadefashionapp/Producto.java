package com.example.sublimadefashionapp;

public class Producto {
    int id_producto;
    String nombre;
    double costo_unitario;
    String id_diseño;
    int id_tipo_producto;
    String sexo;
    String categoria;



    public Producto(String categoria, int id_producto, String nombre, double costo_unitario, String id_diseño, int id_tipo_producto, String sexo) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.costo_unitario = costo_unitario;
        this.id_diseño = id_diseño;
        this.id_tipo_producto = id_tipo_producto;
        this.sexo = sexo;
        this.categoria = categoria;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto_unitario() {
        return costo_unitario;
    }

    public void setCosto_unitario(double costo_unitario) {
        this.costo_unitario = costo_unitario;
    }

    public String getId_diseño() {
        return id_diseño;
    }

    public void setId_diseño(String id_diseño) {
        this.id_diseño = id_diseño;
    }

    public int getId_tipo_producto() {
        return id_tipo_producto;
    }

    public void setId_tipo_producto(int id_tipo_producto) {
        this.id_tipo_producto = id_tipo_producto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }



}
