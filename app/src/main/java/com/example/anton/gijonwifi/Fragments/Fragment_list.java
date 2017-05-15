package com.example.anton.gijonwifi.Fragments;

import android.content.Context;

import com.android.volley.Response;
import com.example.anton.gijonwifi.Data.Datos;
import com.example.anton.gijonwifi.Data.GsonRequest;
import com.example.anton.gijonwifi.Data.VolleyManager;
import com.example.anton.gijonwifi.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.anton.gijonwifi.Data.Adapter;
import com.example.anton.gijonwifi.Data.Items;

import java.util.ArrayList;
import java.util.List;

//Fragmento que contiene la lista de las ubicaciones wifi y que tras pulsarlo lanza la actividad GoogleMaps
public class Fragment_list extends Fragment {
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";
    View rootView;
    Context context;
    //código de transparencias de teoría sobre uso de recyclerview
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public Fragment_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);

        context = getActivity();

        initRecyclerView();

        return rootView;
    }

    private void initRecyclerView() {

        final List items = new ArrayList<>();
        //dentro del response, va la implementación que necesita del acceso a los datos del JSON
        //primero rellenamos la lista y pasamos los datos al Adapter y al RecyclerView
        Response.Listener<Datos> response = new Response.Listener<Datos>() {

            @Override
            public void onResponse(final Datos response) {
                //obtener por separado latitud y longitud
                for(int i=0;i<67;i++){
                    String localizacion = response.getDirectorio().get(i).getLocalizacion().getCoordenadas();
                    if(localizacion != null){
                        String delimitador = "[ ]+";
                        String[] latlong = localizacion.split(delimitador);
                        String latitud = latlong[0];
                        String longitud = latlong[1];
                        String descripcion = response.getDirectorio().get(i).getNombre().getNombreMarcador();
                        float lat = Float.parseFloat(latitud);
                        float lon = Float.parseFloat(longitud);
                        items.add(new Items(R.drawable.wifi, descripcion, lat, lon));
                    }
                }

                //código de transparencias de teoría sobre uso de recyclerview
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(context); // para que los items salgan en cuadrados como si fuese una galeria de fotos cambiar el linearlayoutmanager por gridlayoutmanager
                mRecyclerView.setLayoutManager(mLayoutManager);
                //el context para lanzar la segunda actividad desde la pulsacion de un item
                adapter = new Adapter(context,items);
                mRecyclerView.setAdapter(adapter);
            }//onResponse
        };//response

        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(context).addToRequestQueue(request);



    }
}
