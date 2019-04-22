package com.example.sublimadefashionapp;

public class Carrito {
    public int id_carrito;
    public double sub_total;
    public int id_producto;
    public int cantidad;
    public int total;
    public String talla;
    String nombre;
    double costo_unitario;
    public int reg;

    public Carrito(int reg, int id_carrito, double sub_total, int id_producto, int cantidad, int total, String talla, String nombre, double costo_unitario, String id_diseño, int id_tipo_producto, String categoria, String diseno) {
        this.id_carrito = id_carrito;
        this.sub_total = sub_total;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.total = total;
        this.talla = talla;
        this.nombre = nombre;
        this.costo_unitario = costo_unitario;
        this.id_diseño = id_diseño;
        this.id_tipo_producto = id_tipo_producto;
        this.categoria = categoria;
        this.diseno = diseno;
        this.reg = reg;
    }

    String id_diseño;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDiseno() {
        return diseno;
    }

    public void setDiseno(String diseno) {
        this.diseno = diseno;
    }

    int id_tipo_producto;
    String categoria;
    String diseno;



    public int getId_carrito() {
        return id_carrito;
    }

    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
    public int getReg() { return reg; }

    public void setReg(int reg) { this.reg = reg; }




}
