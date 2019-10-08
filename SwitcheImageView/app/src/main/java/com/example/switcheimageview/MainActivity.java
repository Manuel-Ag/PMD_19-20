package com.example.switcheimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private boolean isSonic = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listener toggleButton
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(this);
        Switch aSwitch = findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(this);
    }

    /**
     * Escuchamos si se cambia el estado del switch
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // podemos acceder a la id del ToggleButton pulsado en
        //caso de tener varios (comparar con R.id.ToggleButton)
        int id = buttonView.getId();

        Toast.makeText(this, "Pulsado: " + id + " a " + isChecked, Toast.LENGTH_SHORT).show();
    }

    /**
     * Mostramos una imagen en botón e ImageView
     * @param v
     */
    public void imageButton(View v) {
        ImageView imageView = findViewById(R.id.imageView);
        ImageButton imageButton = findViewById(R.id.imageButton);

        if (isSonic) {
            imageView.setImageResource(R.drawable.knuckles);
            imageButton.setImageResource(android.R.drawable.star_big_off);
            isSonic = false;
        }
        else {
            imageView.setImageResource(R.drawable.sonic);
            imageButton.setImageResource(android.R.drawable.star_big_on);
            isSonic = true;
        }

    }
}
