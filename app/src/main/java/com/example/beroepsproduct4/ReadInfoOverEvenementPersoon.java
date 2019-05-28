package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadInfoOverEvenementPersoon extends AppCompatActivity {
    private TextView naam, geboortedatum, woonplaats, sport, huisdier, tvprogramma, website;
    private Button btn;
    private String persoonsNaam2, ingelogdePersoonNaam, evenementNaam, persoonsEmail;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private DatabaseReference writeGroepDataRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_info_over_anderen);
        getIntentData();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        persoonsEmail = user.getEmail();


        naam = (TextView) findViewById(R.id.ia_iv_naam);
        geboortedatum = (TextView) findViewById(R.id.ia_iv_Geboortedatum);
        woonplaats = (TextView) findViewById(R.id.ia_iv_woonp);
        sport = (TextView) findViewById(R.id.ia_iv_sport);
        huisdier = (TextView) findViewById(R.id.ia_iv_huisdier);
        tvprogramma = (TextView) findViewById(R.id.ia_iv_tvprogramma);
        website = (TextView) findViewById(R.id.ia_iv_website);
        btn = (Button) findViewById(R.id.buttonSamenGaan);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                writeData();

                }
            });


        reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
                userNaam(dataSnapshot);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        String nam = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoonnaam").getValue().toString();
        String gd = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoongeboortedatum").getValue().toString();
        String hd = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoonhuisdier").getValue().toString();
        String sp = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoonsport").getValue().toString();
        String tvp = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoontvprogramma").getValue().toString();
        String wp = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoonwoonplaats").getValue().toString();
        String web = dataSnapshot.child("Personen").child(persoonsNaam2).child("persoonwebsite").getValue().toString();
        naam.setText(nam);
        geboortedatum.setText(gd);
        woonplaats.setText(wp);
        sport.setText(sp);
        huisdier.setText(hd);
        tvprogramma.setText(tvp);
        website.setText(web);
    }

    private void userNaam(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.child("Personen").getChildren()) {
            Persoon persoon = new Persoon();
            try {
                persoon.setPersoonnaam(ds.getValue(Persoon.class).getPersoonnaam());
                persoon.setPersoonemail(ds.getValue(Persoon.class).getPersoonemail());
            }
            catch (Exception e)
            {
            }


            if (persoon.getPersoonemail().equals(persoonsEmail)) {
                ingelogdePersoonNaam = persoon.getPersoonnaam();
                break;
            }
        }

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            persoonsNaam2 = bundle.getString("persoonsnaam");
            evenementNaam = bundle.getString("evenementnaam");

    }

    private void writeData()
    {
        EvenementGroep evenementGroep = new EvenementGroep(ingelogdePersoonNaam, persoonsNaam2, evenementNaam);
        writeGroepDataRef = FirebaseDatabase.getInstance().getReference("Personen_Evenementen");
        writeGroepDataRef.child(evenementNaam).setValue(evenementGroep);
    }
}

