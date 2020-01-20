package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class Storage extends AppCompatActivity {

    private StorageReference mStorageRef;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }
    
    public void upload(View v) {
        Uri file = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.captura);
        StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //taskSnapshot.get
                        Log.d("prueba", "completada la subida");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.d("prueba", "fallo subida");
                    }
                });
    }

    public void download(View v) throws IOException {
        final StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

        final File localFile = File.createTempFile("images", "jpg");
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...

                        //Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/prueba-e593f.appspot.com/o/images%2Fcaptura2.jpg?alt=media&token=237213eb-5fab-4f6d-92a4-601b11c1b6a2").into(imageView);

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                ImageView imageView = findViewById(R.id.imageView);

                                Log.d("prueba", "onSuccess: uri= "+ uri.toString());
                                Picasso.get().load(uri.toString()).into(imageView);

                            }
                        });
                        Log.d("prueba", "completada la descarga " );
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
                Log.d("prueba", "fallo descarga");
            }
        });
    }
}
