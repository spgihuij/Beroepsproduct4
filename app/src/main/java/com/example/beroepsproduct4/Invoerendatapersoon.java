package com.example.beroepsproduct4;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class Invoerendatapersoon extends Fragment{

    private DatabaseReference databaseReference, databasePersonen;
    private EditText editText31, editText32, editText33, editText34, editText35, editText36, editText37, editText38;
    private Button btnOpslaan10,btnoplsaandata1,btnoplsaandata2,btnoplsaandata3,btnoplsaandata4,btnoplsaandata5,btnoplsaandata6,btnoplsaandata7,btnoplsaandata8;
    private TextView tvProfielfoto;
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "ViewDatabase";
    private FirebaseDatabase FirebaseDatabase;
    private FirebaseAuth.AuthStateListener AuthListener;
    private DatabaseReference myRef;
    private String userEmail;
    private ListView mListView;


    //Servitest
    private ImageView ivProfielfoto;
    FirebaseStorage storage;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.activity_invoerendatapersoon, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editText31 = (EditText) view.findViewById(R.id.editText31);
        editText32 = (EditText) view.findViewById(R.id.editText32);
        editText33 = (EditText) view.findViewById(R.id.editText33);
        editText34 = (EditText) view.findViewById(R.id.editText34);
        editText35 = (EditText) view.findViewById(R.id.editText35);
        editText36 = (EditText) view.findViewById(R.id.editText36);
        editText37 = (EditText) view.findViewById(R.id.editText37);
        editText38 = (EditText) view.findViewById(R.id.editText38);
        tvProfielfoto = (TextView) view.findViewById(R.id.tvImageurl);
        btnoplsaandata1 =(Button) view.findViewById(R.id.btnopslaandata1);
        btnoplsaandata2 =(Button) view.findViewById(R.id.btnopslaandata2);
        btnoplsaandata3 =(Button) view.findViewById(R.id.btnopslaandata3);
        btnoplsaandata4 =(Button) view.findViewById(R.id.btnopslaandata4);
        btnoplsaandata5 =(Button) view.findViewById(R.id.btnopslaandata5);
        btnoplsaandata6 =(Button) view.findViewById(R.id.btnopslaandata6);
        btnoplsaandata7 =(Button) view.findViewById(R.id.btnopslaandata7);
        btnoplsaandata8 =(Button) view.findViewById(R.id.btnopslaandata8);
        btnOpslaan10 =(Button) view.findViewById(R.id.btnOpslaan10);
        firebaseAuth = FirebaseAuth.getInstance();
        databasePersonen = FirebaseDatabase.getInstance().getReference("Personen");
        FirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getReference().child("Personen");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userEmail = user.getEmail();

        btnOpslaan10.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata4.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata5.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata6.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});
        btnoplsaandata7.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});

        btnoplsaandata8.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v)
            { opslaanPersoon(); }});


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
        for(DataSnapshot ds : dataSnapshot.getChildren()){

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
            if(uInfo.getPersoonemail().equals(userEmail))
            {
                StorageReference gsReference = storage.getReferenceFromUrl(ds.child("persoonprofielFoto").getValue().toString());
                String nam = ds.child("persoonnaam").getValue().toString();
                String email = ds.child("persoonemail").getValue().toString();
                String gd = ds.child("persoongeboortedatum").getValue().toString();
                String hd = ds.child("persoonhuisdier").getValue().toString();
                String sp = ds.child("persoonsport").getValue().toString();
                String tvp = ds.child("persoontvprogramma").getValue().toString();
                String wp = ds.child("persoonwoonplaats").getValue().toString();
                String web = ds.child("persoonwebsite").getValue().toString();
                String pf = ds.child("persoonprofielFoto").getValue().toString();
                tvProfielfoto.setText(pf);




                editText31.setText(nam);
                editText32.setText(email);
                editText33.setText(wp);
                editText34.setText(gd);
                editText35.setText(hd);
                editText36.setText(sp);
                editText37.setText(tvp);
                editText38.setText(web);
//Servitest

                Glide.with(this)
                        .load(gsReference)
                        .centerCrop()
                        .into(ivProfielfoto);

            }else
            {

            }


        }
    }

    public void opslaanPersoon(){


        String PersoonNaam = editText31.getText().toString().trim();
        String PersoonEmail = editText32.getText().toString().trim();
        String PersoonWoonplaats = editText33.getText().toString().trim();
        String PersoonGeboorteDatum = editText34.getText().toString().trim();
        String PersoonHuisdier = editText35.getText().toString().trim();
        String PersoonSport = editText36.getText().toString().trim();
        String PersoonTvProgramma= editText37.getText().toString().trim();
        String PersoonWebsite = editText38.getText().toString().trim();
        String PersoonProfielFoto = tvProfielfoto.getText().toString().trim();
        if (PersoonNaam.trim().length() > 0) {
            Persoon Persoon = new Persoon(PersoonNaam, PersoonEmail, PersoonWoonplaats, PersoonGeboorteDatum,
                    PersoonHuisdier, PersoonSport, PersoonTvProgramma, PersoonWebsite, PersoonProfielFoto);
            Persoon.setPersoonnaam(PersoonNaam);
            Persoon.setPersoonemail(PersoonEmail);
            Persoon.setPersoonwoonplaats(PersoonWoonplaats);
            Persoon.setPersoongeboortedatum(PersoonGeboorteDatum);
            Persoon.setPersoonhuisdier(PersoonHuisdier);
            Persoon.setPersoonsport(PersoonSport);
            Persoon.setPersoontvprogramma(PersoonTvProgramma);
            Persoon.setPersoonwebsite(PersoonWebsite);
            Persoon.setPersoonprofielfoto(PersoonProfielFoto);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        databasePersonen.child(PersoonNaam).setValue(Persoon);
        String dataaangepast = getString(R.string.toastpersoondataaangepast);
        Toast.makeText(getActivity(), dataaangepast, Toast.LENGTH_LONG).show();
    } else {
            String toastvulnaamenemailin = getString(R.string.toastvulnaamenmailin);
            Toast.makeText(getActivity(), toastvulnaamenemailin, Toast.LENGTH_SHORT).show();}
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

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

    }
}



