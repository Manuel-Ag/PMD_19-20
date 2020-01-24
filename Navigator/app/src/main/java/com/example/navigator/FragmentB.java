package com.example.navigator;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {


    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Recuperar argumentos
        if (getArguments() != null) {
            // Bundle
            int recepcion = getArguments().getInt("id");
            Log.i("prueba", recepcion+"");

            // Safe args
            FragmentBArgs args = FragmentBArgs.fromBundle(getArguments());
            Log.i("prueba", args.getCadena());
        }

        View v = inflater.inflate(R.layout.fragment_b, container, false);

        Button boton = v.findViewById(R.id.button2);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enviar datos
                Bundle bundle = new Bundle();
                bundle.putInt("id", 22);
                Navigation.findNavController(v).navigate(R.id.fragmentC, bundle);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
