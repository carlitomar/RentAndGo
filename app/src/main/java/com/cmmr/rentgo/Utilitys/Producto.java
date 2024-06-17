package com.cmmr.rentgo.Utilitys;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    private String title;
    private Uri imageUri;
    private String descripcion;
    private double precio;
    private String categoria;

    public Producto(String title, Uri imageUri, String descripcion, double precio, String categoria) {
        this.title = title;
        this.imageUri = imageUri;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    protected Producto(Parcel in) {
        title = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        descripcion = in.readString();
        precio = in.readDouble();
        categoria = in.readString();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeParcelable(imageUri, flags);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
        dest.writeString(categoria);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
// Getters and setters if needed
}
