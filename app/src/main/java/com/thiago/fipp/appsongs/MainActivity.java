package com.thiago.fipp.appsongs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.thiago.fipp.appsongs.db.bean.Genero;
import com.thiago.fipp.appsongs.db.bean.Musica;
import com.thiago.fipp.appsongs.db.dal.MusicaDAL;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvMusicas;
    private List<Musica> musicas;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.miMusica:
                intent = new Intent(MainActivity.this, MusicaActivity.class);
                startActivity(intent);
                break;
            case R.id.miGenero:
                intent = new Intent(MainActivity.this, GeneroActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMusicas = findViewById(R.id.lvMusicas);
        createListMusicas();
    }

    private void createListMusicas() {
        musicas = new MusicaDAL(this).get("");
        lvMusicas.setAdapter(new MusicasAdapter(this, R.layout.block_list, musicas));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        createListMusicas();
    }
}
