package com.example.radiobuttonycheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listener para el radioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroupEquipos);
        radioGroup.setOnCheckedChangeListener(this);
        //listener para los checkBox
        CheckBox checkBoxLocal = findViewById(R.id.checkBoxLocal);
        CheckBox checkBoxCamiseta = findViewById(R.id.checkBoxCamiseta);
        checkBoxLocal.setOnCheckedChangeListener(this);
        checkBoxCamiseta.setOnCheckedChangeListener(this);
    }

    //Botón sin listener
    public void  mostrarValores(View v) {
        String texto;
        RadioButton radioButton;
        //Referencia al RadioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroupEquipos);
        //Obtengo la id del radioButton que está seleccionado por el usuario
        int id = radioGroup.getCheckedRadioButtonId();
        //switch según el id seleccionado
        switch (id){
            case R.id.radioButtonReal:
                //"Hala Madrid! Cambia camiseta y es Local"
                //"Hala Madrid! No cambia camiseta y no es Local"
                Toast.makeText(this, "Hala Madrid!" + comprobarCheckBox(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButtonAtletico:
                Toast.makeText(this, "Aupa Atleti" + comprobarCheckBox(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButtonZaragoza:
                Toast.makeText(this, "Viva Zaragoza" + comprobarCheckBox(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Método para el radioGroup
     * @param group Nos llega una referencia a quién es el group seleccionado (pueden haber varios)
     * @param checkedId Nos llega el id numérico del elemento seleccionado
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //Referencio el radioButton seleccionado
        RadioButton radioButton = findViewById(checkedId);
        //Imprimir texto del radioButon seleccionado
        Toast.makeText(this, radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Método para los checkBox
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //Esta vez probaremos a comparar las vistas
        if (buttonView == findViewById(R.id.checkBoxCamiseta))
            Toast.makeText(this, "Ha cambiado Camiseta " + isChecked, Toast.LENGTH_SHORT).show();
        else if (buttonView == findViewById(R.id.checkBoxLocal))
            Toast.makeText(this, "Ha cambiado Local " + isChecked, Toast.LENGTH_SHORT).show();
    }

    /**
     * Compruebo el estado de los checkBox
     * @return devuelve la cadena correspondiente dependiendo de los checkBox
     */
    private String comprobarCheckBox() {
        String cadena = "";

        CheckBox checkBoxLocal = findViewById(R.id.checkBoxLocal);
        CheckBox checkBoxCamiseta = findViewById(R.id.checkBoxCamiseta);

        if (checkBoxLocal.isChecked())
            cadena += " es local";
        else
            cadena += " no es Local";
        if (checkBoxCamiseta.isChecked())
            cadena += " con camiseta";
        else
            cadena += " sin camiseta";

        return cadena;


    }
}
