package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.Nullable;
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


public class Invoerendatapersoon extends Fragment implements View.OnClickListener {

    private DatabaseReference databaseReference, databasePersonen;
    private EditText editText31, editText32, editText33;
    private Button btnOpslaan10;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.activity_invoerendatapersoon, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editText31 = (EditText) view.findViewById(R.id.editText31);
        editText32 = (EditText) view.findViewById(R.id.editText32);
        editText33 = (EditText) view.findViewById(R.id.editText33);
        btnOpslaan10 =(Button) view.findViewById(R.id.btnOpslaan10);
        firebaseAuth = FirebaseAuth.getInstance();
        databasePersonen = FirebaseDatabase.getInstance().getReference("personen");
        btnOpslaan10.setOnClickListener(this);

        return view;
    }

    public void opslaanPersoon(){

        String Naam = editText31.getText().toString().trim();
        String Emailadrespersoon = editText32.getText().toString().trim();
        String Woonplaats = editText33.getText().toString().trim();
        String UserID = databasePersonen.push().getKey();
        UserInformation2 userInformation2= new UserInformation2(Naam, Emailadrespersoon, Woonplaats, UserID);
        userInformation2.setEmailadrespersoon(Emailadrespersoon);
        userInformation2.setWoonplaats(Woonplaats);
        userInformation2.setUserID(UserID);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databasePersonen.child(Naam).setValue(userInformation2);
        Toast.makeText(getActivity(), "Persoondata Aangepast", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if(view == btnOpslaan10){
            opslaanPersoon();
        }
    }
}