package com.example.hola_mundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button boton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listener forma1
        boton1 = findViewById(R.id.buttonTipo1);
        boton1.setOnClickListener(this);

        //tercera forma de hacer un botón
        final Button boton3 = findViewById(R.id.buttonTipo3);
        boton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Toast.makeText(MainActivity.this, "No necesito implementar la interface", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * Forma 1 de poner un botón
     * @param v
     */
    @Override
    public void onClick(View v) {
        //Hago referencia al elemento que quiero capturar
        EditText campoTexto = findViewById(R.id.editText);
        //Cojo el texto
        String textoUsuario = campoTexto.getText().toString();
        //Y lo muestro en una tostada
        Toast.makeText(this, textoUsuario, Toast.LENGTH_LONG).show();

    }

    /**
     * Forma 2 de poner un botón
     */

    public void forma2(View v) {
        Toast.makeText(this, "Forma 2 de poner un botón", Toast.LENGTH_SHORT).show();
    }
}
