package com.example.servicios;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import androidx.core.net.ConnectivityManagerCompat;

public class WifiTest extends Service {
    private boolean enEjecucion = false;
    Tester tester;
    @Override
    public void onCreate() {
        Log.i("prueba", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (!enEjecucion) {
            tester = new Tester();
            tester.start();
            enEjecucion = true;
            Log.i("prueba", "arrancando");
        }else {
            Log.i("prueba", "el sevicio ya estaba arrancado");
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if(enEjecucion)
            tester.interrupt();

        Log.i("prueba", "servicio destruido");
    }

    public class Tester extends Thread {

        public void run() {
            while (enEjecucion) {
                if (comprobarWifi())
                    Log.i("prueba", "wifi_activada");
                else
                    Log.i("prueba", "wifi_desactivada");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    enEjecucion = false;
                    e.printStackTrace();
                    Log.i("prueba", "hilo interrumpido");
                }
            }
        }

        public boolean comprobarWifi() {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo.isConnected())
                return true;

            return false;
        }
    }
}
