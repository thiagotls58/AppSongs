package com.thiago.fipp.appsongs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBMySongs extends SQLiteOpenHelper {
    private static final int VERSAO = 1;

    public DBMySongs(Context context) {
        super(context, "mysongs.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE genero( gen_id INTEGER PRIMARY KEY AUTOINCREMENT, gen_nome VARCHAR (15));");

        db.execSQL("CREATE TABLE musica " +
                "(mus_id  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mus_ano INTEGER, mus_titulo VARCHAR (40), " +
                "mus_interprete VARCHAR (30), " +
                "mus_genero INTEGER REFERENCES genero (gen_id), " +
                "mus_duracao NUMERIC (4, 1) );");

        db.execSQL("INSERT INTO genero(gen_id, gen_nome) VALUES (1, 'Rock');");
        db.execSQL("INSERT INTO genero(gen_id, gen_nome) VALUES (2, 'Indie');");
        db.execSQL("INSERT INTO genero(gen_id, gen_nome) VALUES (3, 'Pop');");
        db.execSQL("INSERT INTO genero(gen_id, gen_nome) VALUES (4, 'Eletrônica');");

        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('The Getaway', 2016, 4.10, 'Red Hot Chili Peppers', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Dark Necessities', 2016, 5.02, 'Red Hot Chili Peppers', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Isolation', 2010, 4.14, 'Alter Bridge', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Come To Life', 2007, 3.52, 'Alter Bridge', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Man in the Box', 1990, 4.44, 'Alice In Chains', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Sunshine', 1990, 4.45, 'Alice In Chains', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('One', 1991, 4.36, 'U2', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Beautiful Day', 2000, 4.08, 'U2', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Papercut', 2000, 3.05, 'Linkin Park', 1);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Dog Days Are Over', 2009, 4.13, 'Florence + The Machine', 2);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Shake It Out', 2011, 4.38, 'Florence + The Machine', 2);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Shake It Out', 2011, 4.38, 'Florence + The Machine', 2);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('New Slang', 2001, 3.51, 'The Shins', 2);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Simple Song', 2012, 4.15, 'The Shins', 2);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Simple Song', 2012, 4.15, 'The Shins', 2);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Bad Romance', 2009, 4.55, 'Lady Gaga', 3);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Just Dance', 2009, 4.02, 'Lady Gaga', 3);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Chantaje', 2017, 3.16, 'Shakira', 3);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Loca', 2010, 3.04, 'Shakira', 3);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Maldad', 2020, 2.48, 'Steve Aoki', 4);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('New Blood', 2020, 3.12, 'Steve Aoki', 4);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('My World', 2020, 3.58, 'NERVO', 4);");
        db.execSQL("INSERT INTO musica(mus_titulo, mus_ano, mus_duracao, mus_interprete, mus_genero) VALUES ('Revolution', 2014, 3.23, 'NERVO', 4);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS musica");
        db.execSQL("DROP TABLE IF EXISTS genero");
        onCreate(db);

    }
}

