package com.example.beroepsproduct4;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EvenementAanmaken extends Fragment implements View.OnClickListener {

private DatabaseReference databaseReference;
private EditText editTextEvenementnaam, editTextLocatie, editTextBeschrijving;
private Button btnOpslaan;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.fragment_evenement_aanmaken, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextEvenementnaam = (EditText) view.findViewById(R.id.editTextEvenementnaam);
        editTextLocatie = (EditText) view.findViewById(R.id.editTextLocatie);
        editTextBeschrijving = (EditText) view.findViewById(R.id.editTextBeschrijving);
        btnOpslaan =(Button) view.findViewById(R.id.btnOpslaan);
        firebaseAuth = FirebaseAuth.getInstance();

        btnOpslaan.setOnClickListener(this);

        return view; //test2
    }

    // data opslaan
    private void opslaanEvenement(){
    String evenementnaam = editTextEvenementnaam.getText().toString().trim();
        String evenementlocatie = editTextLocatie.getText().toString().trim();
        String evenementbeschrijving = editTextBeschrijving.getText().toString().trim();

        Evenement evenement = new Evenement(evenementnaam, evenementlocatie, evenementbeschrijving);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(evenement);

        Toast.makeText(getActivity(), "Evenement Toegevoegd", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
if(view == btnOpslaan){
    opslaanEvenement();
}
    }
}