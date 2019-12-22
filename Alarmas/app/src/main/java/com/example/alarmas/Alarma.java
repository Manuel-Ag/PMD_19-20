package com.example.alarmas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarma extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarma", Toast.LENGTH_SHORT).show();
        Log.i("prueba", "alarma lanzada");
    }
}
