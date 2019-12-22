package com.example.alarmas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editTextHora;
    EditText editTextMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHora = findViewById(R.id.editTextHora);
        editTextMin= findViewById(R.id.editTextMin);
    }

    public void boton(View v) {
        AlarmManager alarmManager;
        PendingIntent pendingIntent;

        // Hora y minutos. Los segundos los dejamos a 0.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(editTextHora.getText().toString()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(editTextMin.getText().toString()));
        calendar.set(Calendar.SECOND, 0);

        // Podemos definir quién recibe el intent, tenemos varias posibilidades
        Intent intent = new Intent(this, Alarma.class);
        //Intent intent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Se crea la instancia de AlarmManager
        alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);

        // Alarma inexacta
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
         //       AlarmManager.INTERVAL_DAY, pendingIntent);

        // Alarma exacta (dependiendo de la versión de Android el comportamiento puede variar)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Toast.makeText(this, "Alarma programada", Toast.LENGTH_SHORT).show();
    }
}
