package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class OverzichtData extends Fragment {
    private static final String TAG = "ViewDatabase";

    //add Firebase Database stuff
    private FirebaseDatabase FirebaseDatabase;
    private FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private DatabaseReference myRef;
    private String userEmail;

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.activity_overzicht_data, container, false);

       mListView = (ListView) view.findViewById(R.id.listviewData);


        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        FirebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getReference().child("Personen");
        FirebaseUser user = FirebaseAuth.getCurrentUser();
        userEmail = user.getEmail();


        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getEmail());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
            }
        };


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
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
            UserInformation uInfo = new UserInformation();
            uInfo.setEmailadrespersoon(ds.getValue(UserInformation.class).getEmailadrespersoon());
            uInfo.setNaam(ds.getValue(UserInformation.class).getNaam());
            uInfo.setWoonplaats(ds.getValue(UserInformation.class).getWoonplaats());
        if (uInfo.getEmailadrespersoon().equals(userEmail))
            {
                //display all the information
                Log.d(TAG, "showData: persoonnaam: " + uInfo.getNaam());
                Log.d(TAG, "showData: persoonwoonplaats: " + uInfo.getWoonplaats());
                Log.d(TAG, "showData: persoonemail: " + uInfo.getEmailadrespersoon());
                ArrayList<String> array  = new ArrayList<>();
                array.add(uInfo.getNaam());
                array.add(uInfo.getWoonplaats());
                array.add(uInfo.getEmailadrespersoon());
                ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,array);
                mListView.setAdapter(adapter);
            }

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthListener != null) {
            FirebaseAuth.removeAuthStateListener(AuthListener);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

    }
}