package com.example.tamao_layout_en_ejecucion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l = findViewById(R.id.linearLayout);
        ViewTreeObserver observer = l.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                init();
                l.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });
    }
    protected void init() {
        int a= l.getHeight();
        int b = l.getWidth();
        //Toast.makeText(this,""+a+" "+b,3000).show();
        Log.i("Tamanyo: ", a + " " + b);
    }
}



