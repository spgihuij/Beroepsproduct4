package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InfoOverEvenementPlaceholder extends AppCompatActivity {
    TextView naam;
    Button btn;
    String evenementNaam;
    String persoonsEmail;
    String ingelogdePersoonNaam;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferencePersonen;
    private DatabaseReference databaseReferenceGroepAanmaken;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_info_evenementen);
        getIntentData();

        naam = (TextView) findViewById(R.id.ia_iv_naam);
        btn = (Button) findViewById(R.id.buttonInfoOveranderen) ;
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        persoonsEmail = user.getEmail();
        imageView = (ImageView) findViewById(R.id.imageView);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                persoonAanmelden();
            }
        });

        reference = firebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userNaam(dataSnapshot);
                showData(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {

            String nam = dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementnaam").getValue().toString();
            String pf = dataSnapshot.child("Evenementen").child(evenementNaam).child("evenementfoto").getValue().toString();

        naam.setText(nam);


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
            evenementNaam = bundle.getString("evenementennaam");
    }


    private void persoonAanmelden() {

        EvenementGroep evenementGroep = new EvenementGroep(ingelogdePersoonNaam, "-", evenementNaam);
        databaseReferenceGroepAanmaken = FirebaseDatabase.getInstance().getReference("Personen_Evenementen");
        databaseReferenceGroepAanmaken.child(evenementNaam).setValue(evenementGroep);

    }

    private void userNaam(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.child("Evenementen").getChildren()) {
            Evenement evenement = new Evenement();
            try {
                evenement.setEvenementnaam(ds.getValue(Evenement.class).getEvenementnaam());



            }
            catch (Exception e)
            {

            }


            }
        }

    }
