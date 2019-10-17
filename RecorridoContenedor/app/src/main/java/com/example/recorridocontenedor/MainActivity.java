package com.example.recorridocontenedor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mostrarTableRows();
        //mostrarVistas();
        anadirBotones();
    }

    /**
     * Mostramos los TableRow de TableLayout
     */
    private void mostrarTableRows() {
        TableRow tableRow;
        View view;
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            //Hacemos cast porque sabemos su tipo
            tableRow = (TableRow) tableLayout.getChildAt(i);
            //también podría funcionar
            //view = tableLayout.getChildAt(i);
            //Compruebo si realmente tengo acceso
            Log.i("Prueba", tableRow.toString());
        }
    }

    /**
     * Mostramos las vistas dentro de los TableRow
     */
    private void mostrarVistas() {
        TableRow tableRow;
        View view;
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            //Hacemos cast porque sabemos su tipo
            tableRow = (TableRow) tableLayout.getChildAt(i);
            //Recorremos los tableRow
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                //Como no sabemos el tipo de vista, lo guardo en un tipo View
                view = tableRow.getChildAt(j);
                Log.wtf("Prueba", view.toString());
            }
        }
    }

    /**
     * Añado botones en tiempo de ejecución
     */
    private void anadirBotones() {
        TableRow tableRow;
        View view;
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            //Hacemos cast porque sabemos su tipo
            tableRow = (TableRow) tableLayout.getChildAt(i);
            //Creamos un botón
            Button button = new Button(this);
            button.setText("Texto");
            //Generamos Id
            button.setId(View.generateViewId());
            //Añadimos el listener a cada botón
            button.setOnClickListener(this);
            //Asignamos una etiqueta
            button.setTag("boton" + i);
            //Añadimos botón
            tableRow.addView(button);
        }

        //Es posible añadir una quinta fila
        //Creo un tableRow
        TableRow tr_head = new TableRow(this);
        //Y lo añado al tableLayout
        tableLayout.addView(tr_head);

        //Añadimos un botón a la fila creada
        Button button = new Button(this);
        button.setText("Texto");
        button.setId(View.generateViewId());
        tr_head.addView(button);
    }

    @Override
    public void onClick(View v) {
        //Puedo identificar la vista que se ha pulsado
        Toast.makeText(this, "Funciono, soy un "
                + v.getClass().getSimpleName() +", y tengo el tag: " + v.getTag(), Toast.LENGTH_SHORT).show();

        //Funcionalidad de botón 0
        if (v.getTag().equals("boton0")) {

        }
    }
}
