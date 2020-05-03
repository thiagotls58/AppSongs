package com.thiago.fipp.appsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thiago.fipp.appsongs.db.bean.Musica;

import java.util.List;

public class MusicasAdapter extends ArrayAdapter<Musica> {

    private int layout;

    public MusicasAdapter(Context context, int resource, List<Musica> musicas) {
        super(context, resource, musicas);
        layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvInterprete = convertView.findViewById(R.id.tvInterprete);
        TextView tvGenero = convertView.findViewById(R.id.tvGenero);

        Musica musica = this.getItem(position);

        tvTitulo.setText(musica.getTitulo());
        tvInterprete.setText(musica.getInterprete());
        tvGenero.setText(musica.getGenero().getNome());

        return convertView;
    }
}
