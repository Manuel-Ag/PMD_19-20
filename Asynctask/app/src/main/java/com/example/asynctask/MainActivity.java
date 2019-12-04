package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.asyntask.R;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    Tarea tarea;
    private ProgressBar progressBar;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
    }

    public void sumar(View v) {
        tarea = new Tarea();
        tarea.execute(Integer.parseInt(editText.getText().toString()));

        // la ejecución sigue
        Toast.makeText(this, "Inicio del calculo", Toast.LENGTH_SHORT).show();

        // El método get() esperaría a que terminase el hilo para continuar la ejecución del hilo principal (se bloquea la interface de usuario)
         //tarea.get(1, TimeUnit.MILLISECONDS);

    }

    public void cancelar(View v) {
        tarea.cancel(true);
    }

    public synchronized void cambiarTexto(String s) {
        editText.setText(s);
    }

    // <Parámetros, progreso y resultado> ver: https://stackoverflow.com/questions/6053602/what-arguments-are-passed-into-asynctaskarg1-arg2-arg3
    public class Tarea extends AsyncTask<Integer, Integer, Integer> {

        /**
         * Previo a la ejecución de la tarea
         */
        @Override
        protected void onPreExecute() {
            button.setText("Calculando...");
        }

        /**
         * Código en hilo nuevo
         */
        @Override
        protected Integer doInBackground(Integer... objects) {
            for (int i = 0; i <= objects[0]; i++) {
                cambiarTexto(i+"");
                publishProgress(i*100/objects[0]);
                SystemClock.sleep(300);

                if (isCancelled()) break;
            }

            return objects[0];
        }

        /**
         * Muestra el progreso de la tarea
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        /**
         * Cuando acaba. Mostrará el resultado final
         */
        @Override
        protected void onPostExecute(Integer integer) {
            button.setText("Se ha sumado" + integer + "veces");
        }
    }
}
