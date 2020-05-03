package com.thiago.fipp.appsongs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.thiago.fipp.appsongs.db.bean.Genero;
import com.thiago.fipp.appsongs.db.dal.GeneroDAL;

public class GeneroActivity extends AppCompatActivity {

    private EditText etNome;
    private Button bSalvarGenero;
    private Button bCancelarGenero;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genero);

        etNome = findViewById(R.id.etNome);
        bSalvarGenero = findViewById(R.id.bSalvarGenero);
        bCancelarGenero = findViewById(R.id.bCancelarGenero);

        bSalvarGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String nome = etNome.getText().toString();
                if (nome.isEmpty()) {
                    toast = Toast.makeText(GeneroActivity.this, "Informe o nome do gênero.", Toast.LENGTH_SHORT);
                } else {
                    Genero genero = new Genero(nome);
                    GeneroDAL generoDAL = new GeneroDAL(GeneroActivity.this);

                    if(generoDAL.salvar(genero)) {
                        toast = Toast.makeText(GeneroActivity.this, "Gênero adicionado com sucesso.", Toast.LENGTH_SHORT);
                        etNome.setText("");
                    } else {
                        toast = Toast.makeText(GeneroActivity.this, "Erro ao adicionar o gênero.", Toast.LENGTH_SHORT);
                    }
                }
                toast.show();
            }
        });

        bCancelarGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNome.setText("");
            }
        });
    }
}
