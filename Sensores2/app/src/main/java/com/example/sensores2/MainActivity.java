package com.example.sensores2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

// https://developer.android.com/guide/topics/sensors/sensors_overview
public class MainActivity extends AppCompatActivity {

    private  SensorManager mSensorManager;
    private  Sensor mSigMotion;
    private  TriggerEventListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.requestTriggerSensor(mListener, mSigMotion);

        mListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent triggerEvent) {
                mSensorManager.requestTriggerSensor(mListener, mSigMotion);
                float x, y, z;

                x = triggerEvent.values[0];
                y = triggerEvent.values[1];
                z = triggerEvent.values[2];

                Log.i("prueba", x + " " + y + " " + " " + z);
            }
        };
    }

}
/*
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x, y, z;

        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];

        Log.i("prueba", x + " " + y + " " + " " + z);
    }


    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void pararMon(View v) {
        sensorManager.unregisterListener(this);
    }
    */

