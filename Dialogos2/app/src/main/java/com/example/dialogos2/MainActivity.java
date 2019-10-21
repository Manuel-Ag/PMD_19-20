package com.example.dialogos2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements Dialogo1.Respuesta{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Lanzo el diálogo
     * @param v
     */
    public void boton1(View v) {
        Dialogo1 dialogo = new Dialogo1();
        dialogo.show(getSupportFragmentManager(), "Etiqueta");
    }

    public void boton2(View v) {
        Dialogo2 dialogo = new Dialogo2();
        dialogo.show(getSupportFragmentManager(), "Etiqueta");
    }

    public void boton3(View v) {
        Dialogo3 dialogo = new Dialogo3();
        dialogo.show(getSupportFragmentManager(), "Etiqueta");
    }

    public void boton4(View v) {
        Dialogo4 dialogo = new Dialogo4();
        dialogo.show(getSupportFragmentManager(), "Etiqueta");
    }

    @Override
    public void onRespuesta(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
