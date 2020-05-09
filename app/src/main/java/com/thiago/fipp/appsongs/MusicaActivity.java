package com.thiago.fipp.appsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.thiago.fipp.appsongs.db.bean.Genero;
import com.thiago.fipp.appsongs.db.bean.Musica;
import com.thiago.fipp.appsongs.db.dal.GeneroDAL;
import com.thiago.fipp.appsongs.db.dal.MusicaDAL;

import java.util.List;

public class MusicaActivity extends AppCompatActivity {
    private EditText etTitulo, etAno, etDuracao, etInterprete;
    private Spinner sGeneros;
    private Button bSalvarMusica, bCancelarMusica;
    private Boolean adicionar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);

        etTitulo = findViewById(R.id.etTitulo);
        etAno = findViewById(R.id.etAno);
        etDuracao = findViewById(R.id.etDuracao);
        etInterprete = findViewById(R.id.etInterprete);
        sGeneros = findViewById(R.id.sGeneros);
        bSalvarMusica = findViewById(R.id.bSalvarMusica);
        bCancelarMusica = findViewById(R.id.bCancelarMusica);


        GeneroDAL generoDAL = new GeneroDAL(MusicaActivity.this);
        List<Genero> generos = generoDAL.get("");

        carregarGeneros(generos);

        Intent intent = getIntent();
        final int idMusica = intent.getIntExtra("idMusica", 0);
        if (idMusica > 0) {
            Musica musica = new MusicaDAL(MusicaActivity.this).get(idMusica);
            if (musica != null) {

                adicionar = false;

                etTitulo.setText(musica.getTitulo());
                etAno.setText(""+musica.getAno());
                String duracao = String.format("%2.2f", musica.getDuracao());
                duracao = duracao.replace(",", ":");
                if (musica.getDuracao() < 10) {
                    duracao = "0"+duracao;
                }
                etDuracao.setText(duracao);
                etInterprete.setText(musica.getInterprete());
                int index = 0;
                while (index < generos.size() && generos.get(index).getId() != musica.getGenero().getId())
                    index++;
                sGeneros.setSelection(index);
            }
        } else {
            adicionar = true;
        }

        bSalvarMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Musica musica = null;
                Toast toast;
                String msg = validarDados();
                if (msg.isEmpty()) {
                    String titulo = etTitulo.getText().toString();
                    int ano = Integer.parseInt(etAno.getText().toString());
                    double duracao = Double.parseDouble(etDuracao.getText().toString().replace(":", "."));
                    String interprete = etInterprete.getText().toString();
                    Genero genero =  (Genero) sGeneros.getSelectedItem();
                    musica = new Musica(ano, titulo, interprete, genero, duracao);
                    MusicaDAL musicaDAL = new MusicaDAL(MusicaActivity.this);

                    if (adicionar) {
                        if (musicaDAL.salvar(musica)) {
                            toast = Toast.makeText(MusicaActivity.this, "Música adicionada com sucesso.", Toast.LENGTH_SHORT);
                            limparTela();
                        } else {
                            toast = Toast.makeText(MusicaActivity.this, "Erro ao adicionar a música.", Toast.LENGTH_SHORT);
                        }
                    } else {
                        musica.setId(idMusica);
                        if (musicaDAL.alterar(musica)) {
                            toast = Toast.makeText(MusicaActivity.this, "Música alterada com sucesso.", Toast.LENGTH_SHORT);
                            limparTela();
                        } else {
                            toast = Toast.makeText(MusicaActivity.this, "Erro ao alterar a música.", Toast.LENGTH_SHORT);
                        }
                    }
                } else {
                    toast = Toast.makeText(MusicaActivity.this, msg, Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });

        bCancelarMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparTela();
            }
        });

    }

    private String validarDados() {
        String titulo = etTitulo.getText().toString();
        if (titulo.isEmpty()) {
            return "Informe o título da música";
        }

        String ano = etAno.getText().toString();
        if (ano.isEmpty()) {
            return "Informe o ano da música";
        }

        String duracao = etDuracao.getText().toString();
        if (duracao.isEmpty()) {
            return "Informe a duração da música";
        } else if (duracao.startsWith(":") || duracao.endsWith(":")) {
            return "Informe a duração da música corretamente";
        }

        String interprete = etInterprete.getText().toString();
        if (interprete.isEmpty()) {
            return "Informe o interprete da música";
        }

        Genero genero =  (Genero) sGeneros.getSelectedItem();
        if (genero == null) {
            return "Informe o gênero da música";
        }

        return "";
    }

    private void carregarGeneros(List<Genero> generos) {
        sGeneros.setAdapter(new ArrayAdapter<Genero>(MusicaActivity.this, android.R.layout.simple_spinner_item, generos));
    }

    private void limparTela() {
        etTitulo.setText("");
        etAno.setText("");
        etDuracao.setText("");
        etInterprete.setText("");
        sGeneros.setSelection(0);
    }
}
