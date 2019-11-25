package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void conectarCrearBaseDatos(View v) {
        // Crear o conectarse a una base de datos existente
        db = openOrCreateDatabase("MiDB", Context.MODE_PRIVATE, null);
    }

    public void crearTabla(View v) {
        // Creamos la base de datos si no existe
        db.execSQL("CREATE TABLE IF NOT EXISTS miTabla(atributo1 VARCHAR, atributo2 VARCHAR)");
    }

    public void insertarDatos(View v) {
        // Lanzamos la consulta de insertado
        db.execSQL("INSERT INTO miTabla VALUES ('Campo1', 'Campo2')");
    }

    public void listar(View v) {
        // rawQuery lanza la consulta SQL y devuelve un cursor
        Cursor c = db.rawQuery("SELECT * FROM miTabla", null);
        if (c.getCount() == 0) {
            Log.i("Resultado", "No hay registros");
        }
        else {
            while(c.moveToNext())
                Log.i("Resultado", c.getPosition() + ". " + c.getString(0) + ", " +  c.getString(1));
        }

    }
}
