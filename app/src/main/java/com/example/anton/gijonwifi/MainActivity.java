package com.example.anton.gijonwifi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import com.android.volley.Response;
import com.example.anton.gijonwifi.JSON.Datos;
import com.example.anton.gijonwifi.JSON.GsonRequest;
import com.example.anton.gijonwifi.JSON.VolleyManager;


public class MainActivity extends AppCompatActivity  {
    private static final String URL = "http://datos.gijon.es/doc/ciencia-tecnologia/zona-wifi.json";


    Button btmapa = null;
    TextView tx = null;

    //Intent que llama a maps pasándole una localización
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


        //creo objeto respuesta
        Response.Listener<Datos> response = new Response.Listener<Datos>() {
            @Override
            public void onResponse(Datos response) {


                tx.setText(response.getDirectorio().get(21).getNombre().getNombreMarcador());
                //la sintaxix siguiente muestra marcador y etiqueta
                //geo:41.3825581,2.1704375?z=16&q=41.3825581,2.1704375(Barcelona)

                String s = "geo:"+response.getDirectorio().get(21).getLocalizacion().getCoordenadas()+
                        "?z=+16&q="+response.getDirectorio().get(21).getLocalizacion().getCoordenadas()+
                        "("+response.getDirectorio().get(21).getNombre().getNombreMarcador()+")";
                System.out.println(response.getDirectorio().get(21).getNombre().getNombreMarcador());
                final Uri myUri = Uri.parse(s);
                btmapa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMap(myUri);
                    }
                });
            }//onResponse
        };//response

        GsonRequest<Datos> request = new GsonRequest<>(URL, Datos.class, null, response, null, "directorios");
        VolleyManager.getInstance(this).addToRequestQueue(request);



    }//onCreate

}