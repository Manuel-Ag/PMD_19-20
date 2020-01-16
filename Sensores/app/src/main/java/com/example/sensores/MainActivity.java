package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listadoSensores(View v) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL); // Se puede seleccinar el tipo

        for (int i = 0; i < sensors.size(); i++) {
            Log.i("prueba", sensors.get(i).getName());
        }
    }

    public void acelerometro(View v) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Toast.makeText(this, acelerometro.getName(), Toast.LENGTH_SHORT).show();
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                ArrayList<Float> valores = new ArrayList<Float>();

                Log.i("prueba", "Cantidad de valores: " + sensorEvent.values.length);
                for (int i = 0; i < sensorEvent.values.length; i++){
                    valores.add(sensorEvent.values[i]);
                }

                Log.i("prueba", valores.toString());

                break;
        }
    }

    /**
     * Es invocado cuando un sensor cambia su precisión
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void pararMon(View v) {
        // también se puede llamar a unregistrerListener(Context, Sensor) para dejar de escuhar a uno en concreto
        sensorManager.unregisterListener(this);
    }

}
