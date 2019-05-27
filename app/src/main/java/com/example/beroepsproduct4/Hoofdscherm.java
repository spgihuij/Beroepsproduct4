package com.example.beroepsproduct4;


import android.content.Intent;


import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Hoofdscherm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth, mAuth;
    FirebaseUser currentuser;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private ArrayList<String> ontwikkelaars = new ArrayList<String>();
    private static final String TAG = "MyActivity";
    View creerzinnen;
    View creertimestamp;
    View creerimage;
    ImageView ivHoofdscherm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //navdrawer
        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //beginnen van het maken van welkomszinnen
        creertimestampzinnen(creertimestamp);
        creerrandomzinnen(creerzinnen);
        creerrandomimage(creerimage);
        //gegevens in navigation drawer plaatsen
        updateNavHeader();


        //ontwikkelaars code
        ontwikkelaars.add("mCoC80t1pXfjwvtaXD22xTOprzI2");
        ontwikkelaars.add("nA6IucwbJkgtswYG7MfKwGXC67g1");

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    checkUser(navigationView);
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);


    }

    public void creerrandomimage(View view) {
        ivHoofdscherm = (ImageView) findViewById(R.id.ivHoofdscherm);
        int image1 = R.drawable.hoofdschermimage1;
        int image2 = R.drawable.hoofdschermimage2;
        int image3 = R.drawable.hoofdschermimage3;
        int image4 = R.drawable.hoofdschermimage4;
        int image5 = R.drawable.hoofdschermimage5;

        final int[] leukeplaatjes = {image1, image2, image3, image4, image5};
        int randomimage = (int) (Math.random() * 5);
        ivHoofdscherm.setImageResource(leukeplaatjes[randomimage]);
    }


    public void creertimestampzinnen(View view) {
        Calendar currTime = Calendar.getInstance();
        int hour = currTime.get(Calendar.HOUR_OF_DAY);
        final TextView timestampzin = (TextView) findViewById(R.id.tvTijdbegroet);
        if (hour >= 6 && hour < 12) {
            String goedemorgen = getString(R.string.goedemorgen);
            timestampzin.setText(goedemorgen);
        } else if (hour >= 12 && hour < 18) {
            String goedemiddag = getString(R.string.goedemiddag);
            timestampzin.setText(goedemiddag);
        } else if (hour >= 18 && hour < 23) {
            String goedeavond = getString(R.string.goedeavond);
            timestampzin.setText(goedeavond);
        } else if (hour >= 0 && hour < 6) {
            String goedenacht = getString(R.string.goedenacht);
            timestampzin.setText(goedenacht);
        }


    }


    public void creerrandomzinnen(View view) {
        // random zinnen
        String zin1 = getString(R.string.zin1);
        String zin2 = getString(R.string.zin2);
        String zin3 = getString(R.string.zin3);
        String zin4 = getString(R.string.zin4);
        String zin5 = getString(R.string.zin5);

        final TextView begroetingszin = (TextView) findViewById(R.id.begroetingszinnen_hoofdscherm);
        final String[] voelgoedzinnen = {zin1, zin2, zin3, zin4, zin5};
        int rando = (int) (Math.random() * 5);
        begroetingszin.setText(voelgoedzinnen[rando]);
    }


    public void checkUser(NavigationView navigationView) {
        for (String x : ontwikkelaars) {

            FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
            if (user.getUid().equals(x)) {
                Menu nav_Menu = navigationView.getMenu();

                nav_Menu.findItem(R.id.groep_ontwikkelaar).setVisible(true);
            } else {

                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.groep_ontwikkelaar).setVisible(false);

            }
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Inlogscherm.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.info_over_mij:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Invoerendatapersoon()).commit();
                break;


            case R.id.info_over_anderen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AnderenZoeken()).commit();
                break;

            case R.id.evenementen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EvenementZoeken()).commit();

                break;
            case R.id.sociaal_netwerk:
                //sociaalnetwerk
                break;
            case R.id.mijn_agenda:
                Intent intent2 = new Intent(Hoofdscherm.this, ReadInfoOverAnderen.class);
                startActivity(intent2);
                break;
            case R.id.evenement_toevoegen:
                Intent intent3 = new Intent(Hoofdscherm.this, EvenementAanmaken.class);
                startActivity(intent3);
                break;

            case R.id.sociaal_netwerk_toevoegen:
                //sociaal netwerk teovoegen
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void updateNavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUserName = headerView.findViewById(R.id.nav_profielnaam);
        TextView navUserEmail = headerView.findViewById(R.id.nav_profielemail);
        ImageView navUserPhoto = headerView.findViewById(R.id.nav_profielFoto);

        navUserName.setText(currentuser.getDisplayName());
        navUserEmail.setText(currentuser.getEmail());

        // ik gebruik Glide om een foto te laden
        //Glide.with(this).load(currentuser.getPhotoUrl()).into(navUserPhoto);
    }

}
