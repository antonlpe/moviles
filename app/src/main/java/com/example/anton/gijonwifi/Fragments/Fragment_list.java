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
import com.example.anton.gijonwifi.Data.RVItems;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Fragment_list extends Fragment {
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";
    View rootView;
    Context context;

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
        // creas la lista con las imagenes (aqui pongo unas a bulto) y el texto
        final List items = new ArrayList<>();
        //AQUI TENGO Q PONER MIS DATOS QUE SE IMPORTAN DEL JSON
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
                        items.add(new RVItems(R.drawable.ic_audiotrack, descripcion, lat, lon));
                    }



                }

                // en los fragments tienes que usar la vista para buscar el item, en este caso rootView es la vista principal del fragment
                mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
                // el sethasfixedsize es para decirle que la lista de items para el recyclerview es fija, puedes cambiarlo en caso de que no lo sea
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(context); // si quieres que los items salgan en cuadrados como si fuese una galeria de fotos cambia el linearlayoutmanager por gridlayoutmanager
                mRecyclerView.setLayoutManager(mLayoutManager);
                // llamamos al adapter, le pasamos el contexto y la lista de items. el context lo usaremos para lanzar la segunda actividad desde la pulsacion de un item
                adapter = new Adapter(context,items);
                mRecyclerView.setAdapter(adapter);
            }//onResponse
        };//response

        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(context).addToRequestQueue(request);



    }
}
