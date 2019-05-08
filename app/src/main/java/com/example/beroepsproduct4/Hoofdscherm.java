
package com.example.beroepsproduct4;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class Hoofdscherm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    //test
    private ArrayList<String> ontwikkelaars = new ArrayList<String>();

    private static final String TAG = "MyActivity";

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

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ontwikkelaars.add("PhDjDY2gtGSoGrrUNwCrPy2TCpm1");

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        //createUser();

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


    public void checkUser(NavigationView navigationView) {
        for (String x : ontwikkelaars) {
            FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
            if (user.getUid().equals(x)) {
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.evenement_toevoegen).setVisible(true);
                nav_Menu.findItem(R.id.sociaal_netwerk_toevoegen).setVisible(true);
                nav_Menu.findItem(R.id.geen_ontwikkelaar).setVisible(false);
            } else {
                Menu nav_Menu = navigationView.getMenu();
                nav_Menu.findItem(R.id.evenement_toevoegen).setVisible(false);
                nav_Menu.findItem(R.id.sociaal_netwerk_toevoegen).setVisible(false);
                nav_Menu.findItem(R.id.geen_ontwikkelaar).setVisible(true);
                nav_Menu.findItem(R.id.geen_ontwikkelaar).setEnabled(false);
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
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.info_over_mij) {
            // Handle the camera action
        } else if (id == R.id.info_over_anderen) {

        } else if (id == R.id.evenementen) {
            Intent intent = new Intent(Hoofdscherm.this, EvenementAanmaken.class);
            startActivity(intent);

        } else if (id == R.id.sociaal_netwerk) {

        } else if (id == R.id.mijn_agenda) {

        } else if (id == R.id.evenement_toevoegen) {

        } else if (id == R.id.sociaal_netwerk_toevoegen) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}