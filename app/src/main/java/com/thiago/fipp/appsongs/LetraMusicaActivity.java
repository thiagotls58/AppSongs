package com.thiago.fipp.appsongs;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LetraMusicaActivity extends AppCompatActivity {

    private TextView tvTituloMusica, tvInterpreteMusica, tvLetraMusica;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letra_musica);

        tvTituloMusica = findViewById(R.id.tvTituloMusica);
        tvInterpreteMusica = findViewById(R.id.tvInterpreteMusica);
        tvLetraMusica = findViewById(R.id.tvLetraMusica);

        Intent intent = getIntent();
        String tituloMusica = intent.getStringExtra("tituloMusica");
        String interpreteMusica = intent.getStringExtra("interpreteMusica");
        String letraMusica = intent.getStringExtra("letraMusica");

        tvTituloMusica.setText(tituloMusica.toString());
        tvInterpreteMusica.setText(interpreteMusica);
        tvLetraMusica.setText(letraMusica);
        tvLetraMusica.setMovementMethod(new ScrollingMovementMethod()); // Scroll na TextView

    }
}
