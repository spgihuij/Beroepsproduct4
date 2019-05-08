package com.example.beroepsproduct4;


import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;


public class Hoofdscherm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth firebaseAuth;
    FragmentManager fragmentManager = getSupportFragmentManager();
    private ArrayList<String> ontwikkelaars = new ArrayList<String>();
    private static final String TAG = "MyActivity";

    private TextView mEmail;
    FirebaseUser user;
    View creerzinnen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        creerrandomzinnen(creerzinnen);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        //ontwikkelaars code
        ontwikkelaars.add("PhDjDY2gtGSoGrrUNwCrPy2TCpm1");

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


    public void creerrandomzinnen(View view) {
        // random zinnen
        final TextView gebroetingszinnen = (TextView) findViewById(R.id.begroetingszinnen_hoofdscherm);
        Button generate = (Button) findViewById(R.id.btnzinnengenerate);
        final String[] voelgoedzinnen = {"voel je je goed", "hoe voel je je vandaag", "helemaal top vandaag"};

        int rando = (int) ((Math.random() * 3));

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rando = (int) (Math.random() * 3);
                gebroetingszinnen.setText(voelgoedzinnen[rando]);
            }
        });
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
                fragmentManager.beginTransaction()
                        .replace(R.id.drawer_layout,
                                new AnderenZoeken())
                        .commit();
                break;

            case R.id.evenementen:
                Intent intent = new Intent(Hoofdscherm.this, EvenementAanmaken.class);
                startActivity(intent);
                break;
            case R.id.sociaal_netwerk:
                //sociaalnetwerk
                break;
            case R.id.mijn_agenda:
                Intent intent2 = new Intent(Hoofdscherm.this, ReadInfoOverAnderen.class);
                startActivity(intent2);
                break;
            case R.id.evenement_toevoegen:
                // evenement toevoegen
                break;
            case R.id.sociaal_netwerk_toevoegen:
                //sociaal netwerk teovoegen
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        return true;

        }

    }
