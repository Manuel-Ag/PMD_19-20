package com.example.preferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void claseSharedPreferences(View v) {
        SharedPreferences misPreferencias = getSharedPreferences("prefs", MODE_PRIVATE);

        //guardamos las preferencias
        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putString("nombre", "Paco");
        editor.putInt("edad", 40);
        editor.putBoolean("soltero", true);
        editor.apply();

        //recuperamos las preferencias
        String nombre = misPreferencias.getString("nombre", "Sin dato");
        int edad = misPreferencias.getInt("edad", 0);
        boolean soltero = misPreferencias.getBoolean("soltero", true);

        Toast.makeText(this, "Nombre: " + nombre + " Edad: " + edad + " Soltero: " + soltero, Toast.LENGTH_SHORT).show();
    }

    public void clasePreferenceActivity(View v) {
        startActivity(new Intent(MainActivity.this, PreferenciasActivity.class));
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String campo1 = sharedPreferences.getString("edit_text_preference_1","Sin dato");
        boolean campo2 = sharedPreferences.getBoolean("check_box_preference_1", false);
        String campo3 = sharedPreferences.getString("list_preference_1", "Sin selección");
        Set<String> entries = sharedPreferences.getStringSet("multi_select_list_preference_1", new HashSet<String>());

        Toast.makeText(this, campo1 + " "+ campo2 + " " + campo3 + " " + entries.toString(), Toast.LENGTH_SHORT).show();

        super.onResume();
    }
}
