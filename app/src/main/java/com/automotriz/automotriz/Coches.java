package com.automotriz.automotriz;

/**
 * Created by elias on 27/10/2016.
 */

public class Coches {
    String marca;
    String nombre;
    int precio;


    public Coches(String marca, String nombre, int precio) {
        this.marca = marca;
        this.nombre = nombre;
        this.precio = precio;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
