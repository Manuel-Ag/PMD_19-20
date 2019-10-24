package com.example.hundir_la_flota;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anadirBotones();
    }

    private void anadirBotones() {
        // Tamaño fijo
        int filas = 4;
        int columnas = 4;

        TableRow tableRow;
        View view;

        // Tamaño pantalla
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.d("debug", "Tamaño pantalla: " + size.x + " " + size.y);

        // Definiendo algunos parámetros
        /*TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        tableLayoutParams.setMargins(0, 0, 0, 0);*/
        /*param.height = -1;
        param.width = -1;*/
        TableRow.LayoutParams tableRowLayoutParams = new TableRow.LayoutParams((size.x) / columnas, (size.y * 87 / 100) / filas);
        tableRowLayoutParams.setMargins(0, 0, 0, 0);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        //tableLayout.setLayoutParams(tableLayoutParams);

        for (int i = 0; i < filas; i++) {
            tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < columnas; j++) {
                ImageButton button = new ImageButton(this);
                // Añadimos los parámetros
                button.setLayoutParams(tableRowLayoutParams);
                button.setImageResource(R.drawable.ic_launcher_foreground);

                tableRow.addView(button);
            }
        }
    }
}
