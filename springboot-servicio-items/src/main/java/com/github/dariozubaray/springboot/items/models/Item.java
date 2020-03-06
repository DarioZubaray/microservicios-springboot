package com.github.dariozubaray.springboot.items.models;

public class Item {

    private int cantidad;
    private Producto producto;

    public Item() { }

    public Item(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public Double getTotal() {
        return producto.getPrecio() * cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
