package com.example.beroepsproduct4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Registreerscherm extends AppCompatActivity
        implements View.OnClickListener {


    private int Gallery_intent = 2;
    private ImageButton imageButton;
    private Button Registreerknop, btnNaarInlog;
    private EditText editTextEmail, editTextWachtwoord, editTextNaam;
    private TextView textViewInloggen;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databasePersoonmetmail;
    private CheckBox checkBox;
    private StorageReference storage;
    private StorageReference imagepath;
    Persoon persoon;
    String imageLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreerscherm);
        imageButton = (ImageButton) findViewById(R.id.ibProfielfoto);
        Registreerknop = (Button) findViewById(R.id.button);
        btnNaarInlog = (Button) findViewById(R.id.button3);
        editTextNaam = (EditText) findViewById(R.id.editTextNaam);
        editTextEmail = (EditText) findViewById(R.id.editText1);
        editTextWachtwoord = (EditText) findViewById(R.id.editTextNaam);
        textViewInloggen = (TextView) findViewById(R.id.textView3);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        Registreerknop.setOnClickListener(this);
        btnNaarInlog.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.checkBoxnaamopslaan);
        checkBox.setOnClickListener(this);
        storage = FirebaseStorage.getInstance().getReference();


        if (firebaseAuth.getCurrentUser() != null) {
            // Hoofdscherm starten (want user is al ingelogd)
            finish();
            startActivity(new Intent(getApplicationContext(), Hoofdscherm.class));
        }
    }


    private void naamenemailOpslaan() {

        final String emailadres = editTextEmail.getText().toString().trim();
        final String naam = editTextNaam.getText().toString().trim();
        final String profielfoto = imagepath.toString().trim();
        firebaseAuth = FirebaseAuth.getInstance();
        databasePersoonmetmail = FirebaseDatabase.getInstance().getReference("Persoon Met Mail");
        persoon = new Persoon(naam, emailadres, profielfoto);
        databasePersoonmetmail.child(naam).setValue(persoon);
        Toast.makeText(Registreerscherm.this, "U heeft toestemming gegeven.", Toast.LENGTH_SHORT);

    }


    private void registerUser() {

        final String emailadres = editTextEmail.getText().toString().trim();
        final String wachtwoord = editTextWachtwoord.getText().toString().trim();
        final String naam = editTextEmail.getText().toString().trim();
        String toastgeennaam = getString(R.string.toastgeennaam);
        String toastgeenemail = getString(R.string.toastgeenemail);
        String toastgeenwachtwoord = getString(R.string.toastgeenwachtwoord);
        String toastbezigmetregistreren = getString(R.string.toastbezigmetregistreren);
        String toastgeencheckbox = getString(R.string.toastgeencheckbox);

        if (TextUtils.isEmpty(emailadres)) {
            // email is leeg
            Toast.makeText(this, toastgeenemail, Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(wachtwoord)) {
            // wachtwoord is leeg
            Toast.makeText(this, toastgeenwachtwoord, Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(naam)) {
            // wachtwoord is leeg
            Toast.makeText(this, toastgeennaam, Toast.LENGTH_LONG).show();
            return;
        }
        if (checkBox.isChecked() == false) {
            Toast.makeText(this, toastgeencheckbox, Toast.LENGTH_LONG).show();
            return;
        }
        //Als alles klopt laat ik een progressbar zien
        progressDialog.setMessage(toastbezigmetregistreren);
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(emailadres, wachtwoord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Hoofdscherm.class));

                            progressDialog.dismiss();
                            String toastregistrerensuccesvol = getString(R.string.toastregistrerensuccesvol);

                            Toast.makeText(Registreerscherm.this, toastregistrerensuccesvol, Toast.LENGTH_LONG).show();

                        } else {
                            String toastregistrerenmislukt = getString(R.string.toastregistrerenmislukt);
                            Toast.makeText(Registreerscherm.this, toastregistrerenmislukt, Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }


    @Override
    public void onClick(View view) {
        if (view == Registreerknop) {
            registerUser();
        }

        if (view == checkBox) {
            naamenemailOpslaan();
        }
        if (view == btnNaarInlog) {
            // Opent inlogscherm
            startActivity(new Intent(this, Inlogscherm.class));
        }

    }

    public void btnProfielfoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Gallery_intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_intent && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageButton.setImageURI(uri);
            imagepath = storage.child("PersoonInlog").child(uri.getLastPathSegment());
            imageLocation = uri.getLastPathSegment();
            imagepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Registreerscherm.this, "Profielfoto geupload", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Registreerscherm.this, "Profielfoto niet geupload", Toast.LENGTH_SHORT).show();

                }
            });
        }


    }
}
