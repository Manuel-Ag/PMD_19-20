package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static final String CHANNEL_ID = "ID_CANAL";
    // Key for the string that's delivered in the action's intent.
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    // Para la notificación con progreso
    NotificationCompat.Builder builderProgreso;
    NotificationManagerCompat notificationManagerProgreso;
    int PROGRESS_MAX = 100;
    int PROGRESS_CURRENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se crea el canal de notificación, oblitagorio a partir del API 26
        createNotificationChannel();
    }

    public void notificacionSimple(View v) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)  // el icono debe de ser en gama de blancos y negros
                .setContentTitle("Título")
                .setContentText("Contenido")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Mostramos la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());


    }

    public void notificacionAccionToque(View v) {
        // Intent explícito hacia una actividad
        Intent intent = new Intent(this, Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Mostramos la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(2, builder.build());
    }


    public void notificacionBoton(View v) {
        Intent snoozeIntent = new Intent(this, Main2Activity.class);
        snoozeIntent.setAction("Botón");
        snoozeIntent.putExtra("extra", 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getActivity(this, 0, snoozeIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_android_black_24dp, "Botón",
                        snoozePendingIntent)
                // podemos añadir hasta 3 botones, en este caso botón 2 utiliza el mismo pending intent,
                // por lo que tendrá la misma funcionalidad de botón 1
                .addAction(R.drawable.ic_android_black_24dp, "Botón2",
                        snoozePendingIntent);

        // Mostramos la notificación
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // La id debe de ser única para cada notificación
        notificationManager.notify(3, builder.build());
    }

    public void notificacionRespuesta(View v) {

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Etiqueta1")
                .build();

        // Crea un PendingIntent para la acción de respuesta
        Intent intent = new Intent(this, Main3Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);

        // Creamos la acción de respuesta, asignándole el PendingIntent
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_android_black_24dp,
                        "Etiqueta2", pendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        // Creamos la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(action)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(4, builder.build());
    }

    public void notifiacionBarra(View v) {
        notificationManagerProgreso = NotificationManagerCompat.from(this);
        builderProgreso = new NotificationCompat.Builder(this, CHANNEL_ID);
        builderProgreso.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        // Issue the initial notification with zero progress
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
        builderProgreso.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManagerProgreso.notify(5, builderProgreso.build());

    }

    public void notificacionUp(View v) {
        // Si estamos por debajo del máximo
        if (PROGRESS_CURRENT < PROGRESS_MAX) {
            builderProgreso.setProgress(PROGRESS_MAX, PROGRESS_CURRENT += 10, false);
            notificationManagerProgreso.notify(5, builderProgreso.build());
        }
        // Si llegamos al máximo
        else {
            builderProgreso.setContentText("Download complete")
                    .setProgress(0, 0, false);
            notificationManagerProgreso.notify(5, builderProgreso.build());
        }
    }

    public void notificacionDown(View v) {
        builderProgreso.setProgress(PROGRESS_MAX, PROGRESS_CURRENT-=10, false);
        notificationManagerProgreso.notify(5, builderProgreso.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Nombre_canal";
            String description = "Descripción_canal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
