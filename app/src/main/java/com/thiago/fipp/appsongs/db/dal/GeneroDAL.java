package com.thiago.fipp.appsongs.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;


import com.thiago.fipp.appsongs.db.bean.Genero;
import com.thiago.fipp.appsongs.db.util.Conexao;

import java.util.ArrayList;


public class GeneroDAL {
    private Conexao con;
    private final String TABLE = "genero";

    public GeneroDAL(Context context) {
        con = new Conexao(context);
        try {
            con.conectar();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean salvar(Genero o) {
        ContentValues dados = new ContentValues();
        dados.put("gen_nome", o.getNome());

        return con.inserir(TABLE, dados) > 0;
    }

    public boolean alterar(Genero o) {
        ContentValues dados = new ContentValues();
        dados.put("gen_id", o.getId());
        dados.put("gen_nome", o.getNome());
        return con.alterar(TABLE, dados, "gen_id=" + o.getId()) > 0;
    }

    public boolean apagar(long chave) {
        return con.apagar(TABLE, "gen_id=" + chave) > 0;
    }

    public Genero get(int id) {
        Genero o = null;
        Cursor cursor = con.consultar("select * from " + TABLE + " where gen_id=" + id);
        if (cursor.moveToFirst())
            o = new Genero(cursor.getInt(0), cursor.getString(1));
        cursor.close();
        ;
        return o;
    }

    public ArrayList<Genero> get(String filtro) {
        ArrayList<Genero> objs = new ArrayList();
        String sql = "select * from " + TABLE;
        if (!filtro.equals(""))
            sql += " where " + filtro;

        Cursor cursor = con.consultar(sql);
        if (cursor.moveToFirst())
            while (!cursor.isAfterLast()) {
                objs.add(new Genero(cursor.getInt(0), cursor.getString(1)));
                cursor.moveToNext();
            }
        cursor.close();
        ;
        return objs;
    }
}
