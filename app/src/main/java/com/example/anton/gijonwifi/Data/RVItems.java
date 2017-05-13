package com.example.anton.gijonwifi.Data;

/**
 * Created by Antón on 13/05/2017.
 */


public class RVItems {
    private int imagen;
    private String nombre;
    private float lat;
    private float lon;

    public RVItems(int imagen, String nombre, float lat, float lon) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;

    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }



}
