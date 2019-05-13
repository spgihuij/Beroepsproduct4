package com.example.beroepsproduct4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Invoerendatapersoon extends Fragment implements View.OnClickListener {

    private DatabaseReference databaseReference, databasePersonen;
    private EditText editText31, editText32, editText33, editText34, editText35, editText36, editText37, editText38;
    private Button btnOpslaan10,btnoplsaandata1,btnoplsaandata2,btnoplsaandata3,btnoplsaandata4,btnoplsaandata5,btnoplsaandata6,btnoplsaandata7,btnoplsaandata8;
    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved) {
        View view = inflater.inflate(R.layout.activity_invoerendatapersoon, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        editText31 = (EditText) view.findViewById(R.id.editText31);
        editText32 = (EditText) view.findViewById(R.id.editText32);
        editText33 = (EditText) view.findViewById(R.id.editText33);
        editText34 = (EditText) view.findViewById(R.id.editText34);
        editText35 = (EditText) view.findViewById(R.id.editText35);
        editText36 = (EditText) view.findViewById(R.id.editText36);
        editText37 = (EditText) view.findViewById(R.id.editText37);
        editText38 = (EditText) view.findViewById(R.id.editText38);
        btnoplsaandata1 =(Button) view.findViewById(R.id.btnopslaandata1);
        btnoplsaandata2 =(Button) view.findViewById(R.id.btnopslaandata2);
        btnoplsaandata3 =(Button) view.findViewById(R.id.btnopslaandata3);
        btnoplsaandata4 =(Button) view.findViewById(R.id.btnopslaandata4);
        btnoplsaandata5 =(Button) view.findViewById(R.id.btnopslaandata5);
        btnoplsaandata6 =(Button) view.findViewById(R.id.btnopslaandata6);
        btnoplsaandata7 =(Button) view.findViewById(R.id.btnopslaandata7);
        btnoplsaandata8 =(Button) view.findViewById(R.id.btnopslaandata8);
        btnOpslaan10 =(Button) view.findViewById(R.id.btnOpslaan10);
        firebaseAuth = FirebaseAuth.getInstance();
        databasePersonen = FirebaseDatabase.getInstance().getReference("Personen");
        btnOpslaan10.setOnClickListener(this);
        btnoplsaandata1.setOnClickListener(this);
        btnoplsaandata2.setOnClickListener(this);
        btnoplsaandata3.setOnClickListener(this);
        btnoplsaandata4.setOnClickListener(this);
        btnoplsaandata5.setOnClickListener(this);
        btnoplsaandata6.setOnClickListener(this);
        btnoplsaandata7.setOnClickListener(this);
        btnoplsaandata8.setOnClickListener(this);

        return view;
    }

    public void opslaanPersoon(){

        String PersoonNaam = editText31.getText().toString().trim();
        String PersoonEmail = editText32.getText().toString().trim();
        String PersoonWoonplaats = editText33.getText().toString().trim();
        String PersoonGeboorteDatum = editText34.getText().toString().trim();
        String PersoonHuisdier = editText35.getText().toString().trim();
        String PersoonSport = editText36.getText().toString().trim();
        String PersoonTvProgramma= editText37.getText().toString().trim();
        String PersoonWebsite = editText38.getText().toString().trim();
        String PersoonProfielFoto = ("-");

        Persoon Persoon= new Persoon(PersoonNaam,PersoonEmail , PersoonWoonplaats, PersoonGeboorteDatum,
                PersoonHuisdier, PersoonSport, PersoonTvProgramma, PersoonWebsite, PersoonProfielFoto);
        Persoon.setPersoonnaam(PersoonNaam);
        Persoon.setPersoonemail(PersoonEmail);
        Persoon.setPersoonwoonplaats(PersoonWoonplaats);
        Persoon.setPersoongeboortedatum(PersoonGeboorteDatum);
        Persoon.setPersoonhuisdier(PersoonHuisdier);
        Persoon.setPersoonsport(PersoonSport);
        Persoon.setPersoontvprogramma(PersoonTvProgramma);
        Persoon.setPersoonwebsite(PersoonWebsite);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databasePersonen.child(PersoonNaam).setValue(Persoon);
        Toast.makeText(getActivity(), "Persoondata Aangepast", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        if(view == btnOpslaan10){
            opslaanPersoon();
        }
    }
    public void onClick1(View view) {
        if(view == btnoplsaandata1){
            opslaanPersoon();
        }
    }
    public void onClick2(View view) {
        if(view == btnoplsaandata2){
            opslaanPersoon();
        }
    }
    public void onClick3(View view) {
        if(view == btnoplsaandata3){
            opslaanPersoon();
        }
    }
    public void onClick4(View view) {
        if(view == btnoplsaandata4){
            opslaanPersoon();
        }
    }
    public void onClick5(View view) {
        if(view == btnoplsaandata5){
            opslaanPersoon();
        }
    }
    public void onClick6(View view) {
        if(view == btnoplsaandata6){
            opslaanPersoon();
        }
    }
    public void onClick7(View view) {
        if(view == btnoplsaandata7){
            opslaanPersoon();
        }
    }
    public void onClick8(View view) {
        if(view == btnoplsaandata8){
            opslaanPersoon();
        }
    }
}
