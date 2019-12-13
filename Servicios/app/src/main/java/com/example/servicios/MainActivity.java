package com.example.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                1);
    }

    public void arrancar(View v) {
        Intent intent = new Intent(this, WifiTest.class);
        startService(intent);
    }

    public void parar(View v) {
        Intent intent = new Intent(this, WifiTest.class);
        stopService(intent);
    }
}
