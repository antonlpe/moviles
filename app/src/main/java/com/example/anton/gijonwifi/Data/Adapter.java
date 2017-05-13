package com.example.anton.gijonwifi.Data;

/**
 * Created by Antón on 13/05/2017.
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

import com.example.anton.gijonwifi.Activities.MainActivity;

import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {
    private List<RVItems> items;
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

    public Adapter(Context context, List<RVItems> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // aqui seleccionamos el layout que vamos a cargar con cada item, row_item_layout
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_layout, viewGroup, false);
        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterViewHolder viewHolder, int i) {
        final RVItems rvItems = items.get(i);
        // aqui vas rellenando los campos del layout con los datos del array
        viewHolder.imagen.setImageResource(rvItems.getImagen());
        viewHolder.nombre.setText(rvItems.getNombre());
        // esto es para que al pulsar el cardview (puede ser un linearlayout o lo que quieras) respondas a la pulsacion con lo que quieras, abrir otra actividad o lo que sea
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, rvItems.getNombre(), Snackbar.LENGTH_SHORT).show(); // esto es para probar que responde bien, puedes borrarlo
                Intent intent = new Intent(context, GoogleMaps.class);
                // vamos a pasarle el nombre del item a la segunda actividad
                intent.putExtra("latitud", rvItems.getLat());
                intent.putExtra("longitud", rvItems.getLon());
                intent.putExtra("descripcion", rvItems.getNombre());
                context.startActivity(intent);
            }
        });
    }
}