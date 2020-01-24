package com.example.navigator;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {


    public FragmentA() {
        // Required empty public constructor
    }


    // https://developer.android.com/guide/navigation/navigation-getting-started

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        Button boton = v.findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentADirections.ActionFragmentA3ToFragmentB action = FragmentADirections.actionFragmentA3ToFragmentB();
                action.setCadena("asdf");
                Navigation.findNavController(v).navigate(action);

            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}
