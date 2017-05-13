package com.example.anton.gijonwifi.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Antón on 07/04/2017.
 */

public class Localizacion {
    @SerializedName("content")
    private String coordenadas;

    public String getCoordenadas(){
        return this.coordenadas;
    }
    public void setCoordenadas(String coordenadas){
        this.coordenadas=coordenadas;
    }
}