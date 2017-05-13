package com.example.anton.gijonwifi.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Antón on 30/03/2017.
 * Clase para acceder a cada uno de los elementos del array. Los campos se corresponden con los del fichero JSON
 * con el nombre exacto o con el nombre serializado. Los objetos cuyo tipo de dato es otra clase se explican en la línea
 * en la que se instancian
 */

public class ObjetoWifi {

    @SerializedName("correo-electronico")
    private String correoElectronico;
    //accede al campo "localizacion", cuyo primer campo contiene las coordenadas del punto wifi
    private Localizacion localizacion;
    private String tipo;
    //accede al campo "nombre", cuyo primer campo contiene el nombre de la localización del punto wifi
    private Nombre nombre;


    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Localizacion getLocalizacion(){
        return this.localizacion;
    }
    public void setLocalizacion(){
        this.localizacion=localizacion;
    }

    public Nombre getNombre(){return this.nombre;}
    public void setNombre(){this.nombre=nombre;}



}
