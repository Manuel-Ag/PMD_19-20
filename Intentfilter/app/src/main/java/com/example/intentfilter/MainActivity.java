package com.example.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        // Acción
        String action = intent.getAction();
        // Tipo mime
        String type = intent.getType();

        Log.d("prueba", "Action: " + action + " Tipo: " + type);

        // Comprobamos la acción y que le tipo no es nulo
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            // En caso de ser texto plano (hay que definirlo en el Manifest.xml)
            if ("text/plain".equals(type)) {

            }
            // En caso de ser imagen
            else if (type.startsWith("image/")) {
                ImageView imageView = findViewById(R.id.imageView);
                // Pasamos la dirección de la imagen
                Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
                imageView.setImageURI(imageUri);
            }
        }

    }

}
