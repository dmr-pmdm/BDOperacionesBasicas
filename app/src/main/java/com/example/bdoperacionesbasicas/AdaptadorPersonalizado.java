package com.example.bdoperacionesbasicas;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorPersonalizado extends ArrayAdapter {
    private Activity context;
    private String[] usuarios;

    public AdaptadorPersonalizado(@NonNull Activity context, String[] usuarios) {
        super(context, R.layout.layout_lista, usuarios);
        this.context = context;
        this.usuarios = usuarios;
    }

    static class ViewHolder {
        LinearLayout llLista;
        TextView txtLista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View fila = convertView;
        ViewHolder holder;

        if (fila == null) {
            LayoutInflater inflador = context.getLayoutInflater();
            fila = inflador.inflate(R.layout.layout_lista, null);

            holder = new ViewHolder();
            holder.llLista = fila.findViewById(R.id.llLista);
            holder.txtLista = fila.findViewById(R.id.txtLista);

            fila.setTag(holder);
        } else {
            holder = (ViewHolder) fila.getTag();
        }

        holder.txtLista.setText(usuarios[position]);

        return fila;
    }
}
