package com.example.grabacinvideo;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private final static int GRABAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void grabar(View v) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5); // límite en segundos
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0); // 0 baja calidad, 1 alta

        startActivityForResult(intent, GRABAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GRABAR) {
            if (resultCode == RESULT_OK) {
                VideoView videoView = findViewById(R.id.videoView);
                videoView.setVideoURI(data.getData());

                // Recuerda que tenemos muchos otros métodos para administrar videoView https://developer.android.com/reference/android/widget/VideoView
                videoView.start();
            }
        }
    }
}
