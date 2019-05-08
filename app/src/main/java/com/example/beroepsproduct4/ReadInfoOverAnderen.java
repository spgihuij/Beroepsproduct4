package com.example.beroepsproduct4;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadInfoOverAnderen extends AppCompatActivity {
    TextView naam, geboortedatum, woonplaats, sport, huisdier, tvprogramma, website;
    Button btn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_info_over_anderen);

        naam = (TextView) findViewById(R.id.ia_iv_naam);
        geboortedatum = (TextView) findViewById(R.id.ia_iv_Geboortedatum);
        woonplaats = (TextView) findViewById(R.id.ia_iv_woonp);
        sport = (TextView) findViewById(R.id.ia_iv_sport);
        huisdier = (TextView) findViewById(R.id.ia_iv_huisdier);
        tvprogramma = (TextView) findViewById(R.id.ia_iv_tvprogramma);
        website = (TextView) findViewById(R.id.ia_iv_website);

        btn = (Button) findViewById(R.id.buttonInfoOveranderen);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("info over anderen").child("rens");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String nam = dataSnapshot.child("naam").getValue().toString();
                        String gd = dataSnapshot.child("geboortedatum").getValue().toString();
                        String hd = dataSnapshot.child("huisdier").getValue().toString();
                        String sp = dataSnapshot.child("sport").getValue().toString();
                        String tvp = dataSnapshot.child("tv programma").getValue().toString();
                        String wp = dataSnapshot.child("woonplaats").getValue().toString();
                        String web = dataSnapshot.child("website").getValue().toString();
                        naam.setText(nam);
                        geboortedatum.setText(gd);
                        woonplaats.setText(wp);
                        sport.setText(sp);
                        huisdier.setText(hd);
                        tvprogramma.setText(tvp);
                        website.setText(web);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });

    }
}
