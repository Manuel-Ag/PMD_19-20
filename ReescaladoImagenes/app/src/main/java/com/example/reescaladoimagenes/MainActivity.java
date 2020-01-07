package com.example.reescaladoimagenes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    final static int GALERIA = 1;
    final static int REDUCIR = 2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

    }

    // Tamaño real
    public void seleccionarFoto(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERIA);
    }

    // Reducir
    public void reducirFoto(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REDUCIR);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GALERIA) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(Uri.parse(data.getDataString()));
            }
        }
        if (requestCode == REDUCIR) {
            if (resultCode == RESULT_OK) {
                //imageView.setImageURI(Uri.parse(data.getDataString()));
                try {
                    imageView.setImageBitmap(reducir(data.getDataString(), 2, 2));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Bitmap reducir(String uri, int ancho, int alto) throws FileNotFoundException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // Evita cargar toda la fotografía en memoria
        options.inJustDecodeBounds = true;
        // Solo cargamos el tamaño y lo guardamos en options
        BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(uri)), null, options);
        // Definimos un nuevo tamaño. BitmapFactory solo reduce las imágenes en potencia de 2, por lo que puede variar el resultado
        options.inSampleSize = (int) Math.max(Math.ceil(options.outWidth/ancho), Math.ceil(options.outHeight/alto));
        // Activamos la carga de imagen en memoria
        options.inJustDecodeBounds = false;
        // Cargamos la imagen en memoria.
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(uri)), null, options);
    }
}
