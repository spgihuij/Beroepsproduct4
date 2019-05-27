package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;



public class Invoerendatapersoon extends Fragment {

    private DatabaseReference databaseReference, databasePersonen;
    private EditText editTextWoonplaats, editTextGeboortedatum, editTextHuisdier, editTextSport, editTextTv, editTextWebsite;
    private Button btnOpslaan10;
    private TextView tvProfielfoto, editTextNaam, editTextEmail;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase FirebaseDatabase;
    private FirebaseAuth.AuthStateListener AuthListener;
    private DatabaseReference myRef;
    private String userEmail;
    private ImageView ivProfielfoto;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.activity_invoerendatapersoon, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextNaam = (TextView) view.findViewById(R.id.editTextNaam);
        editTextEmail = (TextView) view.findViewById(R.id.editTextEmail);
        editTextWoonplaats = (EditText) view.findViewById(R.id.editTextWoonplaats);
        editTextGeboortedatum = (EditText) view.findViewById(R.id.editTextGeboortedatum);
        editTextHuisdier = (EditText) view.findViewById(R.id.editTextHuisdier);
        editTextSport = (EditText) view.findViewById(R.id.editTextSport);
        editTextTv = (EditText) view.findViewById(R.id.editTextTv);
        editTextWebsite = (EditText) view.findViewById(R.id.editTextWebsite);
        tvProfielfoto = (TextView) view.findViewById(R.id.tvImageurl);
        btnOpslaan10 = (Button) view.findViewById(R.id.btnOpslaan10);
        firebaseAuth = FirebaseAuth.getInstance();
        databasePersonen = FirebaseDatabase.getInstance().getReference("Personen");
        FirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getReference().child("Personen");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userEmail = user.getEmail();
        btnOpslaan10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opslaanPersoon();
            }
        });
        ivProfielfoto = (ImageView) view.findViewById(R.id.ivProfielFoto);


        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {

                }
            }
        };


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Persoon uInfo = new Persoon();
            uInfo.setPersoonemail(ds.getValue(Persoon.class).getPersoonemail());
            uInfo.setPersoonnaam(ds.getValue(Persoon.class).getPersoonnaam());
            uInfo.setPersoonwoonplaats(ds.getValue(Persoon.class).getPersoonwoonplaats());
            uInfo.setPersoongeboortedatum(ds.getValue(Persoon.class).getPersoongeboortedatum());
            uInfo.setPersoonhuisdier(ds.getValue(Persoon.class).getPersoonhuisdier());
            uInfo.setPersoonsport(ds.getValue(Persoon.class).getPersoonsport());
            uInfo.setPersoontvprogramma(ds.getValue(Persoon.class).getPersoontvprogramma());
            uInfo.setPersoonwebsite(ds.getValue(Persoon.class).getPersoonwebsite());
            uInfo.setPersoonprofielfoto(ds.getValue(Persoon.class).getPersoonprofielfoto());
            if (uInfo.getPersoonemail().equals(userEmail)) {

                String nam = ds.child("persoonnaam").getValue().toString();
                String email = ds.child("persoonemail").getValue().toString();
                String gd = ds.child("persoongeboortedatum").getValue().toString();
                String hd = ds.child("persoonhuisdier").getValue().toString();
                String sp = ds.child("persoonsport").getValue().toString();
                String tvp = ds.child("persoontvprogramma").getValue().toString();
                String wp = ds.child("persoonwoonplaats").getValue().toString();
                String web = ds.child("persoonwebsite").getValue().toString();
                String pf = ds.child("persoonprofielfoto").getValue().toString();
                tvProfielfoto.setText(pf);

                Picasso.get()
                        .load(pf)
                        .placeholder(R.color.colorPrimaryDark)
                        .fit()
                        .centerCrop()
                        .into(ivProfielfoto);

                editTextNaam.setText(nam);
                editTextEmail.setText(email);
                editTextWoonplaats.setText(wp);
                editTextGeboortedatum.setText(gd);
                editTextHuisdier.setText(hd);
                editTextSport.setText(sp);
                editTextTv.setText(tvp);
                editTextWebsite.setText(web);

            }
        }
    }

    public void opslaanPersoon() {


        String PersoonNaam = editTextNaam.getText().toString().trim();
        String PersoonEmail = editTextEmail.getText().toString().trim();
        String PersoonWoonplaats = editTextWoonplaats.getText().toString().trim();
        String PersoonGeboorteDatum = editTextGeboortedatum.getText().toString().trim();
        String PersoonHuisdier = editTextHuisdier.getText().toString().trim();
        String PersoonSport = editTextSport.getText().toString().trim();
        String PersoonTvProgramma = editTextTv.getText().toString().trim();
        String PersoonWebsite = editTextWebsite.getText().toString().trim();
        String PersoonProfielfoto = tvProfielfoto.getText().toString().trim();
        if (PersoonNaam.trim().length() > 0) {
            Persoon Persoon = new Persoon(PersoonNaam, PersoonEmail, PersoonWoonplaats, PersoonGeboorteDatum,
                    PersoonHuisdier, PersoonSport, PersoonTvProgramma, PersoonWebsite, PersoonProfielfoto);
            Persoon.setPersoonnaam(PersoonNaam);
            Persoon.setPersoonemail(PersoonEmail);
            Persoon.setPersoonwoonplaats(PersoonWoonplaats);
            Persoon.setPersoongeboortedatum(PersoonGeboorteDatum);
            Persoon.setPersoonhuisdier(PersoonHuisdier);
            Persoon.setPersoonsport(PersoonSport);
            Persoon.setPersoontvprogramma(PersoonTvProgramma);
            Persoon.setPersoonwebsite(PersoonWebsite);
            Persoon.setPersoonprofielfoto(PersoonProfielfoto);


            FirebaseUser user = firebaseAuth.getCurrentUser();
            databasePersonen.child(PersoonNaam).setValue(Persoon);
            String dataaangepast = getString(R.string.toastpersoondataaangepast);
            Toast.makeText(getActivity(), dataaangepast, Toast.LENGTH_LONG).show();
        } else {
            String toastvulnaamenemailin = getString(R.string.toastvulnaamenmailin);
            Toast.makeText(getActivity(), toastvulnaamenemailin, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthListener != null) {
            firebaseAuth.removeAuthStateListener(AuthListener);
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }
}



