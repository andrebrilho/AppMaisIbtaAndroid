package com.example.brilho.maisibta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.brilho.maisibta.modelo.Materia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brilho on 07/05/2017.
 */

public class MateriaDao extends SQLiteOpenHelper {
    public MateriaDao(Context context) {
        super(context, "Materias", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //"CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        String sql = "CREATE TABLE Materias (id INTEGER PRIMARY KEY, materia TEXT NOT NULL, professor TEXT, nota1 TEXT, nota2 TEXT, notaEd TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Materias";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Materia materia) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesMateria(materia);


        db.insert("Materias", null,dados );
    }

    @NonNull
    private ContentValues getContentValuesMateria(Materia materia) {
        ContentValues dados = new ContentValues();
        dados.put("materia", materia.getMateria());
        dados.put("professor", materia.getProfessor());
        dados.put("nota1", materia.getNotaProva1());
        dados.put("nota2", materia.getNotaProva2());
        dados.put("notaEd", materia.getNotaEd());
        return dados;
    }

    public List<Materia> buscaMateria() {

        String sql = "SELECT * FROM Materias";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Materia> materias = new ArrayList<Materia>();

        while (c.moveToNext()){
            Materia materia = new Materia();
            materia.setId(c.getLong(c.getColumnIndex("id")));
            materia.setMateria(c.getString(c.getColumnIndex("materia")));
            materia.setProfessor(c.getString(c.getColumnIndex("professor")));
            materia.setNotaProva1(c.getString(c.getColumnIndex("nota1")));
            materia.setNotaProva2(c.getString(c.getColumnIndex("nota2")));
            materia.setNotaEd(c.getString(c.getColumnIndex("notaEd")));

            materias.add(materia);

        }
        c.close();
        return materias;
    }

    public void deleta(Materia materia) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {materia.getId().toString()};
        db.delete("Materias", "id = ?", params);

    }

    public void altera(Materia materia) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValuesMateria(materia);

        String[] params = {materia.getId().toString()};

        db.update("Materias", dados, "id = ?", params);

    }
}
