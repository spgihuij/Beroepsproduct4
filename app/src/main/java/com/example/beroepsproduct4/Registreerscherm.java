package com.example.beroepsproduct4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registreerscherm extends AppCompatActivity
    implements View.OnClickListener {

    private Button Registreerknop, btnNaarInlog;
    private EditText editTextEmail, editTextWachtwoord;
    private TextView textViewInloggen;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreerscherm);

        Registreerknop = (Button) findViewById(R.id.button);
        btnNaarInlog = (Button) findViewById(R.id.button3);

        editTextEmail = (EditText) findViewById(R.id.editText1);
        editTextWachtwoord = (EditText) findViewById(R.id.editText2);
        textViewInloggen = (TextView) findViewById(R.id.textView3);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        Registreerknop.setOnClickListener(this);
        btnNaarInlog.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser() !=null){
            // Hoofdscherm starten (want user is al ingelogd)
            finish();
            startActivity(new Intent(getApplicationContext(), Hoofdscherm.class));
        }
    }

    private void registerUser() {
        final String emailadres = editTextEmail.getText().toString().trim();
        final String wachtwoord = editTextWachtwoord.getText().toString().trim();
        String toastgeenemail = getString(R.string.toastgeenemail);
        String toastgeenwachtwoord = getString(R.string.toastgeenwachtwoord);
        String toastbezigmetregistreren = getString(R.string.toastbezigmetregistreren);

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
        public void onClick (View view){
            if (view == Registreerknop) {
                registerUser();

            }

            if (view == btnNaarInlog) {
                // Opent inlogscherm
                startActivity(new Intent(this, Inlogscherm.class));
            }
        }
    }
