package com.example.beroepsproduct4;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class EvenementLijstAdapter extends RecyclerView.Adapter<EvenementLijstAdapter.ViewHolder> {

    private List<Evenement> mEvenementLijst;
    private Callback mCallback;
    private static final String EVENEMENTLIJST_PATH = "https://beroepsproductblok4ict1a.firebaseio.com/Evenementen";
    public DatabaseReference mEvenementLijstRef;
    FirebaseDatabase firebaseDatabase;

    public EvenementLijstAdapter(Callback callback, Context context){
        mCallback = callback;
        mEvenementLijst = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance(EVENEMENTLIJST_PATH);
        mEvenementLijstRef = firebaseDatabase.getReference();
        mEvenementLijstRef.addChildEventListener(new EvenementLijstChildEventListener());

    }

    class EvenementLijstChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Evenement evenement = (Evenement) dataSnapshot.getValue(Evenement.class);
            evenement.setEvenementid(dataSnapshot.getKey());
            mEvenementLijst.add(0, evenement);
            notifyDataSetChanged();
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


    @NonNull
    @Override
    public EvenementLijstAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EvenementLijstAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
