package com.example.beroepsproduct4;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ReadInfoOverAnderen extends AppCompatActivity {

    TextView naam, geboortedatum, woonplaats, sport, huisdier, tvprogramma, website, infoover;
    Button btn;
    String persoonsNaam;
    DatabaseReference reference;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_info_over_anderen);
        getIntentData();


        naam = (TextView) findViewById(R.id.tvNaam);
        geboortedatum = (TextView) findViewById(R.id.tvGeboortedatum);
        woonplaats = (TextView) findViewById(R.id.tvWoonplaats);
        sport = (TextView) findViewById(R.id.ia_iv_sport);
        huisdier = (TextView) findViewById(R.id.ia_iv_huisdier);
        tvprogramma = (TextView) findViewById(R.id.ia_iv_tvprogramma);
        website = (TextView) findViewById(R.id.ia_iv_website);
        imageView = (ImageView) findViewById(R.id.imageView);
        infoover = (TextView) findViewById(R.id.titel);
        btn = (Button) findViewById(R.id.buttonSamenGaan);
        btn.setVisibility(View.INVISIBLE);


        reference = FirebaseDatabase.getInstance().getReference().child("Personen").child(persoonsNaam);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        String nam = dataSnapshot.child("persoonnaam").getValue().toString();
        String gd = dataSnapshot.child("persoongeboortedatum").getValue().toString();
        String hd = dataSnapshot.child("persoonhuisdier").getValue().toString();
        String sp = dataSnapshot.child("persoonsport").getValue().toString();
        String tvp = dataSnapshot.child("persoontvprogramma").getValue().toString();
        String wp = dataSnapshot.child("persoonwoonplaats").getValue().toString();
        String web = dataSnapshot.child("persoonwebsite").getValue().toString();
        String pf = dataSnapshot.child("persoonprofielfoto").getValue().toString();
        naam.setText(nam);
        geboortedatum.setText(gd);
        woonplaats.setText(wp);
        sport.setText(sp);
        huisdier.setText(hd);
        tvprogramma.setText(tvp);
        website.setText(web);
        infoover.setText(nam);
        Picasso.get()
                .load(pf)
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(imageView);
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            persoonsNaam = bundle.getString("persoonsnaam");
    }

}

