package com.cmmr.rentgo.Utilitys;

public class Producto {
    private String title;
    private int imageResId;
    private String descripcion;
    private Double precio;


    public Producto(String title, int imageResId, String descripcion, Double precio) {
        this.title = title;
        this.imageResId = imageResId;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
