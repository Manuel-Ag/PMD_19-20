package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Ejemplo a seguir: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
 */

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    //private String[] myDataSet = {"Prueba1", "Prueba2", "Prueba3"};
    private ArrayList<String> myDataSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myDataSet.add("Prueba1");
        myDataSet.add("Prueba2");
        myDataSet.add("Prueba3");

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataSet);
        mAdapter.setClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("prueba", "You clicked " + mAdapter.getItem(position) + " on row number " + position);
    }

    // Para añadir nuevos elementos consultar: https://stackoverflow.com/questions/31367599/how-to-update-recyclerview-adapter-data/48959184#48959184
    public void boton(View v) {
        myDataSet.add("Añadido");
        mAdapter.notifyDataSetChanged();
    }
}
