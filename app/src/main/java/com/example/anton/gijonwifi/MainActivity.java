package com.example.anton.gijonwifi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.anton.gijonwifi.JSON.Datos;
import com.example.anton.gijonwifi.JSON.GsonRequest;
import com.example.anton.gijonwifi.JSON.VolleyManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";


    Button btmapa = null;
    TextView tx = null;
    ListView lvlista = null;



    //metodo que crea un Intent que llama a maps pasándole una localización
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = (TextView) findViewById(R.id.txprueba);
        btmapa = (Button) findViewById(R.id.btmapa);
        lvlista = (ListView) findViewById(R.id.lvlista);


        //creo objeto respuesta
        Response.Listener<Datos> response = new Response.Listener<Datos>() {
            @Override
            public void onResponse(final Datos response) {

                //la sintaxix siguiente muestra marcador y etiqueta
                //geo:41.3825581,2.1704375?z=16&q=41.3825581,2.1704375(Barcelona)

                String [] nombres = new String[67];
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
                        String s = "geo:"+response.getDirectorio().get(arg2).getLocalizacion().getCoordenadas()+
                                "?z=+16&q="+response.getDirectorio().get(arg2).getLocalizacion().getCoordenadas()+
                                "("+response.getDirectorio().get(arg2).getNombre().getNombreMarcador()+")";

                        Uri myUri = Uri.parse(s);
                        showMap(myUri);
                    }
                });

            }//onResponse
        };//response


        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(this).addToRequestQueue(request);

    }//onCreate

}