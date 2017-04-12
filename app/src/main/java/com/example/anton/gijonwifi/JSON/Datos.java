package com.example.anton.gijonwifi.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antón on 05/04/2017.
 * Clase que accede al array de elementos en los que se encuentra la información
 * cada ObjetoWifi contiene la información de uno de los puntos wifi
 */

public class Datos {
    private List<ObjetoWifi> directorio = new ArrayList<>();


    public List<ObjetoWifi> getDirectorio() {
        return directorio;
    }

    public void setDirectorio(List<ObjetoWifi> directorio) {
        this.directorio = directorio;
    }

}