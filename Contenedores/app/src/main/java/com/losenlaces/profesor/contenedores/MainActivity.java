package com.losenlaces.profesor.contenedores;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // tv = (TextView) findViewById(R.id.texto1);
       // recorrer();
        anadeHijos();

    }

    public void anadeHijos()
    {
        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.constraintLay);

        for (int i = 0 ; i <3; i++) {
            b = new Button(this);


            //Seteamos los parámetros de tamaños para el layout. En este caso
            // se ajusta al tamaño del contenido del botón en altura y anchura
            b.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));


            b.setText("btn" + i);

            b.setId(View.generateViewId());

            b.setX(300*i);

            cl.addView(b, i);

        }

    }














    public void anadeHijos2()
    {
        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.constraintLay);


        for (int i = 0; i <3; i++)
        {
            b = new Button(this);

            b.setLayoutParams( new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            b.setX(i*300);
            b.setText("btn" + i);
            b.setId(View.generateViewId());
            cl.addView(b, i);
        }

    }






    public void recorrer()
    {
        View v;

        ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.constraintLay);

        int numEltos = cl.getChildCount(); // nos devuelve el nº de elementos que hay en el layout

        for (int i =0 ; i< cl.getChildCount(); i++)
        {
            v=cl.getChildAt(i); //Guarda en v el elemento que está en la posición i
            //System.out.println("objeto : " + v.toString());
                String s = v.getClass().getSimpleName();
            //COMPROBAMOS SI EL ELEMENTO ES UN BOTÓN
            if(v.getClass().getSimpleName().equals("AppCompatButton"))
            {
                b = (Button) v;
                b.setOnClickListener(this);
            }

        }






        if (numEltos >= 1)
        {
            v = cl.getChildAt(0);
            tv.setText("objeto : " + v.toString());

        }



    }

    @Override
    public void onClick(View v) {

    }
}
