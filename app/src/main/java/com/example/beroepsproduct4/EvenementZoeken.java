package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class EvenementZoeken extends Fragment implements SearchView.OnQueryTextListener {

    private ArrayList<Evenement> evenementen = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private RecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.evenementen_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Evenementen");

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
        evenementen.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Evenement evenement = new Evenement();
            evenement.setEvenementnaam(ds.getValue(Evenement.class).getEvenementnaam());
            evenement.setEvenementfoto(ds.getValue(Evenement.class).getEvenementfoto());
            evenement.setEvenementbeschrijving(ds.getValue(Evenement.class).getEvenementbeschrijving());
            evenement.setEvenementdatum(ds.getValue(Evenement.class).getEvenementdatum());


            if (evenement != null) {

                evenementen.add(evenement);
                Log.d(TAG, evenement.getEvenementnaam());
            }

        }
        adapter = new RecyclerViewAdapter(getActivity(), null, evenementen, null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!isVisible())
        {
            return true;
        }
        adapter.getFilter().filter(newText);
        return false;
    }
}
