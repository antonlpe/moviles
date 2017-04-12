package com.example.anton.gijonwifi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Antón on 11/04/2017.
 * Clase que obtiene el campo del nombre de la ubicación
 */

public class Nombre {
    @SerializedName("content")
    private String nombreMarcador;

    public String getNombreMarcador(){
        return this.nombreMarcador;
    }
    public void setNombreMarcador(String nombreMarcador){
        this.nombreMarcador=nombreMarcador;
    }
}