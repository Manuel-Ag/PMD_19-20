package com.example.ficheros;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boton(View v) throws IOException { Resources r = getResources();
        InputStream inputStream = this.getResources().openRawResource(R.raw.palabras);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputreader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Log.i("debug", line);
        }

    }
}
