package com.thiago.fipp.appsongs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.thiago.fipp.appsongs.db.bean.Musica;
import com.thiago.fipp.appsongs.db.dal.MusicaDAL;

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

        final Musica musica = this.getItem(position);

        tvTitulo.setText(musica.getTitulo());
        tvInterprete.setText(musica.getInterprete());
        tvGenero.setText(musica.getGenero().getNome());

        ImageButton bEditMusica = convertView.findViewById(R.id.bEditMusica);
        bEditMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MusicaActivity.class);
                intent.putExtra("idMusica", musica.getId());
                getContext().startActivity(intent);
            }
        });


        ImageButton bRemoveMusica = convertView.findViewById(R.id.bRemoveMusica);
        bRemoveMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicaDAL musicaDAL = new MusicaDAL(getContext());
                Boolean apagou = musicaDAL.apagar(musica.getId());
                Toast toast;
                if (apagou) {
                    remove(musica);
                    toast = Toast.makeText(getContext(), "Música apagada com sucesso..", Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(getContext(), "Erro ao apagar a música.", Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });

        ImageButton bExibirLetra = convertView.findViewById(R.id.bExibirLetra);
        bExibirLetra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letra = "";
                try {
                    String url="https://api.vagalume.com.br/search.php"+
                                "?art=" + musica.getInterprete() +
                                "&mus=" + musica.getTitulo();
                    AcessaWSTask task=new AcessaWSTask();
                    String json = task.execute(url).get();

                    Gson gson = new Gson();
                    ApiVagalume apiVagalume = gson.fromJson(json, ApiVagalume.class);
                    letra = apiVagalume.getMus().get(0).getText();
                } catch (Exception e)
                {
                    letra = e.getMessage();
                }
                Intent intent = new Intent(getContext(), LetraMusicaActivity.class);
                intent.putExtra("tituloMusica", musica.getTitulo());
                intent.putExtra("interpreteMusica", musica.getInterprete());
                intent.putExtra("letraMusica", letra);

                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
