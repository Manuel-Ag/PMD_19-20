package com.example.dialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Dialogo.Respuesta{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Lanzo el diálogo
     * @param v
     */
    public void boton(View v) {
        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "Etiqueta");
    }

    @Override
    public void onRespuesta(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
