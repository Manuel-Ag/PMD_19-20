package com.example.maguera.recibirsms;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS},
                1);

    }

    public void enviarSMS(View v) {
        EditText editText = findViewById(R.id.editText);

        // SMS mediante el API
        /*SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(editText.toString(), null, "sms message", null, null);*/

        // SMS mediante intent
        Uri uri = Uri.parse("smsto:"+editText.toString());
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "The SMS text");
        startActivity(intent);
    }
}
