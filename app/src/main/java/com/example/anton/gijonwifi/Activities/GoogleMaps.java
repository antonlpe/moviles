package com.example.anton.gijonwifi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.anton.gijonwifi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMaps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*
    MÉTODO SOBREESCRITO PARA QUE MUESTRE LA UBICACIÓN ELEGIDA
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //SE CREAN LAS VARIABLES Y SE ALMACENAN LOS DATOS NECESARIOS A TRAVÉS DE UN BUNDLE
        float lat = 0, lon = 0;
        String nombre = "";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            lat = bundle.getFloat("latitud");
            lon = bundle.getFloat("longitud");
            nombre =(String) bundle.get("nombre");
        }

        //SE ASIGNA LA UBICACIÓN AL MAPA Y SE CONFIGURA LA CÁMARA Y LA ETIQUETA
        LatLng ubicacion = new LatLng(lat, lon);
        Marker prueba = mMap.addMarker(new MarkerOptions().position(ubicacion).title(nombre));
        prueba.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));



    }

}
