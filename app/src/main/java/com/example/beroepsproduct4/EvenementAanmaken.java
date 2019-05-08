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


public class EvenementAanmaken extends AppCompatActivity implements View.OnClickListener {

private DatabaseReference databaseReference, databaseEvenementen;
private EditText editTextEvenementnaam, editTextLocatie, editTextBeschrijving, editTextDatum;
private Button btnOpslaan;
private FirebaseAuth firebaseAuth;
private ImageButton ibEvenementFoto;
private StorageReference storage;
private StorageReference imagepath;
private int Gallery_intent =2;
Evenement evenement;
String imageLocation;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenementaanmaken);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextEvenementnaam = (EditText) findViewById(R.id.editTextEvenementnaam);
        editTextLocatie = (EditText) findViewById(R.id.editTextLocatie);
        editTextBeschrijving = (EditText) findViewById(R.id.editTextBeschrijving);
        editTextDatum = (EditText) findViewById(R.id.editTextDatum) ;
        ibEvenementFoto = (ImageButton) findViewById(R.id.ibAfbeeldingEvenement);
        btnOpslaan =(Button) findViewById(R.id.btnOpslaan);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseEvenementen = FirebaseDatabase.getInstance().getReference("Evenementen");
        btnOpslaan.setOnClickListener(this);
        storage = FirebaseStorage.getInstance().getReference();



    }

    // data opslaan
    private void opslaanEvenement(){

    String evenementnaam = editTextEvenementnaam.getText().toString().trim();
        String evenementlocatie = editTextLocatie.getText().toString().trim();
        String evenementbeschrijving = editTextBeschrijving.getText().toString().trim();
        String evenementdatum = editTextDatum.getText().toString().trim();
        String evenementfoto = imagepath.toString().trim();
        String id = databaseEvenementen.push().getKey();
        evenement = new Evenement(id, evenementnaam, evenementlocatie, evenementbeschrijving,evenementdatum, evenementfoto);
        databaseEvenementen.child(id).setValue(evenement);
        Toast.makeText(this, "Evenement Toegevoegd", Toast.LENGTH_LONG).show();

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Gallery_intent && resultCode == RESULT_OK){
            Uri uri2 = data.getData();
            ibEvenementFoto.setImageURI(uri2);
            imagepath = storage.child("EvenementFoto").child(uri2.getLastPathSegment());
            imageLocation = uri2.getLastPathSegment();
            imagepath.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(EvenementAanmaken.this, "Foto opgeslagen", Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EvenementAanmaken.this, "Foto niet opgeslagen", Toast.LENGTH_LONG).show();

                }
            });
        }


    }
    @Override
    public void onClick(View view) {
if(view == btnOpslaan){
    opslaanEvenement();
}


    }
    public void btnEvenementFoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Gallery_intent);
    }


}