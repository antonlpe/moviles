package com.example.anton.gijonwifi.Data;

/**
 * Created by Antón on 13/05/2017.
 * Esta clase define los elementos que queremos mostrar en el recyclerview y los que queremos pasar al mapa.
 * Le pasaremos al adapter una imagen y un nombre y almacenaremos la latitud y longitud del mismo
 * índice para pasarle las coordenadas al mapa, además de su descripción para mostrar en el marcador
 */


public class Items {
    private int imagen;
    private String nombre;
    private float lat;
    private float lon;

    public Items(int imagen, String nombre, float lat, float lon) {
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
