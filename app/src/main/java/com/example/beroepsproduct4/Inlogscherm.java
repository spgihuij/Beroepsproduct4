package com.example.beroepsproduct4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Inlogscherm extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextWachtwoord;
    private Button btnInlog;
    private TextView textViewRegistreren;
private ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
private CheckBox checkBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inlogscherm);
editTextEmail = (EditText) findViewById(R.id.editText1);
editTextWachtwoord = (EditText) findViewById(R.id.editText2);
btnInlog = (Button) findViewById(R.id.button);
textViewRegistreren = (TextView) findViewById(R.id.textView3);
checkBox1 = (CheckBox) findViewById(R.id.checkBox1);

firebaseAuth = FirebaseAuth.getInstance();
progressDialog = new ProgressDialog(this);

if(firebaseAuth.getCurrentUser() !=null){
    // Hoofdscherm starten (want user is al ingelogd)
    finish();
    startActivity(new Intent(getApplicationContext(), Hoofdscherm.class));
}

btnInlog.setOnClickListener(this);
textViewRegistreren.setOnClickListener(this);
        }

        private void userLogin(){
            final String emailadres = editTextEmail.getText().toString().trim();
            final String wachtwoord = editTextWachtwoord.getText().toString().trim();

            if (TextUtils.isEmpty(emailadres)) {
                // email is leeg
                Toast.makeText(this, "Vul alstublieft een emailadres in", Toast.LENGTH_LONG).show();
                return;
            }
            if (checkBox1.isChecked()==false) {
                Toast.makeText(this, "Als u wilt inloggen moet u het vierkantje aanvinken.", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(wachtwoord)) {
                // wachtwoord is leeg
                Toast.makeText(this, "Vul alstublieft een wachtwoord in", Toast.LENGTH_LONG).show();
                return;
            }
            //Als alles klopt laat ik een progressbar zien
            progressDialog.setMessage("Bezig met inloggen...");
            progressDialog.show();

firebaseAuth.signInWithEmailAndPassword(emailadres, wachtwoord)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    // Start hoofdscherm activity
                    finish();
                    startActivity(new Intent(getApplicationContext(), Hoofdscherm.class));
                    Toast.makeText(Inlogscherm.this, "Inloggen succesvol!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Inlogscherm.this, "Inloggen mislukt probeer het opnieuw", Toast.LENGTH_LONG).show();

                }
            }
        });


        }
    @Override
    public void onClick(View view) {
        if(view == btnInlog){
            userLogin();
        }

        if(view == textViewRegistreren) {
            finish();
            startActivity(new Intent(this, Registreerscherm.class));
        }

    }
}
