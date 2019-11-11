package com.example.intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Código para el intent
    public static final int OPCION1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Intent explícito sin respuesta
     *
     * @param v
     */
    public void botonIntentExplicito(View v) {
        // Creamos el intent
        Intent intent = new Intent(this, Actividad2.class);
        // Lanzamos la segunda actividad
        startActivity(intent);
    }

    /**
     * Intent con información sin respueta
     *
     * @param v
     */
    public void botonIntentExplicitoPutExtra(View v) {
        // Creamos el intent
        Intent intent = new Intent(this, Actividad2.class);
        // Llamamos a putExtra()
        intent.putExtra("tag1", "Contenido intent(1)");
        intent.putExtra("tag2", "Contenido intent(2)");
        // Lanzamos la segunda actividad
        startActivity(intent);
    }

    /**
     * Intent con respuesta
     *
     * @param v
     */
    public void botonIntentExplicitoRespuesta(View v) {
        Intent intent = new Intent(this, Actividad2.class);
        startActivityForResult(intent, OPCION1);
    }

    /**
     * Recibimos el intent respuesta
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case OPCION1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Intent Opción 1: "
                            + data.getStringExtra("tag3"), Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(this, "Usuario ha cancelado", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void botonIntentImplicitoNavegador(View v) {
        // Cogemos la dirección del editText
        EditText editText  = findViewById(R.id.editTextIntentNavegador);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        //Parseamos a tipo Uri (la dirección debe de empezar por http:
        intent.setData(Uri.parse(editText.getText().toString()));
        startActivity(intent);
    }

    public void botonIntentImplicitoGPS(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        // Según lo definido en setData se abrirá una aplicación u otra
        //intent.setData(Uri.parse("geo:41.6460036,-0.8884131"));
        intent.setData(Uri.parse("google.navigation:q=Los+Enlaces,+Zaragoza+España"));
        startActivity(intent);
    }

    public void botonIntentImplicitoEmail(View v) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.setAction(Intent.ACTION_SENDTO);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"direcasd@gads.com", "adsf@gfas.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
        intent.putExtra(Intent.EXTRA_STREAM, "Cuerpo");

            startActivity(intent);


    }
}
