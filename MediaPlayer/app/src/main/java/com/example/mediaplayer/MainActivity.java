package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    TextView textViewTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.ac);
        textViewTexto = findViewById(R.id.textViewTexto);

        mediaPlayer.setLooping(true);
    }

    public void play(View v) {
        if (mediaPlayer.isPlaying())
            textViewTexto.setText("Ya se está reproduciendo");
        else {
            mediaPlayer.start();
            textViewTexto.setText("Empezando a reproducir...");
        }
    }

    public void pause(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            textViewTexto.setText("Pausado");
        }
        else
            textViewTexto.setText("No estaba reproduciéndose");
    }

    public void stop(View v) throws IOException {
        if (mediaPlayer.isPlaying() || mediaPlayer.getCurrentPosition() > 1) {
            textViewTexto.setText("Acabamos de parar");
            mediaPlayer.stop();
            mediaPlayer.prepare();
        }
        else {
            textViewTexto.setText("No estaba sonando la música");
        }
    }

    public void cambiarCancion(View v) throws IOException {
        String path = "android.resource://" + getPackageName() + "/" + R.raw.classic;
        Uri uri = Uri.parse(path);

        mediaPlayer.reset();
        mediaPlayer.setDataSource(this, uri);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void cambiarStreaming(View v) throws IOException {
        String path = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3";

        mediaPlayer.reset();
        mediaPlayer.setDataSource(path);

        Toast.makeText(this, "Ruta: " + path, Toast.LENGTH_SHORT).show();

        // Prepare asíncrono
        mediaPlayer.prepareAsync();

        // Listener cuando la carga esté completa
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                // Desactivamos el listener ya que se llama varias veces a prepare() a lo largo del código
                mediaPlayer.setOnPreparedListener(null);
            }
        });
    }

    public void seekTo(View v) {
        EditText editText = findViewById(R.id.editText);

        // Tiempo especificado en milisegundos
        mediaPlayer.seekTo(Integer.parseInt(editText.getText().toString()));
        // Varios métodos sobre MediaPlayer
        Toast.makeText(this, " " + mediaPlayer.getDuration() + " " + mediaPlayer.getCurrentPosition() + " " + mediaPlayer.isLooping(), Toast.LENGTH_SHORT).show();
    }

}
