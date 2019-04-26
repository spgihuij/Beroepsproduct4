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

    private Button Registreerknop;
    private EditText editTextEmail, editTextWachtwoord;
    private TextView textViewInloggen;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registreerscherm);


        Registreerknop = (Button) findViewById(R.id.button);
        editTextEmail = (EditText) findViewById(R.id.editText1);
        editTextWachtwoord = (EditText) findViewById(R.id.editText2);
        textViewInloggen = (TextView) findViewById(R.id.textView3);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        Registreerknop.setOnClickListener(this);
        textViewInloggen.setOnClickListener(this);

    }

    private void registerUser() {
        final String emailadres = editTextEmail.getText().toString().trim();
        final String wachtwoord = editTextWachtwoord.getText().toString().trim();

        if (TextUtils.isEmpty(emailadres)) {
            // email is leeg
            Toast.makeText(this, "Vul alstublieft een emailadres in", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(wachtwoord)) {
            // wachtwoord is leeg
            Toast.makeText(this, "Vul alstublieft een wachtwoord in", Toast.LENGTH_LONG).show();
            return;
        }
        //Als alles klopt laat ik een progressbar zien
        progressDialog.setMessage("Bezig met registreren...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(emailadres, wachtwoord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(Registreerscherm.this, "Succesvol geregistreerd", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(Registreerscherm.this, "Registreren mislukt probeer het opnieuw", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

        @Override
        public void onClick (View view){
            if (view == Registreerknop) {
                registerUser();

            }

            if (view == textViewInloggen) {
                // Opent inlogscherm
            }
        }
    }
