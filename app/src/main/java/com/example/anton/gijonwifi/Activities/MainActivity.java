package com.example.anton.gijonwifi.Activities;

import android.content.Intent;
import com.example.anton.gijonwifi.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;

import com.example.anton.gijonwifi.Data.Datos;
import com.example.anton.gijonwifi.Data.GsonRequest;
import com.example.anton.gijonwifi.Data.VolleyManager;
import com.example.anton.gijonwifi.Fragments.Fragment_list;


public class MainActivity extends AppCompatActivity  {
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";

    TextView lvlista = null;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mostramos el fragmento con la lista en esta actividad
        // (de momento, cuando esté el menú, ponerlo aquí y esto será otra actividad)
        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.conten_fragmento,new Fragment_list());
        tran.commit();

        //lvlista = (ListView) findViewById(R.id.lvlista);
        /*

        //creo objeto respuesta
        Response.Listener<Datos> response = new Response.Listener<Datos>() {
            @Override
            public void onResponse(final Datos response) {

                //la sintaxix siguiente muestra marcador y etiqueta
                //geo:41.3825581,2.1704375?z=16&q=41.3825581,2.1704375(Barcelona)

                final String [] nombres = new String[67];
                for(int i=0;i<67;i++){
                    nombres[i]=response.getDirectorio().get(i).getNombre().getNombreMarcador();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, nombres);
                lvlista.setAdapter(adapter);

                //pulsador
                lvlista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {

                        String localizacion = response.getDirectorio().get(arg2).getLocalizacion().getCoordenadas();
                        String delimitador = "[ ]+";
                        String[] latlong = localizacion.split(delimitador);
                        String latitud = latlong[0];
                        String longitud = latlong[1];
                        System.out.println(latitud);
                        System.out.println(longitud);
                        String descripcion = response.getDirectorio().get(arg2).getNombre().getNombreMarcador();
                        float lat = Float.parseFloat(latitud);
                        float lon = Float.parseFloat(longitud);

                        Intent intent = new Intent(MainActivity.this,GoogleMaps.class);
                        intent.putExtra("latitud",lat);
                        intent.putExtra("longitud",lon);
                        intent.putExtra("descripcion",descripcion);
                        startActivity(intent);
                    }
                });

            }//onResponse
        };//response


        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(this).addToRequestQueue(request);
        */
    }//onCreate

}