package com.example.maguera.recibirsms;

import android.Manifest;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ReceptorSMS.onRecibeSMS {

    ReceptorSMS receptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pedirPermiso();

        receptor = new ReceptorSMS();
        registerReceiver(receptor, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        receptor.setOnRecibeSMSListener(this);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receptor);
        receptor = null;
        super.onDestroy();
    }

        @Override
    public void onRecibeSMS(String origen, String mensaje) {
        //Toast.makeText(this, "origen: " + origen + " texto: "+mensaje, Toast.LENGTH_SHORT).show();
        Log.i("Prueba", origen + " " + mensaje);
    }

    public void pedirPermiso() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

                new AlertDialog.Builder(this)
                        .setTitle("Autorización")
                        .setMessage("Necesito permiso para acceder a los contactos de tu dispositivo.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 1);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Mensaje acción cancelada
                                Toast.makeText(MainActivity.this, "Cancelada", Toast.LENGTH_SHORT).show();;
                            }
                        })
                        .show();

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }
}
