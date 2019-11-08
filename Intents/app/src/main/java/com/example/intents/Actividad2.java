package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        // Recupero el intent que ha lanzado esta actividad
        Intent intent = getIntent();
        // Y su contenido
        String tag1 = intent.getStringExtra("tag1");
        String tag2 = intent.getStringExtra("tag2");

        TextView textView = findViewById(R.id.textViewIntent);
        textView.setText(tag1 + " " + tag2);
    }

    public void botonCancelar(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void botonAceptar(View v) {
        Intent intent = new Intent();
        intent.putExtra("tag3", "Hola");
        // Enviamos el intent al MainActivity.class y cerramos la actividad actual
        setResult(RESULT_OK, intent);
        finish();
    }
}
