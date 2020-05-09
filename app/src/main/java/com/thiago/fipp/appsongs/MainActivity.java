package com.thiago.fipp.appsongs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import com.thiago.fipp.appsongs.db.bean.Genero;
import com.thiago.fipp.appsongs.db.bean.Musica;
import com.thiago.fipp.appsongs.db.dal.MusicaDAL;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvMusicas;
    private List<Musica> musicas;
    private SearchView searchMusica;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        MenuItem item = menu.findItem(R.id.searchMusica);
        searchMusica = (SearchView) item.getActionView();
        searchMusica.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String filtro = "mus_titulo like '%"+newText+"%' or mus_interprete like '%"+newText+"%'";
                musicas = new MusicaDAL(MainActivity.this).get(filtro);
                createListMusicas(musicas);
                return false;
            }
        });

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
        musicas = new MusicaDAL(MainActivity.this).get("");
        createListMusicas(musicas);


    }

    private void createListMusicas(List<Musica> musicas) {
        lvMusicas.setAdapter(new MusicasAdapter(MainActivity.this, R.layout.block_list, musicas));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        musicas = new MusicaDAL(MainActivity.this).get("");
        createListMusicas(musicas);
    }
}
