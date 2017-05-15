package com.example.anton.gijonwifi.Data;

/**
 * Created by Antón on 13/05/2017.
 *
 * Adapter para utilizar un RecyclerView
 */

import android.content.Context;
import android.content.Intent;

import com.example.anton.gijonwifi.Activities.GoogleMaps;
import com.example.anton.gijonwifi.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private List<Items> items;
    private Context context;

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        ImageView imagen;
        TextView nombre;
        CardView cardView;


        public AdapterViewHolder(View v) {
            super(v);
            // buscamos los items en el layout (row_item_layout.xml)
            imagen = (ImageView) v.findViewById(R.id.image);
            nombre = (TextView) v.findViewById(R.id.text);
            cardView = (CardView) v.findViewById(R.id.cardview);
        }
    }

    public Adapter(Context context, List<Items> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //infla el layout y retorna un nuevo ViewHolder inicializado
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_layout, viewGroup, false);
        return new AdapterViewHolder(v);
    }

    //Aquí es donde realmente reemplazamos el contenido del view
    @Override
    public void onBindViewHolder(final AdapterViewHolder viewHolder, int i) {
        final Items Items = items.get(i);
        // aqui vas rellenando los campos del layout con los datos del array
        viewHolder.imagen.setImageResource(Items.getImagen());
        viewHolder.nombre.setText(Items.getNombre());
        // esto es para que al pulsar el cardview (puede ser un linearlayout o lo que se quiera)
        // responda a la pulsacion con lo que quieras, abrir otra actividad en este caso
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoogleMaps.class);
                //aqui se pasan los datos del item a la segunda actividad
                intent.putExtra("latitud", Items.getLat());
                intent.putExtra("longitud", Items.getLon());
                intent.putExtra("nombre", Items.getNombre());
                context.startActivity(intent);
            }
        });
    }
}