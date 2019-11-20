package com.example.consultar_contactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pedimos los contactos de esta forma para ahorrar líneas. Consultar la entrada sobre permisos
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS},
                0);
    }

    /**
     * Listaremos los contactos que contengan una letra en su nombre
     * @param v
     */
    public void listarContactos(View v) {
        // Columnas a recuperar
        String proyeccion[] = {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER, ContactsContract.Contacts.PHOTO_ID};

        // Filtros de la búsqeda
        String filtro = ContactsContract.Contacts.DISPLAY_NAME + " like ?";
        // Nombres que contenga la 'a'
        String arg_filtro[] = {"%a%"};

        //Consulta de los contactos
        ContentResolver contentResolver = getContentResolver();
        Cursor cur = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, proyeccion, filtro, arg_filtro, null);

        // Al recorrer el cursor, recuperamos el resultado de la consulta
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                // Recuperamos las 3 columnas, ID, DISPLAY_NAME y HAS_PHONE_NUMBER
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.i("Contactos", "ID: " + id + " nombre: " + name);
                // Si el contacto tiene algún número de teléfono, los mostramos todos
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    // Realizamos otra consulta cambiado el filtro, cuando el ID del contacto es igual al ID de la tabla donde se encuentran los números de teléfono
                    Cursor phones = contentResolver.query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, null, null);
                    // Volvemos a recorrer un cursos
                    while (phones.moveToNext())
                    {
                        String telefono = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i("Contactos" , telefono);
                    }
                }
            }
        }
    }


}
