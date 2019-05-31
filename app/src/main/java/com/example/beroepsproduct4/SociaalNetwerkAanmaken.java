package com.example.beroepsproduct4;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

//servi
public class SociaalNetwerkAanmaken extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference, databaseSociaalnetwerk;
    private EditText editTextSNNaam, editTextBeschrijvingSN;
    private Button btnOpslaan;
    private FirebaseAuth firebaseAuth;
    private ImageButton ibSNFoto;
    private StorageReference storage;
    private StorageReference imagepath;
    private int Gallery_intent = 2;
    SociaalNetwerk sociaalnetwerk;
    String imageLocation;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sociaalnetwerk_aanmaken);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextSNNaam = (EditText) findViewById(R.id.editTextSociaalNetwerk);
        editTextBeschrijvingSN = (EditText) findViewById(R.id.editTextBeschrijvingSociaalNetwerk);
        ibSNFoto = (ImageButton) findViewById(R.id.ibAfbeeldingSociaalNetwerk);
        btnOpslaan = (Button) findViewById(R.id.btnOpslaanaSN);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseSociaalnetwerk = FirebaseDatabase.getInstance().getReference("SociaalNetwerk");
        btnOpslaan.setOnClickListener(this);
        storage = FirebaseStorage.getInstance().getReference();


    }

    // data opslaan
    private void opslaanSociaalNetwerk() {

        String sociaalnetwerknaam = editTextSNNaam.getText().toString().trim();
        String sociaalnetwerkbeschrijving = editTextBeschrijvingSN.getText().toString().trim();
        String sociaalnetwerkfoto = imagepath.toString().trim();
        String id = databaseSociaalnetwerk.push().getKey();
       sociaalnetwerk = new SociaalNetwerk(id, sociaalnetwerknaam,sociaalnetwerkbeschrijving,sociaalnetwerkfoto);
        databaseSociaalnetwerk.child(id).setValue(sociaalnetwerk);
        Toast.makeText(this, "Evenement Toegevoegd", Toast.LENGTH_LONG).show();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_intent && resultCode == RESULT_OK) {
            Uri uri2 = data.getData();
            ibSNFoto.setImageURI(uri2);
            imagepath = storage.child("EvenementFoto").child(uri2.getLastPathSegment());
            imageLocation = uri2.getLastPathSegment();
            imagepath.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(SociaalNetwerkAanmaken.this, "Foto opgeslagen", Toast.LENGTH_LONG).show();
                }
            }).
                    addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SociaalNetwerkAanmaken.this, "Foto niet opgeslagen", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnOpslaan) {
            opslaanSociaalNetwerk();
        }


    }

    public void btnEvenementFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Gallery_intent);
    }


}