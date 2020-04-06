package com.example.callback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receptor extends BroadcastReceiver {
    Respuesta respuesta = null;
    String prueba;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SMS entrante", Toast.LENGTH_LONG).show();

        respuesta = (Respuesta) context;
        respuesta.onRespuesta("Callback!!");
    }

    public interface Respuesta {
        //Los métodos en las interfaces solo están declarados
        public void onRespuesta(String s);
    }
}
