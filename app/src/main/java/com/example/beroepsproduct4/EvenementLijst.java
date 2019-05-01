package com.example.beroepsproduct4;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class EvenementLijst extends Fragment implements View.OnClickListener {

   /** FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ListView listViewEvenementen;
    private ArrayList<String> mEvenementNaam = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.fragment_evenement_lijst, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Evenementen");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, mEvenementNaam);
        listViewEvenementen = (ListView) view.findViewById(R.id.listViewEvenement);
        listViewEvenementen.setAdapter(arrayAdapter);
        databaseReference.addChildEventListener(new EvenementnaamChildEventListener());
mEvenementNaam = new ArrayList<>();
        return view;
    }





    class EvenementnaamChildEventListener implements ChildEventListener{


        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        Evenement evenementnaam = dataSnapshot.getValue(Evenement.class);
            evenementnaam.setEvenementid(dataSnapshot.getKey());
            mEvenementNaam.add(0, evenementnaam);
            arrayAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }







*/

    @Override
    public void onClick(View view) {


        }


}