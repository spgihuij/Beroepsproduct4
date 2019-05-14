package com.example.beroepsproduct4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.RecursiveAction;

import static android.support.constraint.Constraints.TAG;


public class AnderenZoeken extends Fragment implements SearchView.OnQueryTextListener {

    private ArrayList<String> persoonsnamen = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private RecyclerViewAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.anderenzoekenlayout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        searchView = (SearchView) view.findViewById(R.id.search_view) ;
        searchView.setOnQueryTextListener(this);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Personen");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    private void showData(DataSnapshot dataSnapshot) {
        persoonsnamen.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Persoon persoon = new Persoon();
            persoon.setPersoonnaam( ds.getValue(Persoon.class).getPersoonnaam());

            if(persoon.getPersoonnaam()!= null) {

                persoonsnamen.add(persoon.getPersoonnaam());
                Log.d(TAG, persoon.getPersoonnaam());
            }
            // persoon.setEmail(ds.getValue(Persoon.class).getEmail());
            // persoon.setWoonplaats(ds.getValue(Persoon.class).getWoonplaats());


            adapter = new RecyclerViewAdapter(getActivity(), persoonsnamen);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }
}