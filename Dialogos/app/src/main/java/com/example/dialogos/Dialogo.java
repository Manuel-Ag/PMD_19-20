package com.example.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialogo extends DialogFragment {
    Respuesta respuesta;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Utilizamos esta clase para crear el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Título diálogo");
        builder.setMessage("Este será el mensaje a mostrar");
        //Botón positivo
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Llamo a la implementación en MainActivity.java
                respuesta.onRespuesta("A");
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("Prueba", "funciono");
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        respuesta = (Respuesta) context;
    }

    /**
     * Declaración de la interface que se ejecutará al pulsar los botones
     */
    public interface Respuesta {
        //Los métodos en las interfaces solo están declarados
        public void onRespuesta(String s);
    }


}

