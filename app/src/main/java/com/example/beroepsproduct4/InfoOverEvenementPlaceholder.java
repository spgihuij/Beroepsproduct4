package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InfoOverEvenementPlaceholder extends AppCompatActivity {
    TextView evNaam, evDatum,evLocatie,evBeschrijving;
    String evenementNaam;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_info_evenementen);
        getIntentData();

        evNaam = (TextView) findViewById(R.id.tvNaam);
        evDatum = (TextView) findViewById(R.id.tvDatum);
        evLocatie = (TextView) findViewById(R.id.tvLocatie);
        evBeschrijving = (TextView) findViewById(R.id.tvBeschrijving);


        imageView = (ImageView) findViewById(R.id.imageView);


        reference = firebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                evenementData(dataSnapshot);
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {

        String evnaam = dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementnaam").getValue().toString();
        String evdatum = dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementdatum").getValue().toString();
        String evlocatie = dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementlocatie").getValue().toString();
        String evbes =dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementbeschrijving").getValue().toString();
        String evfoto = dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementfoto").getValue().toString();

        evNaam.setText(evnaam);
        evDatum.setText(evdatum);
        evLocatie.setText(evlocatie);
        evBeschrijving.setText(evbes);

        Picasso.get()
                .load(evfoto)
                .placeholder(R.color.colorPrimaryDark)
                .fit()
                .centerCrop()
                .into(imageView);
    }


    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            evenementNaam = bundle.getString("evenementennaam");
    }


    private void evenementData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.child("Evenementen").getChildren()) {
            Evenement evenement = new Evenement();
            try {
                evenement.setEvenementnaam(ds.getValue(Evenement.class).getEvenementnaam());


            } catch (Exception e) {

            }


        }
    }

}
