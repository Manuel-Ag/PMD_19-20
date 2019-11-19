package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS},
                0);


        /* Registramos el componente en tiempo de ejecución. Según la documentación de Android:

        Android 8.0

        Beginning with Android 8.0 (API level 26), the system imposes additional restrictions on manifest-declared receivers.
        If your app targets Android 8.0 or higher, you cannot use the manifest to declare a receiver for most implicit broadcasts
        (broadcasts that don't target your app specifically). You can still use a context-registered receiver when the user is actively
        using your app.
         */
        Receptor receptor = new Receptor();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");

        this.registerReceiver(receptor, filter);
    }

}
