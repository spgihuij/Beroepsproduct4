package com.example.beroepsproduct4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class PersonenZoekenEvenement extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<EvenementGroep> evenementGroepen = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private RecyclerViewAdapter adapter;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personeninevenement_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        searchView = (SearchView) findViewById(R.id.search_view_groepen);
        searchView.setOnQueryTextListener(this);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Personen_Evenementen");



        databaseReference.addValueEventListener(new ValueEventListener() {
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
        evenementGroepen.clear();
        Intent intent = getIntent();
        String evenementnaam = intent.getStringExtra("evenementnaam");


        for (DataSnapshot ds : dataSnapshot.getChildren()) {


            EvenementGroep evenementGroep = new EvenementGroep();
            if(ds.getValue(EvenementGroep.class).getPersoonsnaam2().equals("-") && ds.getValue(EvenementGroep.class).getEvenementnaam().equals(evenementnaam))
            {
                evenementGroep.setPersoonsnaam1(ds.getValue(evenementGroep.getClass()).getPersoonsnaam1());
                evenementGroep.setEvenementnaam(evenementnaam);
                //persoon.setPersoonprofielfoto(ds.getValue(Persoon.class).getPersoonprofielfoto());
                evenementGroepen.add(evenementGroep);
            }

            adapter = new RecyclerViewAdapter(this, null, null, evenementGroepen);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(adapter!= null) {
            adapter.getFilter().filter(newText);
            return false;
        }
        return true;
    }
}
